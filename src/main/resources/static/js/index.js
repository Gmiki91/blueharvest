var userInfo = document.getElementById("user-info");
var details = document.querySelector(".details");
userInfo.style.display="none";
details.style.display="none";

window.onload=fetchChar;
var imageId;
var selectLearnable = document.createElement("select");;
var selectDoable = document.createElement("select");;

function fetchChar(){
    fetch("/character")
    .then(function (response) {
          return response.json();
    })
    .then(function (jsonData) {
         if (jsonData!=null){
            var logkibe = document.getElementById("login");
            logkibe.innerHTML = "Kijelentkezés";
            logkibe.href = "/logout";

            userInfo.style.display="block";
            details.style.display="block";
            displaySkillsLearned(jsonData.character.id);
            document.getElementById("column-name").innerHTML=jsonData.character.name;

            imageId = jsonData.character.imageId;
            var img = document.getElementById("image");
            getPic(img);
            img.setAttribute('width','200');
            img.setAttribute('height','200');

            document.getElementById("cell1").innerHTML=jsonData.character.food;
            document.getElementById("cell2").innerHTML=jsonData.character.money;

            var actionButton = document.createElement("button");
            var learnButton = document.createElement("button");

            document.getElementById("cell3").innerHTML="";
            if (jsonData.character.status == "AVAILABLE"){
                actionButton.innerHTML="Csináld: ";
                learnButton.innerHTML="Tanulj: ";
                document.getElementById("cell3").appendChild(learnButton);
                document.getElementById("cell3").appendChild(selectLearnable);
                displaySkillsToLearn(jsonData.character.id);
                document.getElementById("cell3").appendChild(actionButton);
                document.getElementById("cell3").appendChild(selectDoable);
                displaySkillsToDo(jsonData.character.id);
            }else if (jsonData.character.status == "LEARNING"){
                learnButton.innerHTML = "Szörnyed éppen a következőt tanulja: "+jsonData.nameOfSkillLearned+".\n (Hátralévő idő: "+jsonData.remainingTime+" óra)";
                learnButton.disabled = true;
                document.getElementById("cell3").appendChild(learnButton);
            }else if (jsonData.character.status == "INACTION"){
            console.log(jsonData);
                actionButton.innerHTML = "Szörnyed éppen a következő tevékenységgel foglalatoskodik: "+jsonData.nameOfSkillLearned+".\n (Hátralévő idő: "+jsonData.remainingTime+" óra)";
                actionButton.disabled = true;
                document.getElementById("cell3").appendChild(actionButton);
            }
            actionButton.addEventListener("click",function(){
                hunt(jsonData.character.id, selectDoable.value)});
            learnButton.addEventListener("click",function(){
                learn(jsonData.character.id, selectLearnable.value)});

            if (jsonData.remainingTime===-1){
                displayActionResults(jsonData.receivedFood, jsonData.receivedMoney, jsonData.nameOfSkillLearned);
            }
         }
    })
}
function displayActionResults(food, money, skill){
    var resultP=document.getElementById("results");
    if (food!==-1){
        resultP.innerHTML=`Szörnyed egy kiadós ${skill}-n van túl. Zsákmánya: ${food} élelem és ${money} rémgomb!`;
    }else{
        resultP.innerHTML=`Szörnyed elsajátította a ${skill} művészetét!`;
    }
}
function getPic(img){
    fetch(`/image/${imageId}`)
            .then(function (response) {
                return response.json()
             })
             .then(function (jsonData){
                 img.src = "data:image/jpg;base64," + jsonData.imageArray;
             });
}
function hunt(idOfChar, idOfSkill){
var request = {
    "id" : idOfChar
    };
 fetch(`/character/hunt?id=${idOfChar}&skillId=${idOfSkill}`, {
        method: "PUT"
    })
    .then(function (){
        fetchChar();
    });
}
function learn(idOfChar, idOfSkill){
var request = {
    "id" : idOfChar
    };
 fetch(`/character/learn?id=${idOfChar}&skillId=${idOfSkill}`, {
        method: "PUT"
    })
    .then(function (){
        fetchChar();
    });
}

function displaySkillsToLearn(id){
selectLearnable.innerHTML="";
fetch(`/skills/tolearn?id=${id}`)
    .then(function (response) {
          return response.json();
    })
    .then(function (jsonData) {
        for (var i = 0; i < jsonData.length; i++) {
            var moglichkeit = document.createElement("option");
            moglichkeit.value=jsonData[i].id;
            moglichkeit.innerHTML=jsonData[i].name;
            selectLearnable.appendChild(moglichkeit);

        }
    });
}
function displaySkillsToDo(id){
selectDoable.innerHTML="";
fetch(`/skills/todo?id=${id}`)
    .then(function (response) {
          return response.json();
    })
    .then(function (jsonData) {
        for (var i = 0; i < jsonData.length; i++) {
            var moglichkeit = document.createElement("option");
            moglichkeit.value=jsonData[i].id;
            moglichkeit.innerHTML=jsonData[i].name;
            selectDoable.appendChild(moglichkeit);
        }
    });
}

function displaySkillsLearned(id){
    var introText = "";
    fetch(`/skills/learned?id=${id}`)
        .then(function (response) {
              return response.json();
        })
        .then(function (jsonData) {
            if(jsonData.length===0){
                introText="Szörnyed még semmit sem tud.";
                document.getElementById("skills").innerHTML=introText;
            }else{
                introText="Szörnyed az alábbi művészetekben jeleskedik: \n\n";
                document.getElementById("skills").innerHTML=introText;
                for (var i = 0; i < jsonData.length; i++) {
                    document.getElementById("skills").innerHTML+=jsonData[i].name;
                    document.getElementById("skills").innerHTML+="\n";
                    document.getElementById("skills").innerHTML+=jsonData[i].description;
                    document.getElementById("skills").innerHTML+="\n";
                }
            }
        });
}
