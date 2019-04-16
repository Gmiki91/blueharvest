var userInfo = document.getElementById("user-info");
var details = document.querySelector(".details");
userInfo.style.display="none";
details.style.display="none";

window.onload=fetchChar;
var imageId;

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

            document.getElementById("column-name").innerHTML=jsonData.character.name;

            imageId = jsonData.character.imageId;
            var img = document.getElementById("image");
            getPic(img);
            img.setAttribute('width','200');
            img.setAttribute('height','200');

            document.getElementById("cell1").innerHTML=jsonData.character.food;
            document.getElementById("cell2").innerHTML=jsonData.character.money;

            var huntButton = document.createElement("button");
            if (jsonData.character.status == "AVAILABLE"){
                huntButton.innerHTML="Vadászat!";
            }else if (jsonData.character.status == "HUNTING"){
                huntButton.innerHTML="Szörnyed éppen vadászik.\n (Hátralévő idő: "+jsonData.remainingTime+" óra)";
                huntButton.disabled = true;
            }
            huntButton.addEventListener("click",function(){
                hunt(jsonData.character.id)});

            document.getElementById("cell3").innerHTML="";
            document.getElementById("cell3").appendChild(huntButton);

            if (jsonData.receivedFood!=-1){
                displayActionResults(jsonData.receivedFood, jsonData.receivedMoney);
            }
         }
    })
}
function displayActionResults(food, money){
    details.style.display="block";
    var resultP=document.getElementById("results");
    resultP.innerHTML=`Szörnyed visszatért küldetéséről. Zsákmánya: ${food} élelem és ${money} rémgomb!`
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
function hunt(idOfChar){
var request = {
    "id" : idOfChar
    };
 fetch(`/character?id=${idOfChar}`, {
        method: "PUT"
    })
    .then(function (){
        fetchChar();
    });
}