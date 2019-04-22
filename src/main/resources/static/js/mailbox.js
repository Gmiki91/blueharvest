window.onload=fetchChar;
var writeBtn = document.getElementById("writeBtn");
var readBtn = document.getElementById("readBtn");
var sendMsg = document.getElementById("send-message");
var span = document.getElementsByClassName("close")[0];
var modal = document.getElementById('myModal');
writeBtn.addEventListener("click", write);
readBtn.addEventListener("click", read);
sendMsg.addEventListener("click", sendMessage);
var fromId;

function write(){
 modal.style.display = "block";
}

function sendMessage(){
 var toChar = document.getElementById("to-char").value;
 var subject = document.getElementById("subject").value;
 var content = document.getElementById("content").value;

 var request = {
    "to": toChar,
    "from":fromId,
    "subject":subject,
    "content":content
    };
 fetch('/message/new', {
            method: "POST",
            body: JSON.stringify(request),
            headers:{
            "Content-type": "application/json"
            }
        })
        .then(function (response){
        alert("messaged sent.")
        modal.style.display = "none";
        });
 }

function read(){
    fetch("/message/inbox")
    .then(function (response) {
              return response.json();
     })
     .then(function (jsonData) {

     });
 }

span.onclick = function() {
  modal.style.display = "none";
}

function fetchChar(){
    fetch("/character")
    .then(function (response) {
          return response.json();
    })
    .then(function (jsonData) {
       var logkibe = document.getElementById("login");
                   logkibe.innerHTML = "Kijelentkezés";
                   logkibe.href = "/logout";
    });
}