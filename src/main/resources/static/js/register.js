document.getElementById("create-char").addEventListener("click", handleCreateChar);
document.getElementById("next-pic").addEventListener("click", handleNextPic);
document.getElementById("previous-pic").addEventListener("click",handlePreviousPic);
var nrOfImages ;
var id=1;
numberOfImages();
window.onload=getPic;

function numberOfImages(){
    fetch("/image/size")
    .then(function(response){
        return response.json();
     })
     .then(function(jsonData){
        nrOfImages = jsonData;
     });
}

function handleCreateChar(){
    var name = document.getElementById("input-name").value;
    var password1 = document.getElementById("password-input").value;
    var password2 = document.getElementById("password-again-input").value;
    if (password2!==password1){
        alert("A jelszavak nem egyeznek meg!");
    }
    var request = {
        "name": name,
        "password":password1,
        "imageId": id
    };
    fetch('/character', {
        method: "POST",
        body: JSON.stringify(request),
        headers:{
        "Content-type": "application/json"
        }
    })
    .then(function (response){
        return response.json();
    })
    .then(function (jsonData){
        alert(jsonData.messages);
     });
     return false;
}
function getPic(){
document.getElementById("pic").innerHTML = "";
    fetch(`/image/${id}`)
        .then(function (response) {
            return response.json()
         })
         .then(function (jsonData){
            var img = document.createElement('img');
            img.class="image";
            img.setAttribute('width','200');
            img.setAttribute('height','200');
            img.src =  "data:image/jpg;base64," + jsonData.imageArray;1
            document.getElementById("pic").appendChild(img)})
            if (id!=nrOfImages){
                document.getElementById("next-pic").disabled=false;
            }
            if (id!=1){
                document.getElementById("previous-pic").disabled=false;
            }
    }

function handleNextPic(){
    id++;
    getPic();
    if (id == nrOfImages){
        document.getElementById("next-pic").disabled=true;
    }
}
function handlePreviousPic(){
    id--;
    getPic();
    if (id == 1){
        document.getElementById("previous-pic").disabled=true;
    }
}