var charId;
fetchChar();
function fetchItems(){
    fetch(`/items/ownedBy?id=${charId}`)
     .then(function (response) {
                  return response.json();
         })
         .then(function (jsonData) {
            var shoplistDiv = document.getElementById("shop-list");
            shoplistDiv.innerHTML="";
            for (var i = 0; i < jsonData.length; i++) {
                var divDiv = document.createElement("div");
                divDiv.setAttribute('class', 'shop_item');
                var image = document.createElement("img");
                image.setAttribute('width','100');
                image.setAttribute('height','100');
                image.id = "image";
                var table = document.createElement("table");
                table.setAttribute('class','cinereousTable');
                var imageId = jsonData[i].imageId;
                getPic(image, imageId);
                table.innerHTML = ` <tbody>
                                           <tr>
                                               <td>${jsonData[i].name}<span style="font-size:14px">(x${jsonData[i].qty})</span></td>
                                               <td><button onclick="sale(${jsonData[i].id})">Elad</button> <br> <span style="font-size:14px">(${jsonData[i].price} <img src="img/gomb.png" width="20" height="20" style="vertical-align:text-bottom">)</span></td>
                                               <td><button>Használ</button></td>
                                            </tr>
                                     </tbody>`

                divDiv.appendChild(image);
                divDiv.appendChild(table);
                shoplistDiv.appendChild(divDiv)
            }
         });
}
function sale(itemId){
     fetch(`/items/addToShop?id=${itemId}&charId=${charId}`)
//      .then(function (response) {
//                     return response.json()
//                  })
                  .then(function (){
                    fetchItems();
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