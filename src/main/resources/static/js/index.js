window.onload=fetchChar;

function fetchChar(){
    fetch("/character")
    .then(function (response) {
          return response.json();
    })
    .then(function (jsonData) {
         console.log(jsonData);
         if (jsonData!=null){
            var main = document.getElementById("user-info");
            var name = jsonData.name;
            main.innerHTML=name;
            var logkibe = document.getElementById("login");
            logkibe.innerHTML = "Kijelentkezés";
            logkibe.href = "/logout";
            alert("jee");
         } else {
            alert("ajjajj")
         }
    })
}