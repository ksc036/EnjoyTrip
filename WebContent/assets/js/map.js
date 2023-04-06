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
//    markers = [];
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

	
//	#3. 카카오맵에서 검색결과 가져오기 Start
	//검색객체를 생성
	var ps = new kakao.maps.services.Places();  
	
	// 키워드 검색을 요청하는 함수입니다
	function searchPlaces() {

	    var keyword = document.getElementById('keyword').value;

	    if (!keyword.replace(/^\s+|\s+$/g, '')) {
	        alert('키워드를 입력해주세요!');
	        return false;
	    }

	    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
	    ps.keywordSearch( keyword, placesSearchCB); 
	}
	
	// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
	function placesSearchCB(data, status, pagination) {
	    if (status === kakao.maps.services.Status.OK) {

	        // 정상적으로 검색이 완료됐으면
	        // 검색 목록과 마커를 표출합니다
	        displayPlaces(data);

	        // 페이지 번호를 표출합니다
	        displayPagination(pagination);

	    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

	        alert('검색 결과가 존재하지 않습니다.');
	        return;

	    } else if (status === kakao.maps.services.Status.ERROR) {

	        alert('검색 결과 중 오류가 발생했습니다.');
	        return;

	    }
	}
	
	
	// 검색 결과 목록과 마커를 표출하는 함수입니다
	function displayPlaces(places) {

	    var listEl = document.getElementById('placesList'), 
	    menuEl = document.getElementById('menu_wrap'),
	    fragment = document.createDocumentFragment(), 
	    bounds = new kakao.maps.LatLngBounds(), 
	    listStr = '';
	    
	    // 검색 결과 목록에 추가된 항목들을 제거합니다
	    removeAllChildNods(listEl);

	    // 지도에 표시되고 있는 마커를 제거합니다
	    searchMarks =  removeMarker(searchMarks);
	    
	    for ( var i=0; i<places.length; i++ ) {

	        // 마커를 생성하고 지도에 표시합니다
	        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
	            marker = addMarker(placePosition, i), 
	            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

	        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	        // LatLngBounds 객체에 좌표를 추가합니다
	        bounds.extend(placePosition);

	        // 마커와 검색결과 항목에 mouseover 했을때
	        // 해당 장소에 인포윈도우에 장소명을 표시합니다
	        // mouseout 했을 때는 인포윈도우를 닫습니다
	        (function(marker, title) {
	            kakao.maps.event.addListener(marker, 'mouseover', function() {
	                displayInfowindow(marker, title);
	            });

	            kakao.maps.event.addListener(marker, 'mouseout', function() {
	                infowindow.close();
	            });

	            itemEl.onmouseover =  function () {
	                displayInfowindow(marker, title);
	            };

	            itemEl.onmouseout =  function () {
	                infowindow.close();
	            };
	        })(marker, places[i].place_name);

	        fragment.appendChild(itemEl);
	    }

	    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
	    listEl.appendChild(fragment);
	    menuEl.scrollTop = 0;

	    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	    map.setBounds(bounds);
	}
	
	// 검색결과 항목을 Element로 반환하는 함수입니다
	function getListItem(index, places) {
		
	    var el = document.createElement('li'),
	    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
	                '<div class="info">' +
	                '   <h5>' + places.place_name + '</h5>';

	    if (places.road_address_name) {
	        itemStr += '    <span>' + places.road_address_name + '</span>' +
	                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
	    } else {
	        itemStr += '    <span>' +  places.address_name  + '</span>'; 
	    }
	                 
	      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
	                '</div>';           

	    el.innerHTML = itemStr;
	    el.className = 'item';

	    return el;
	}
	
	// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
	function displayPagination(pagination) {
	    var paginationEl = document.getElementById('pagination'),
	        fragment = document.createDocumentFragment(),
	        i; 

	    // 기존에 추가된 페이지번호를 삭제합니다
	    while (paginationEl.hasChildNodes()) {
	        paginationEl.removeChild (paginationEl.lastChild);
	    }

	    for (i=1; i<=pagination.last; i++) {
	        var el = document.createElement('a');
	        el.href = "#";
	        el.innerHTML = i;

	        if (i===pagination.current) {
	            el.className = 'on';
	        } else {
	            el.onclick = (function(i) {
	                return function() {
	                    pagination.gotoPage(i);
	                }
	            })(i);
	        }

	        fragment.appendChild(el);
	    }
	    paginationEl.appendChild(fragment);
	}
	
	// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
	function addMarker(position, idx, title) {
	    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
	        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
	        imgOptions =  {
	            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
	            spriteOrigin : new kakao.maps.Point(0, (idx*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
	            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
	        },
	        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
	            marker = new kakao.maps.Marker({
	            position: position, // 마커의 위치
	            image: markerImage 
	        });

	    marker.setMap(map); // 지도 위에 마커를 표출합니다
	    searchMarks.push(marker);  // 배열에 생성된 마커를 추가합니다

	    return marker;
	}
	
	 // 검색결과 목록의 자식 Element를 제거하는 함수입니다
	function removeAllChildNods(el) {   
	    while (el.hasChildNodes()) {
	        el.removeChild (el.lastChild);
	    }
	}
	
	function displayInfowindow(marker, title) {
	    var content = '<div style="color : red; padding:5px;z-index:1;">' + title + '</div>';

	    infowindow.setContent(content);
	    infowindow.open(map, marker);
	}
//	#3. 카카오맵에서 검색결과 가져오기 End