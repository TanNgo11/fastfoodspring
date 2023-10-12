<%-- 
    Document   : index
    Created on : Jul 18, 2023, 3:51:07 PM
    Author     : TAN
--%>
<%@ include file="/common/taglib.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"
	integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
<link rel="stylesheet" href="<c:url value='/template/css/style.css'/>">
<link rel="stylesheet" href="<c:url value='/template/css/detail.css'/>">
<link rel="stylesheet" href="<c:url value='/template/css/toastmsg.css'/>">

<c:if
	test="${pageContext.request.localName =='http://localhost:8080/cart'}">
	<link rel="stylesheet" href="<c:url value='/template/css/cart.css'/>">
	<!-- MDB -->
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.js"></script>
	<!-- Google Fonts -->
	<link rel="preconnect" href="https://fonts.googleapis.com">

	<link
		href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
		rel="stylesheet" />
	<!-- MDB -->
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
</c:if>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap"
	rel="stylesheet">


</head>
<body onload="renderProducts(), renderSumary()">
	<%@ include file="/common/web/header.jsp"%>


	<dec:body />
	
	
	<%@ include file="/common/web/footer.jsp"%>
	<div class="goToTopBtn">^</div>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
	<script>
	
	
		$(document).ready(function() {
			$('.goToTopBtn').click(function() {
				$('html , body').animate({
					scrollTop : 0
				}, 500)
				return false;
			})

			$(window).scroll(function() {
				if ($(this).scrollTop() > 100) {
					$('.goTop_btn').fadeIn();
				} else
					$('.goTop_btn').fadeOut();
			})

		});
	</script>
	<script src="<c:url value='/template/js/loadmore.js'/>"></script>
	<script src="<c:url value='/template/js/loadimgs.js'/>"></script>
	<script src="<c:url value='/template/js/cart.js'/>"></script>
	<script src="<c:url value='/template/js/addressVNAPI.js'/>"></script>
	<script src="<c:url value='/template/js/toastmessage.js'/>"></script>
	
	<!--  <script src="resources/js/loadmore.js"></script>
        <script src="resources/js/addToCart.js"></script>
        <script src="resources/js/loadsumary.js"></script>
        <script src="resources/js/category.js"></script>
        <script src="resources/js/searchByName.js"></script>
        <script src="resources/js/showMenuMobile.js"></script> -->
</body>
</html>
