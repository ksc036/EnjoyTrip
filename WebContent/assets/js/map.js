//마커 담을 배열
let HotPlaceMarkers = [] 
let NearByRestMarkers = [];
var searchMarks = [];

// 토글버튼
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

// #0 common Start
// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
function setMarkers(markers,map) {
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }            
}

function removeMarker(markers) {
    for ( var i = 0; i < markers.length; i++ ) {
    	markers[i].setMap(null); 
        
    }   
// markers = [];
    return [];

}

function moveAndShow(La,Lo,id,num){
	if(num == 0){
		markers = HotPlaceMarkers;
	}else{
		markers = NearByRestMarkers;
	}
    infowindow.setContent('<div style="color: black;padding:5px;font-size:12px;">' + markers[id].getTitle() + '</div>');
    infowindow.open(map, markers[id]);
	panTo(La,Lo);
	
}

// 이동할 함수
function panTo(La,Lo) {
    var moveLatLon = new kakao.maps.LatLng(La, Lo);
    map.panTo(moveLatLon);            
}  

function mkToggleContent(data, lst, markers, num){
	let str = "";
	data = data.items;
	
	for (var i = 0; i < data.length; i++) {
		displayMarker(data[i],markers);
	  let img = data[i].firstimage;
	  if (!img) {
		  img = data[i].firstimage2;
	  }
	  if (!img) {
		  img = "https://www.shutterstock.com/image-vector/photo-coming-soon-symbol-600w-161251868.jpg";
	  }
	 str += `
	<div onclick="moveAndShow(${data[i].latitude},${data[i].longitude},${i},${num});" class="card text-bg-dark" content_id=${data[i].contentid}>
      <img src="${img}" class="card-img" alt="주소정보">
      <div class="card-img-overlay">
        <h5 style="text-shadow: #666666 1px 0 10px;" class="card-title">${data[i].title}</h5>
        <p  class="card-text"><small style="text-shadow: #2e2b2b 1px 0 10px;">${data[i].tel}</small></p>
      </div>
    </div>
		`
	}
	console.log(markers);
	lst.innerHTML = str;
}

// 지도에 마커를 표시하는 함수입니다
function displayMarker(place,markers) {
    // 마커를 생성하고 지도에 표시합니다
    var marker = new kakao.maps.Marker({
        map: map,
        position: new kakao.maps.LatLng(place.latitude, place.longitude),
        title : place.title
    });
    // 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function() {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent('<div style="color: black;padding:5px;font-size:12px;">' + place.title + '</div>');
        infowindow.open(map, marker);
    });
    markers.push(marker);
}
// #0 common End


// #1맛집검색 Start
	document.getElementById("searchNearByRes").addEventListener("click", () => {

    // 맵의 경계를 가져올 수 있음 Start
        // 지도 영역정보를 얻어옵니다
        let bounds = map.getBounds();
        // 영역정보의 남서쪽 정보를 얻어옵니다
        let swLatlng = bounds.getSouthWest();
        // 영역정보의 북동쪽 정보를 얻어옵니다
        let neLatlng = bounds.getNorthEast();
	      setMarkers(HotPlaceMarkers,null);
	      NearByRestMarkers = removeMarker(NearByRestMarkers);
	      console.log(NearByRestMarkers);
	      console.log("removeMarker에서 나온직후검사 레스토랑 목록 	" );
    let query = "./trip?action=searchNearByRes";
    query+="&swLa="+swLatlng.Ma;
    query+="&swLo="+swLatlng.La;
    
    query+="&neLa="+neLatlng.Ma;
    query+="&neLo="+neLatlng.La;
    
    const foodGroupLst = document.getElementById("foodGroupLst");
    fetch(query, { method: "GET" })
    .then((response) => response.json())
    .then((data) => {
    	// 토글만들기 data와 연결할 내용 주기.
    	mkToggleContent(data,foodGroupLst,NearByRestMarkers,document.getElementById("searchNearByRes").getAttribute("num"));

  });
  })


	
	// #1맛집검색 End
	
	// #2핫플검색 Start
	  document
	    .getElementById("searchNearByHotPlace")
	    .addEventListener("click", () => {
	      let bounds = map.getBounds();
	      // 영역정보의 남서쪽 정보를 얻어옵니다
	      let swLatlng = bounds.getSouthWest();
	      // 영역정보의 북동쪽 정보를 얻어옵니다
	      let neLatlng = bounds.getNorthEast();

	      setMarkers(NearByRestMarkers,null);
	      HotPlaceMarkers = removeMarker(HotPlaceMarkers);
	      console.log("removeMarker에서 나온직후검사");
	      console.log(HotPlaceMarkers);
	      let query = "./trip?action=searchNearByHotPlace";
	      query += "&swLa=" + swLatlng.Ma;
	      query += "&swLo=" + swLatlng.La;

	      query += "&neLa=" + neLatlng.Ma;
	      query += "&neLo=" + neLatlng.La;

	      const HotPlaceLst = document.getElementById("HotPlaceLst");

	      fetch(query, { method: "GET" })
	        .then((response) => response.json())
	        .then((data) => {
	            mkToggleContent(data,HotPlaceLst,HotPlaceMarkers,document.getElementById("searchNearByHotPlace").getAttribute("num"))
	        });
	    });
	// #2핫플검색 End

	
// #3. 카카오맵에서 검색결과 가져오기 Start
	// 검색객체를 생성부분 삭제
	
	// 키워드 검색을 요청하는 함수입니다
	function searchPlaces() {
	    var keyword = document.getElementById('keyword').value;
	    if (!keyword.replace(/^\s+|\s+$/g, '')) {
	        alert('키워드를 입력해주세요!');
	        return false;
	    }
	    let query =  "./trip?action=searchByKeyWordInMap&keyword=" + keyword;
	    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
	     fetch(query, { method: "GET" })
	        .then((response) => response.json())
	        .then((data) => {
	        	placesSearch(data);
	        });
	}
	
	// 장소검색이 완료됐을 때 호출되는 함수 입니다
	function placesSearch(data) {
		console.log(data);
	    if (data.items.length == 0) {
	        alert('검색 결과가 존재하지 않습니다.');
	        return;
	    } else{
	        // 정상적으로 검색이 완료됐으면 지도를 조정합니다.
	        displayPlaces(data.items);
	    } 
	}
	
	// 검색 결과 목록과 마커를 표출하는 함수입니다
	function displayPlaces(places) {

	    bounds = new kakao.maps.LatLngBounds();
	    for ( var i=0; i<places.length; i++ ) {

	        // 마커를 생성하고 지도에 표시합니다
	        var placePosition = new kakao.maps.LatLng(places[i].latitude, places[i].longitude);
	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        bounds.extend(placePosition);
	        
	    map.setBounds(bounds);
	}
	}

// #3. 카카오맵에서 검색결과 가져오기 End
