document.getElementById("create-char").addEventListener("click", handleCreateChar);

function handleCreateChar(){
    var name = document.getElementById("input-name").value;
    var request = {
        "name": name
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