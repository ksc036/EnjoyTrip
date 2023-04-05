<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
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

</head>

<body>
	<!-- ======= Header ======= -->
	<header id="header" class="header d-flex align-items-center fixed-top">

		<%@ include file="/include/nav.jsp"%>

	</header>

	<!-- End Header -->

	<!-- ======= Hero Section ======= -->
	<section id="hero"
		class="hero d-flex flex-column justify-content-center align-items-center"
		data-aos="fade" data-aos-delay="1500">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-lg-6 text-center">
					<h2>
						<span>Enjoy</span> Your Trip
					</h2>
					<p>Enjoy Trip과 함께 떠나보아요</p>
					<!-- href="contact.html" -->
					<button type="button" class="btn-get-started"
						data-bs-toggle="modal" data-bs-target="#searchModal">여행지
						검색하기</button>

				</div>
			</div>
		</div>

	</section>
	<!-- End Hero Section -->
	<!-- Full screen modal -->
	<!-- <div id="searchModal" class="modal-dialog modal-fullscreen-sm-down">
      ...
    </div> -->
	<!-- ======= Gallery Section ======= -->
	<main id="main" data-aos="fade" data-aos-delay="1500">
	<section id="gallery" class="gallery">
		<div class="container-fluid">
			<!-- 갤러리 만드는 곳 -->
			<div id="pictureLst" class="row gy-4 justify-content-center"></div>
		</div>
	</section>

	<!-- End Gallery Section --> </main>
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<footer id="footer" class="footer">
		<div class="container">
			<div class="copyright">
				&copy; Copyright <strong><span>The Most PowerFul</span></strong>.
				All Rights Reserved
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

	<a href="#"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<div id="preloader">
		<div class="line"></div>
	</div>

	<!-- Vendor JS Files -->
	<script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="assets/vendor/aos/aos.js"></script>
	<script src="assets/vendor/php-email-form/validate.js"></script>

	<!-- 로그인 모달창 start -->

	<%@ include file="/include/loginmodal.jsp"%>
	<!-- 로그인 모달 창 end -->

	<!-- 여행지검색 모달창 start -->
	<div class="modal fade" id="searchModal" data-bs-backdrop="static"
		data-bs-keyboard="false" aria-labelledby="staticBackdropLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">
						<i class="bi bi-airplane-fill text-info">여행지 검색</i>
						<!-- <i class="bi bi-chat-left-dots-fill text-info">
                  설문조사 만들기</i
                > -->
					</h4>
					<button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				</div>

				<div class="modal-body">
					<form onsubmit="return false" action="">
						<div class="row mb-3 mt-3">
							<div class="col-md-6">
								<i class="bi bi-balloon-fill text-primary"></i> <select
									id="search-area" class="form-select"
									aria-label="Default select example">
									<option value="0" selected>검색 할 지역 선택</option>
								</select>
							</div>
							<div class="col-md-6">
								<i class="bi bi-balloon-fill text-primary text-danger"></i> <select
									id="search-content-id" class="form-select"
									aria-label="Default select example">
									<option value="0" selected>관광지 유형</option>
									<option value="12">관광지</option>
									<option value="14">문화시설</option>
									<option value="15">축제공연행사</option>
									<option value="25">여행코스</option>
									<option value="28">레포츠</option>
									<option value="32">숙박</option>
									<option value="38">쇼핑</option>
									<option value="39">음식점</option>
								</select>
							</div>
						</div>
						<input id="search-keyword" class="form-control me-2" type="search"
							placeholder="검색어" aria-label="검색어" />
					</form>
				</div>

				<div class="modal-footer">
					<button id="btn-search" class="btn btn-outline-success btn-sm"
						type="submit">검색</button>
					<button id="btn-close" type="button"
						class="btn btn-outline-danger btn-sm" data-bs-dismiss="modal">
						Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 여행지검색 모달창end -->

	<!-- 자세히 보기 모달창 start -->
	<div class="modal fade" id="viewDetail" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title text-body fs-5 text-primary"
						id="viewDetailTitle"></h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div id="viewDetailBodyImage"></div>
					<div class="text-body" id="viewDetailDescription"></div>
					<div class="text-body" id="viewDetailBodyAddress"></div>
					<div class="text-body" id="viewDetailBodyTel"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">상세보기</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 자세히 보기 모달창 end -->
	<!-- Template Main JS File -->
	<script src="assets/js/main.js"></script>
	<script src="assets/js/home.js"></script>
	<script src="assets/js/common.js"></script>



</body>

</html>