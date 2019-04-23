fetchNeigbours();
function fetchNeigbours(){
    fetch(`/character/neighbours?id=1`)
     .then(function (response) {
                  return response.json();
         })
         .then(function (jsonData) {
            var userInfoDiv = document.getElementById("user-info");
            for (var i = 0; i < jsonData.length; i++) {
                var image = document.createElement("img");
                image.id = "image";
                var table = document.createElement("table");
                table.class = "cinereousTable";
                var imageId = jsonData.imageId;
                getPic(image, imageId);
                table.innerHtml = ` <thead>
                                           <tr>
                                               <th>${jsonData.name}</th>
                                            </tr>
                                     </thead>
                                     `
                userInfoDiv.appendChild(image);
                userInfoDiv.appendChild(table);
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