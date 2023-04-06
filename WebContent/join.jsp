z<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}"/>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>Join</title>
  <meta content="" name="description">
  <meta content="" name="keywords">
    <!-- ajax를 위해 jquery 사용 -->
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link
    href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Cardo:ital,wght@0,400;0,700;1,400&display=swap"
    rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/main.css" rel="stylesheet">

  <!-- =======================================================
  * Template Name: PhotoFolio
  * Updated: Mar 10 2023 with Bootstrap v5.2.3
  * Template URL: https://bootstrapmade.com/photofolio-bootstrap-photography-website-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

  <!-- ======= Header ======= -->
  <header id="header" class="header d-flex align-items-center fixed-top">
	<%@ include file="/include/nav.jsp" %> 
  </header><!-- End Header -->

  <main id="main" data-aos="fade" data-aos-delay="1500">

    <!-- ======= End Page Header ======= -->
    <div class="page-header d-flex align-items-center">
      <div class="container position-relative">
        <div class="row d-flex justify-content-center">
          <div class="col-lg-6 text-center">
            <h2>JOIN</h2>
            <br>
            
            <p>Enjoy Trip</p>

          </div>
        </div>
      </div>
    </div><!-- End Page Header -->

    <!-- ======= Join Section ======= -->
    <section id="Join" class="join">
      <div class="container">

        <div class="input-form-backgroud row">
          <div class="input-form col-md-12 mx-auto">

<form action="${root}/user" method="post" role="form" class="validation-form" novalidate>
    <input type="hidden" name="action" value="join">
    <div class="row php-email-form">
        <div class="col-md-6 mb-3">
            <label for="name">이름</label>
            <input type="text" class="form-control" id="name" placeholder="김철수"  name = "username" value="" required>
            <div class="invalid-feedback">
                이름을 입력해주세요.
            </div>
        </div>
        <div class="col-md-6 mb-3 ">
            <label for="nickname">별명</label>
            <input type="text" class="form-control" id="nickname" placeholder="김싸피" name = "nickname" value="" required>
            <div class="invalid-feedback">
                별명을 입력해주세요.
            </div>
        </div>
    </div>






    <div class="row php-email-form">
        <div class="col-md-10 mb-3">
            <label for="email" id = "joinEmail">이메일(중복확인필요)</label>
            <input type="email" class="form-control" id="join-email" name = "join-email" placeholder="you@example.com" required />
            <div class="invalid-feedback">
                이메일을 입력해주세요.
            </div>
        </div>
        <div class="col-md-2 mb-3 align-self-end">
            <button id="idcheckRequest" type="button" class="btn btn-primary">중복검사</button>
         
        </div>
    </div>









    <div class="mb-3 php-email-form">
        <label for="passward">비밀번호</label>
        <input type="password" class="form-control" id="join-passward" name = "join-password" placeholder="******" required />
        <div class="invalid-feedback">
            비밀번호를 입력해주세요.
        </div>
    </div>



   <div class="row php-email-form">
  <div class="col-md-6 mb-3 sel">
  시/도:
   <select id = "sido_select" name='sido_code'class="form-select" required>
   <option value=''>시/도 선택</option> 
   <c:forEach var = "i" items="${sido_map}">
   <option value=${i.key}>${i.value}</option>
   </c:forEach>
   
    </select>
        <div class="invalid-feedback ">
      주소(시/도)를 골라주세요.
    </div>
  
  </div>
    <div class="col-md-6 mb-3 sel">
      구/군:
        <select name='gugun_code' class="form-select" required>
    </select>
       <div class="invalid-feedback">
      주소(구/군)를 골라주세요.
    </div>
   
    </div>

  
  

 
  

</div>



    <div class="mb-3 php-email-form">
        <label for="address2">상세주소<span class="text-muted">&nbsp;(필수 아님)</span></label>
        <input type="text" class="form-control" id="address2" name = "address" placeholder="상세주소를 입력해주세요.">
    </div>


    <hr class="mb-4">
    <div class="custom-control custom-checkbox">
        <input type="checkbox" class="custom-control-input" id="aggrement" required>
        <label class="custom-control-label" for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
    </div>
    <div class="mb-4"></div>
    <button class="btn btn-primary btn-lg btn-block" id="joinbtn" onclick = "alert('이메일 확인 필요')" type="button">가입 완료</button>
</form>
          </div>
        </div>



    </section><!-- End Join Section -->

  </main><!-- End #main -->

  <!-- ======= Footer ======= -->
  <footer id="footer" class="footer">
    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong><span>PhotoFolio</span></strong>. All Rights Reserved
      </div>
      <div class="credits">
        <!-- All the links in the footer should remain intact. -->
        <!-- You can delete the links only if you purchased the pro version. -->
        <!-- Licensing information: https://bootstrapmade.com/license/ -->
        <!-- Purchase the pro version with working PHP/AJAX Join form: https://bootstrapmade.com/photofolio-bootstrap-photography-website-template/ -->
        Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
      </div>
    </div>
  </footer><!-- End Footer -->

  <a href="#" class="scroll-top d-flex align-items-center justify-content-center"><i
      class="bi bi-arrow-up-short"></i></a>

  <div id="preloader">
    <div class="line"></div>
  </div>
	<!-- 로그인 모달창 start -->

<%@ include file="/include/loginmodal.jsp" %> 
	<!-- 로그인 모달 창 end -->
  <!-- Vendor JS Files -->
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="assets/vendor/aos/aos.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>


  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>
  <script src="assets/js/join.js"></script>
    <script src="assets/js/common.js"></script>

</body>

</html>