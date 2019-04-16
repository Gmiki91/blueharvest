var userInfo = document.getElementById("user-info");
userInfo.style.display="none";
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

            document.getElementById("column-name").innerHTML=jsonData.name;

            imageId = jsonData.imageId;
            var img = document.getElementById("image");
            getPic(img);
            img.setAttribute('width','200');
            img.setAttribute('height','200');

            document.getElementById("cell1").innerHTML=jsonData.food;
            document.getElementById("cell2").innerHTML=jsonData.money;

            var huntButton = document.createElement("button");
            if (jsonData.status == "AVAILABLE"){
                huntButton.innerHTML="Vadászat!";
            }else if (jsonData.status == "HUNTING"){
                huntButton.innerHTML="Szörnyed éppen vadászik."
                huntButton.disabled = true;
            }
            huntButton.addEventListener("click",function(){
                hunt(jsonData.id)});

            document.getElementById("cell3").innerHTML="";
            document.getElementById("cell3").appendChild(huntButton);
         }
    })
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