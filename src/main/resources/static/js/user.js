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
       navmenu.innerHTML = `<ul>
                                <li><a href="index.html"><span class='login-name' style='text-align:right'>Szia ${jsonData.character.name}!</a></span>
                                <li><a href='mailbox.html'><span>Postaláda</span></a></li>
                                <li><li><a href='shop.html'><span>Bolt</span></a></li>
                                <li class='last'><a href='neighbours.html'><span>Szörnyszomszédok</span></a></li>
                                <li class='last'  style='float:right'><a href="/logout" id="login"><span>Kijelentkezés</span></a></li>
                            </ul>`
       } else {
       navmenu.innerHTML = `<ul>
                                <li style='display:inline-block'><a href="login.html" id="login"><span>Bejelentkezés</span></a></li>
                                <li class='last' style='display:inline-block'><a href='register.html'><span>Regisztráció</span></a></li>
                            </ul>`
       }
    });
}