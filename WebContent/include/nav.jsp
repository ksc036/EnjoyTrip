<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<div class="container-fluid d-flex align-items-center justify-content-between">
		<a href="home.jsp" class="logo d-flex align-items-center me-auto me-lg-0"> <i class="bi bi-camera"></i>
			<h1 class="fw-bold">Enjoy Trip</h1>
		</a>

		<nav id="navbar" class="navbar  justify-content-center">
			<ul id="navbarlist">
			 <c:if test="${not empty userInfo}">
			 <li><a href="${root}/home.jsp" class ="homenav">home</a></li>
			 <li><a href="${root}/board?action=list&pgno=1&key=&word=" class ="boardnav">board</a></li>
			 <li><a href="${root}/map.jsp" class ="mapnav">map</a></li>

			 </c:if>
			 <c:if test="${empty userInfo}">
			 
 			 <li><a href="${root}/home.jsp" class ="homenav">home</a></li>
			 <li><a href="${root}/board?action=list&pgno=1&key=&word=" class ="boardnav">board</a></li>
			 <li><a href="${root}/map.jsp" class ="mapnav">map</a></li>
 			 <li><a href="${root}/user?action=mvjoin" class ="joinnav">join</a></li>
			   </c:if>
			
			</ul>

		</nav>
		<div class="header-social-links">
			<a href="#" class="twitter"><i class="bi bi-twitter"></i></a> <a href="#" class="facebook"><i
					class="bi bi-facebook"></i></a> <a href="#" class="instagram"><i class="bi bi-instagram"></i></a> <a
				href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>




			<c:choose>
				<c:when test="${empty userInfo}">
					<span id="loginoutcheck"> <a href="#" data-bs-toggle="modal" data-bs-target="#LoginModal"
							class="linkedin" id="loginbtn">LOGIN</a>
					</span>
		</div>
		<i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i> <i
			class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>



	</c:when>
	<c:otherwise>
		<span id="loginoutcheck"> <a href="${root}/user?action=logout" id="logoutbtn" onclick="alert('로그아웃')">LOGOUT</a>
		</span>
		<div>
			<a class="nav-link disabled float-right"><strong>${userInfo.username}</strong>님</a>
		</div>
		</div>
		<i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i>
		<i class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>
		</div>


	</c:otherwise>
	</c:choose>

	<script>
	let lst = ["home", "board", "map", "join"]
		for (str of lst) {
			if (document.location.href.match(str)) {
				var actlist = document.getElementsByClassName(str+"nav");			
				    for (let i = 0; i < actlist.length; i++){
				    	actlist.item(0).setAttribute('class', 'active')
				    }

		
			}

		}
		
	</script>