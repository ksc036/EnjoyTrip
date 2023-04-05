<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <%-- jstl 사용하기 위한 코드 --%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
                <%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
                    <c:set var="root" value="${pageContext.request.contextPath}" />


                    <meta charset="utf-8">
                    <meta content="width=device-width, initial-scale=1.0" name="viewport">
                    <title>Board</title>
                    <meta content="" name="description">
                    <meta content="" name="keywords">

                    <!-- ajax를 위해 jquery 사용 -->
                    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

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

            
 <link href="assets/css/app.css" rel="stylesheet" />
    </head>

    <body>

        <!-- ======= Header ======= -->
        <header id="header" class="header d-flex align-items-center fixed-top">
            <%@ include file="/include/nav.jsp" %>
        </header>
        <!-- End Header -->

        <main id="main" data-aos="fade" data-aos-delay="1500"> <!-- ======= End Page Header ======= -->
            <div class="page-header d-flex align-items-center">
                <div class="container position-relative">
                    <div class="row d-flex justify-content-center">
                        <div class="col-lg-6 text-center">
                            <h2>게시판</h2>
                        </div>
                    </div>
                </div>
            </div>
            <!-- End Page Header --> <!-- ======= Board Section ======= -->
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8 col-md-10 col-sm-12">
                        <h2 class="my-3 py-3 shadow-sm text-center">글목록</h2>
                    </div>
                    <div class="col-lg-8 col-md-10 col-sm-12">
                        <div class="row align-self-center mb-2">
                            <div class="col-md-2 text-start">
         <c:if test="${not empty userInfo}">
 <button type="button" id="btn-mv-register" class="btn btn-dark btn-sm">글쓰기</button>
            </c:if>                            
                                
                            </div>
                            <div class="col-md-7 offset-3">
                              <form class="d-flex" id="form-search" action="">
                <input type="hidden" name="action" value="list"/>
                <input type="hidden" name="pgno" value="1"/>
                <select
                  name="key"
                  id="key"
                  class="form-select form-select-sm ms-5 me-1 w-50"
                  aria-label="검색조건"
                >
                                        <option value="articleno">글번호</option>
                                        <option value="title" selected>제목</option>
                                        <option value="email">작성자 메일</option>
                                    </select>
                                    <div class="input-group input-group-sm">
                                       <input type="text" name="word" id="word" class="form-control" placeholder="검색어..." />
                                        <button id="btn-search" class="btn btn-dark btn-sm" type="button">검색</button>
                                    </div>
                                </form>
                            </div>
                        </div>
          <table class="table table-striped table-hover">
            <thead >
              <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col" width=40%>제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">작성일</th>
              </tr>
            </thead>
                            <tbody>
                                <c:forEach var="article" items="${articles}">
                                    <tr class="text-center">
                                        <th scope="row">${article.articleNo}</th>
                                        <td class="text-start">
                                        <a href="#" 
                                        class="article-title "
                                        data-no="${article.articleNo}" 
                                        style="text-decoration: none">
                                        ${article.title} </a>
                                        </td>
                                        <td>${article.email}</td>
                                        <td>${article.hit}</td>
                                        <td>${fn:substring(article.regDate,0,10)}</td>

                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="row">${navigation.navigator}</div>
                </div>
            </div>
            <form id="form-param" method="get" action="">
                <input type="hidden" id="p-action" name="action" value=""> 
                <input type="hidden" id="p-pgno" name="pgno"  value="">
               <input type="hidden" id="p-key" name="key" value=""> 
              <input type="hidden" id="p-word" name="word" value="">
            </form>

            <script>
                
    

                document.querySelector("#btn-search").addEventListener("click", function () {
              	  let form = document.querySelector("#form-search");
                    form.setAttribute("action", "${root}/board");
                    form.submit();
                });
              
                

                
                
                
                let pages = document.querySelectorAll(".page-link");
                pages.forEach(function (page) {
                  page.addEventListener("click", function () {
                    console.log(this.parentNode.getAttribute("data-pg"));
                    document.querySelector("#p-action").value = "list";
                 	  document.querySelector("#p-pgno").value = this.parentNode.getAttribute("data-pg");
                 	  document.querySelector("#p-key").value = "${param.key}";
                 	  document.querySelector("#p-word").value = "${param.word}";
                    document.querySelector("#form-param").submit();
                  });
                });
                
                
                let titles = document.querySelectorAll(".article-title");
                titles.forEach(function (title) {
                    title.addEventListener("click", function () {
                        console.log(this.getAttribute("data-no"));
                        location.href = "${root}/board?action=view&articleno=" + this.getAttribute("data-no") + "&pgno=" + "${param.pgno}"+ "&key=" + "${param.key}"+ "&word=" + "${param.word}";
                    });
                });
                
                
            </script>
            <c:if test="${not empty userInfo}">
                <script>
                    document.querySelector("#btn-mv-register").addEventListener("click", function () {
                    	location.href = "${root}/board?action=mvwrite";
                    });
                </script>
            </c:if>
            
        </main>
        <!-- End #main -->

        <!-- ======= Footer ======= -->
        <footer id="footer" class="footer">
            <div class="container">
                <div class="copyright">
                    &copy; Copyright <strong><span>PhotoFolio</span></strong>. All
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
            <script src="assets/js/common.js"></script>
    </body>

    </html>