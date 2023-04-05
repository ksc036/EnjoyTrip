
//마커 답을 배열
let markers = [];


//토글버튼
const toggleInBtn = document.getElementsByClassName("toggleInBtn");
for (let i = 0; i < toggleInBtn.length; i++) {
  toggleInBtn[i].addEventListener("click", (event) => {
    const toggleInGroup = document.getElementsByClassName("toggleInGroup");
    for (let j = 0; j < toggleInGroup.length; j++) {
      let str =
        event.target.getAttribute("num") === toggleInGroup[j].getAttribute("num") ? "" : "none";
      str = "display : " + str;
      toggleInGroup[j].setAttribute("style", str);
    }
  });
}

	document.getElementById("searchNearByRes").addEventListener("click", () => {

    // 맵의 경계를 가져올 수 있음 Start
        // 지도 영역정보를 얻어옵니다
        var bounds = map.getBounds();
        // 영역정보의 남서쪽 정보를 얻어옵니다
        var swLatlng = bounds.getSouthWest();
        // 영역정보의 북동쪽 정보를 얻어옵니다
        var neLatlng = bounds.getNorthEast();
        
        removeMarker();
        
    let query = "./trip?action=searchNearByRes";
    query+="&swLa="+swLatlng.Ma;
    query+="&swLo="+swLatlng.La;
    
    query+="&neLa="+neLatlng.Ma;
    query+="&neLo="+neLatlng.La;
    
    const foodGroupLst = document.getElementById("foodGroupLst");
    fetch(query, { method: "GET" })
    .then((response) => response.json())
    .then((data) => {
    	let str = "";
    	data = data.items;
    	for (var i = 0; i < data.length; i++) {
    		displayMarker(data[i]);    
		  let img = data[i].firstimage;
		  if (!img) {
			  img = data[i].firstimage2;
		  }
		  if (!img) {
			  img = "https://www.shutterstock.com/image-vector/photo-coming-soon-symbol-600w-161251868.jpg";
		  }
    	 str += `
    	<div onclick="panTo(${data[i].latitude},${data[i].longitude});" class="card text-bg-dark" content_id=${data[i].contentid}>
          <img src="${img}" class="card-img" alt="주소정보">
          <div class="card-img-overlay">
            <h5 style="text-shadow: #666666 1px 0 10px;" class="card-title">${data[i].title}</h5>
            <p  class="card-text"><small style="text-shadow: #2e2b2b 1px 0 10px;">${data[i].tel}</small></p>
          </div>
        </div>
    		`
		}
    	console.log(data);
    	foodGroupLst.innerHTML = str;
  });
  })
  
// 지도에 마커를 표시하는 함수입니다
function displayMarker(place) {
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.latitude, place.longitude) 
    });
    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="color: black;padding:5px;font-size:12px;">' + place.title + '</div>');
        infowindow.open(map, marker);
    });
    markers.push(marker);
}

	function removeMarker() {
	    for ( var i = 0; i < markers.length; i++ ) {
	    	markers[i].setMap(null); //왜 markers못잡지?
	        
	    }   
	    markers = [];
	}
	
	
	// 이동할 함수
	function panTo(La,Lo) {
	    var moveLatLon = new kakao.maps.LatLng(La, Lo);
	    map.panTo(moveLatLon);            
	}  

 