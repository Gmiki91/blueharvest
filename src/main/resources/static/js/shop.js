var charId;
fetchChar();
function fetchItems(){
    fetch(`/items`)
     .then(function (response) {
                  return response.json();
         })
         .then(function (jsonData) {
            var shoplistDiv = document.getElementById("shop-list");
            for (var i = 0; i < jsonData.length; i++) {
                var image = document.createElement("img");
                image.setAttribute('width','100');
                image.setAttribute('height','100');
                image.id = "image";
                var table = document.createElement("table");
                table.setAttribute('class','cinereousTable');
                table.setAttribute('style','display:inline-block');
                table.setAttribute('style', 'width:30%');
                var imageId = jsonData[i].imageId;
                getPic(image, imageId);
                table.innerHTML = ` <tbody>
                                           <tr>
                                               <td>${jsonData[i].name}</td>
                                               <td title="Rémgomb">${jsonData[i].price} <img src="img/gomb.png"></th>
                                               <td><button>Buy!</button></td>
                                            </tr>
                                     </tbody>
                                     `
                shoplistDiv.appendChild(image);
                shoplistDiv.appendChild(table);
                shoplistDiv.appendChild(document.createElement("br"))
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
       fetchItems();
       }
       });
       }