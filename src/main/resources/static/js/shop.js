var charId;
var charMoney;
fetchChar();
function fetchItems(){
    fetch(`/items`)
     .then(function (response) {
                  return response.json();
         })
         .then(function (jsonData) {
            var shoplistDiv = document.getElementById("shop-list");
            shoplistDiv.innerHTML = "";
            for (var i = 0; i < jsonData.length; i++) {
                var sellable = "";
                if (jsonData[i].qty ===0 || charMoney < jsonData[i].price){
                    sellable = "disabled";
                 }
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
                                               <td>${jsonData[i].name}<span style="font-size:14px">(x${jsonData[i].qty})<span></td>
                                               <td>${jsonData[i].price} <img src="img/gomb.png"  style="vertical-align:text-bottom"></td>
                                               <td><button ${sellable} onclick="buy(${jsonData[i].id}, ${jsonData[i].price})">Buy!</button></td>
                                            </tr>
                                     </tbody>`
                divDiv.appendChild(image);
                divDiv.appendChild(table);
                shoplistDiv.appendChild(divDiv)
            }
         });
}
function buy(itemId, price){
 fetch(`/items/removeFromShop?id=${itemId}&price=${price}&charId=${charId}`)
//      .then(function (response) {
//                     return response.json()
//                  })
                  .then(function (){
                    fetchChar();
                  })
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
    if (jsonData.character!== null){
       charId = jsonData.character.id;
       charMoney= jsonData.character.money;
       fetchItems();
       }
       });
       }