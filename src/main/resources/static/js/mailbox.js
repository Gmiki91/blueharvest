var writeBtn = document.getElementById("writeBtn");
var sendMsg = document.getElementById("send-message");
var inboxBtn = document.getElementById("inboxBtn");
var outboxBtn = document.getElementById("outboxBtn");
var span = document.getElementsByClassName("close")[0];
var modal = document.getElementById('myModal');
writeBtn.addEventListener("click", write);
sendMsg.addEventListener("click", sendMessage);
outboxBtn.addEventListener("click", readOutbox);
inboxBtn.addEventListener("click",readInbox);

function write(){
 modal.style.display = "block";
}

function sendMessage(){
 var toChar = document.getElementById("to-char").value;
 var subject = document.getElementById("subject").value;
 var content = document.getElementById("content").value;
 var request = {
    "to": toChar,
    "from":charName,
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

function readOutbox(){
    fetch(`/message/outbox?fromName=${charName}`)
    .then(function (response) {
              return response.json();
     })
     .then(function (jsonData) {
        var tbody = document.getElementById("mails-tbody");
        tbody.innerHTML = "";
        if (jsonData.length===0){
            tbody.innerHTML = "Nincsenek elküldött üzeneteid!"
        }else{
            for (var i = 0; i < jsonData.length; i++) {
                var trTo = document.createElement("tr");
                var tdTo = document.createElement("td");
                tdTo.innerHTML = "Kinek: "+ jsonData[i].to;
                tdTo.setAttribute("style","text-align:left");
                trTo.appendChild(tdTo);

                var trSubject = document.createElement("tr");
                var tdSubject = document.createElement("td");
                tdSubject.innerHTML ="Tárgy: "+ jsonData[i].subject;
                tdSubject.setAttribute("style","text-align:left");
                trSubject.appendChild(tdSubject);

                var trTime = document.createElement("tr");
                var tdTime = document.createElement("td");
                tdTime.innerHTML ="Küldés ideje: "+ jsonData[i].timeSent.replace("T"," ");;
                tdTime.setAttribute("style","text-align:left");
                trTime.appendChild(tdTime);

                var trContent = document.createElement("tr");
                var tdContent = document.createElement("td");
                tdContent.innerHTML = jsonData[i].content;
                tdContent.setAttribute("style","text-align:left");
                trContent.appendChild(tdContent);

                tbody.appendChild(trTo);
                tbody.appendChild(trTime);
                tbody.appendChild(trSubject);
                tbody.appendChild(trContent);
                tbody.appendChild(document.createElement("br"));
            }
          }
     });
 }

function readInbox(){
    fetch(`/message/inbox?toName=${charName}`)
    .then(function (response) {
              return response.json();
     })
     .then(function (jsonData) {
        var tbody = document.getElementById("mails-tbody");
        tbody.innerHTML = "";
         if (jsonData.length===0){
               tbody.innerHTML = "Nincsenek üzeneteid!"
         }else{
            for (var i = 0; i < jsonData.length; i++) {
                var trFrom = document.createElement("tr");
                var tdFrom = document.createElement("td");
                tdFrom.innerHTML ="Kitől: " +jsonData[i].from;
                tdFrom.setAttribute("style","text-align:left");
                trFrom.appendChild(tdFrom);

                var trSubject = document.createElement("tr");
                var tdSubject = document.createElement("td");
                tdSubject.innerHTML ="Tárgy: "+ jsonData[i].subject;
                tdSubject.setAttribute("style","text-align:left");
                trSubject.appendChild(tdSubject);

                var trTime = document.createElement("tr");
                var tdTime = document.createElement("td");
                tdTime.innerHTML ="Küldés ideje: "+ jsonData[i].timeSent.replace("T"," ");
                tdTime.setAttribute("style","text-align:left");
                trTime.appendChild(tdTime);

                var trContent = document.createElement("tr");
                var tdContent = document.createElement("td");
                tdContent.innerHTML = jsonData[i].content;
                tdContent.setAttribute("style","text-align:left");
                trContent.appendChild(tdContent);

                tbody.appendChild(trFrom);
                tbody.appendChild(trTime);
                tbody.appendChild(trSubject);
                tbody.appendChild(trContent);
                tbody.appendChild(document.createElement("br"));
            }
            }
     });
 }


span.onclick = function() {
  modal.style.display = "none";
}
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}