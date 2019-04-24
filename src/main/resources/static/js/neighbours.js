var charId;
fetchChar();
function fetchNeigbours(){
    fetch(`/character/neighbours?id=${charId}`)
     .then(function (response) {
                  return response.json();
         })
         .then(function (jsonData) {
            var userInfoDiv = document.getElementById("user-info");
            for (var i = 0; i < jsonData.length; i++) {
                var image = document.createElement("img");
                image.setAttribute('width','200');
                image.setAttribute('height','200');
                image.id = "image";
                var table = document.createElement("table");
                table.setAttribute('class','cinereousTable');
                var imageId = jsonData[i].imageId;
                getPic(image, imageId);
                table.innerHTML = ` <tbody>
                                           <tr>
                                               <th>${jsonData[i].name}</th>
                                            </tr>
                                     </tbody>
                                     `
                userInfoDiv.appendChild(image);
                userInfoDiv.appendChild(table);
                userInfoDiv.appendChild(document.createElement("br"))
            }
         });
}

function getPic(img, imageId){
    fetch(`/image/${imageId}`)
            .then(function (response) {
                return response.json()
             })
             .then(function (jsonData){
                 img.src = "data:image/jpg;base64," + jsonData.imageArray;
             });
}
function fetchChar(){
    fetch("/character")
    .then(function (response) {
          return response.json();
    })
    .then(function (jsonData) {
    var navmenu = document.getElementById("cssmenu");
    if (jsonData.character!== null){
       charId = jsonData.character.id;
       fetchNeigbours();
       }
       });
       }