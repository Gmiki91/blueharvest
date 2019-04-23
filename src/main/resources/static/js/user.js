fetchChar();
var charName;
var charId;
function fetchChar(){
    fetch("/character")
    .then(function (response) {
          return response.json();
    })
    .then(function (jsonData) {
    var navmenu = document.getElementById("cssmenu");
    if (jsonData.character!== null){
       charName = jsonData.character.name;
       charId = jsonData.character.id;
       console.log(charId);
       navmenu.innerHTML = `<ul>
                                <li><a href="/logout" id="login"><span>Kijelentkezés</span></a></li>
                                <li><a href='mailbox.html'><span>Postaláda</span></a></li>
                                <li class='last'><a href='neighbours.html'><span>Szörnyszomszédok</span></a></li>
                                <li class='last' style='float:right'><a href="index.html"><span class='login-name' style='text-align:right'>Szia ${jsonData.character.name}!</a></span>
                            </ul>`
       } else {
       navmenu.innerHTML = `<ul>
                                <li><a href="login.html" id="login"><span>Bejelentkezés</span></a></li>
                                <li><a href='register.html'><span>Regisztráció</span></a></li>
                                <li class='last'><a href='#'><span>Semmi</span></a></li>
                            </ul>`
       }
    });
}