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

            document.getElementById("cell1").innerHTML="Étel: "+jsonData.food;

            var huntButton = document.createElement("button");
            huntButton.innerHTML="Vadászat!";
            huntButton.addEventListener("click",function(){
                hunt(jsonData.id)})
            document.getElementById("cell2").appendChild(huntButton);
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
    console.log(idOfChar);
}