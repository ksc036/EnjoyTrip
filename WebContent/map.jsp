<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="ko">

<head>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=65b405d8da6e784c539c45cf5f134a85&libraries=services"></script>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<meta charset="utf-8" />
<meta content="width=device-width, initial-scale=1.0" name="viewport" />

<title>Enjoy Trip</title>
<meta content="" name="description" />
<meta content="" name="keywords" />
<!-- ajax를 위해 jquery 사용 -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- Favicons -->
<link href="assets/img/favicon.png" rel="icon" />
<link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon" />

<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Cardo:ital,wght@0,400;0,700;1,400&display=swap"
	rel="stylesheet" />

<!-- Vendor CSS Files -->
<link href="assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet" />
<link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet" />
<link href="assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet" />
<link href="assets/vendor/aos/aos.css" rel="stylesheet" />

<!-- Template Main CSS File -->
<link href="assets/css/main.css" rel="stylesheet" />
<style>
.map_wrap {
	margin-top: 100px;
	position: relative;
	width: 100%;
	height: 800px;
}

.map_warp * {
	color: black;
}

.title {
	font-weight: bold;
	display: block;
}

.hAddr {
	position: absolute;
	left: 10px;
	top: 10px;
	border-radius: 2px;
	background: #fff;
	background: rgba(255, 255, 255, 0.8);
	z-index: 1;
	padding: 5px;
	color: black;
}

#centerAddr {
	display: block;
	margin-top: 2px;
	font-weight: normal;
}

.bAddr {
	padding: 5px;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}

#centerAddr, .title, bAddr>div {
	color: black;
}
/* css 넣기 start */
.map_wrap, .map_wrap * {margin:0;padding:0;font-family:'Malgun Gothic',dotum,'돋움',sans-serif;font-size:12px;}
.map_wrap a, .map_wrap a:hover, .map_wrap a:active{color:#000;text-decoration: none;}

#menu_wrap {position:absolute;top:0;right:0;bottom:0;width:250px;margin-top : 100px ;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}

#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}

#placesList li {list-style: none;}
#placesList .item {position:relative;border-bottom:1px solid #888;overflow: hidden;cursor: pointer;min-height: 65px;}
#placesList .item span {display: block;margin-top:4px;}
#placesList .item h5, #placesList .item .info {text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .info .jibun {padding-left:26px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;}
#placesList .info .tel {color:#009900;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
#pagination {margin:10px auto;text-align: center;}
#pagination a {display:inline-block;margin-right:10px;}
#pagination .on {font-weight: bold; cursor: default;color:#777;}

#menu_wrap ul >*{
	color: black;
}
</style>



</head>

<body>
	<!-- ======= Header ======= -->
	<header id="header" class="header d-flex align-items-center fixed-top">
		<%@ include file="/include/nav.jsp"%>
	</header>
	<!-- End Header -->


	<!-- Map띄우기 -->
	<div class="map_wrap">
		<div id="map"
			style="width: 100%; height: 100%; position: relative; overflow: hidden;"></div>
		<div class="hAddr">
			<span class="title">지도중심기준 행정동 주소정보</span> <span id="centerAddr"></span>
		</div>
		<!-- 검색리스트 start -->
		<hr>
		<div id="menu_wrap">
		<ul id="placesList"></ul>
		<div id="pagination"></div>
		</div>
		<!-- 검색리스트end -->

	</div>
	<!-- Map띄우기 END -->

	<a href="#"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<div id="preloader">
		<div class="line"></div>
	</div>

	<!-- 로그인 모달창 start -->
	<%@ include file="/include/loginmodal.jsp"%>
	<!-- 로그인 모달 창 end -->

	<!-- if문 써서 값있으면 그값으로 넣어주고 없으면 기본값으로 넣어주면되곘다 -->
	<!-- Map Script Start -->
	<script>

		var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
		infowindow = new kakao.maps.InfoWindow({
			zindex : 1
		}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다
		let latitude = 37.498004414546934;
		let longitude = 127.02770621963765;
		let title = "강남역";
		
		<c:if test="${longitude != null and latitude !=null}">
		   	latitude = ${latitude};
		   	longitude = ${longitude}; 
		   	title = "${title}";
		</c:if>


		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표
			level : 5
		// 지도의 확대 레벨
		};

		// 지도를 생성합니다
		var map = new kakao.maps.Map(mapContainer, mapOption);

		//마커 따로생성 Start 
		// 마커가 표시될 위치입니다 
		var markerPosition = new kakao.maps.LatLng(latitude, longitude);

		// 마커를 생성합니다
		var fmarker = new kakao.maps.Marker({
			position : markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		fmarker.setMap(map);
		var iwContent = '<div style="padding:5px;color:black">강남역<br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">주변맛집</a> <a href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">길찾기</a></div>'; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
		<c:if test="${title != null}">
	   		iwContent = '<div style="padding:5px;color:black">${title}<br><a href="https://map.kakao.com/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">주변맛집</a> <a href="https://map.kakao.com/link/to/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다 
		</c:if>
		iwPosition = new kakao.maps.LatLng(latitude, longitude); //인포윈도우 표시 위치입니다

		// 인포윈도우를 생성합니다
		var finfowindow = new kakao.maps.InfoWindow({
			position : iwPosition,
			content : iwContent
		});

		// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
		finfowindow.open(map, fmarker);

		// 맵의 경계를 가져올 수 있음  Start
		// 지도 영역정보를 얻어옵니다 
		var bounds = map.getBounds();

		// 영역정보의 남서쪽 정보를 얻어옵니다 
		var swLatlng = bounds.getSouthWest();
		// 영역정보의 북동쪽 정보를 얻어옵니다 
		var neLatlng = bounds.getNorthEast();

		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();

		// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
		searchAddrFromCoords(map.getCenter(), displayCenterInfo);

		// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
		kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
			finfowindow.close();
			fmarker.setMap(null);
			searchDetailAddrFromCoords(mouseEvent.latLng, function(result,
					status) {
				if (status === kakao.maps.services.Status.OK) {
					var detailAddr = !!result[0].road_address ? '<div>도로명주소 : '
							+ result[0].road_address.address_name + '</div>'
							: '';
					detailAddr += '<div style="color:black">지번 주소 : '
							+ result[0].address.address_name + '</div>';

					var content = '<div class="bAddr">'
							+ '<span class="title">법정동 주소정보</span>'
							+ detailAddr + '</div>';

					// 마커를 클릭한 위치에 표시합니다 
					marker.setPosition(mouseEvent.latLng);
					marker.setMap(map);

					// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
					infowindow.setContent(content);
					infowindow.open(map, marker);
				}
			});
		});

		// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
		kakao.maps.event.addListener(map, 'idle', function() {
			searchAddrFromCoords(map.getCenter(), displayCenterInfo);
		});

		function searchAddrFromCoords(coords, callback) {
			// 좌표로 행정동 주소 정보를 요청합니다
			geocoder.coord2RegionCode(coords.getLng(), coords.getLat(),
					callback);
		}

		function searchDetailAddrFromCoords(coords, callback) {
			// 좌표로 법정동 상세 주소 정보를 요청합니다
			geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
		}

		// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
		function displayCenterInfo(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				var infoDiv = document.getElementById('centerAddr');
				for (var i = 0; i < result.length; i++) {
					// 행정동의 region_type 값은 'H' 이므로
					if (result[i].region_type === 'H') {
						infoDiv.innerHTML = result[i].address_name;
						break;
					}
				}
			}
		}
	</script>
	<!-- Map Script End -->

	<button class="btn btn-secondary btn-sm" data-bs-toggle="offcanvas"
		data-bs-target="#offcanvasScrolling" id="test"
		style="position: fixed; top: 50%; left: 10; z-index: 2;">></button>
	<!-- Map Script End -->

	<!-- 토글창 start-->
	<div class="offcanvas offcanvas-start text-bg-dark"
		data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1"
		id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">

		<!-- 토글 헤더 Start -->
		<div style="background-color: #666666; height: 150px;">
			<h1 class="fw-bold">Enjoy Trip</h1>
			<input id= "keyword" type="text"><button onClick="searchPlaces();"> <i class="bi bi-airplane"></i></button>
			<div style="display: flex; justify-content: space-around;">
				<button id="searchBtn" type="button"
					class="btn btn-danger toggleInBtn" num="0">HOTPlace</button>

				<button id="likeBtn" type="button"
					class="btn btn-danger toggleInBtn" num="1">좋아요</button>
				<button id="routeBtn" type="button"
					class="btn btn-primary toggleInBtn" num="2">경로짜기</button>
				<button id="foodBtn" type="button"
					class="btn btn-primary toggleInBtn" num="3">주변맛집</button>
			</div>
		</div>

		<!-- 토글 헤더 end -->

		<!-- 토글 바디 start -->
		<div class="offcanvas-body">

			<div id="searchGroup" class="list-group toggleInGroup"
				style="display: none;" num="0">
				<button id="searchNearByHotPlace" type="button" num="0">실시간
					주변 핫플 검색</button>
				<div id="HotPlaceLst"></div>
			</div>


			<div id="likeGroup" class="list-group toggleInGroup"
				style="display: none;" num="1">like</div>

			<div id="routeGroup" class="list-group toggleInGroup"
				style="display: none;" num="2">route</div>

			<div id="foodGroup" class="list-group toggleInGroup"
				style="display: none;" num="3">
				<button num="3" id="searchNearByRes" type="button">주변 음식점
					검색</button>
				<div id="foodGroupLst"></div>
			</div>

		</div>
		<!-- 토글 바디 end -->

		<!-- close BTN -->
		<button class="btn btn-secondary btn-sm" data-bs-toggle="offcanvas"
			data-bs-target="#offcanvasScrolling" id="test"
			style="position: absolute; top: 50%; right: 0; z-index: 10;">
			<</button>

	</div>
	<!-- 토글창 end -->



	<!-- ======= Footer ======= -->
	<footer id="footer" class="footer">
		<div class="container">
			<div class="copyright">
				© Copyright <strong><span>The Most PowerFul</span></strong>. All
				Rights Reserved
			</div>
			<div class="credits">
				<!-- All the links in the footer should remain intact. -->
				<!-- You can delete the links only if you purchased the pro version. -->
				<!-- Licensing information: https://bootstrapmade.com/license/ -->
				<!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/photofolio-bootstrap-photography-website-template/ -->
				Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
			</div>
		</div>
	</footer>
	<!-- End Footer -->

	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="assets/vendor/aos/aos.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
	<script src="assets/js/common.js"></script>
	<!-- 토글js -->
	<script src="assets/js/map.js"></script>

</body>

</html>