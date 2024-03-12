<%-- 
    Document   : index
    Created on : Jul 18, 2023, 3:51:07 PM
    Author     : TAN
--%>
<%@ include file="/common/taglib.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.fastfood.utils.SecurityUtils"%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="theme-color" content="#FF969A" />

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js"
	integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />


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
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="<c:url value='/template/css/style.css'/>">

<link rel="stylesheet" href="<c:url value='/template/css/boxchat.css'/>">
<link rel="stylesheet"
	href="<c:url value='/template/css/toastmsg.css'/>">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
<script
	src="https://unpkg.com/isotope-layout@3/dist/isotope.pkgd.min.js"></script>

<c:if
	test="${pageContext.request.localName =='http://localhost:8080/cart'}">
	<link rel="stylesheet" href="<c:url value='/template/css/cart.css'/>">

</c:if>



<c:choose>


	<c:when
		test="${fn:startsWith(pageContext.request.requestURI, '/category')}">
		<link rel="stylesheet"
			href="<c:url value='/template/css/category.css'/>">







	</c:when>



	<c:when
		test="${fn:startsWith(pageContext.request.requestURI, '/contact')}">
		<link rel="stylesheet"
			href="<c:url value='/template/css/contact.css'/>">
		<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
			rel='stylesheet'>



	</c:when>



	<c:when
		test="${fn:startsWith(pageContext.request.requestURI, '/detail')}">
		<link rel="stylesheet" type="text/css"
			href="<c:url value='/template/jquery-comments'/>/css/jquery-comments.css">
		<link rel="stylesheet"
			href="<c:url value='/template/css/detail.css'/>">
		<link rel="stylesheet" type="text/css"
			href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

		<script type="text/javascript"
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
		<script type="text/javascript"
			src="<c:url value='/template/jquery-comments'/>/js/jquery-comments.js"></script>

	</c:when>



	<c:when
		test="${fn:startsWith(pageContext.request.requestURI, '/bill')}">
		<!-- MDB -->
		<script type="text/javascript"
			src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.4.0/mdb.min.js"></script>
		<!-- Google Fonts -->
		<link rel="preconnect" href="https://fonts.googleapis.com">

		<link
			href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
			rel="stylesheet" />
		<!-- MDB -->

		<link rel="stylesheet" href="<c:url value='/template/css/bill.css'/>">

	</c:when>





</c:choose>


</head>

<body>


	<c:choose>
		<c:when
			test="${fn:startsWith(pageContext.request.requestURI, '/home')}">
			<%@ include file="/common/web/header.jsp"%>

		</c:when>

		<c:when
			test="${fn:startsWith(pageContext.request.requestURI, '/category')}">
			<script src="<c:url value='/template/js/category.js'/>"></script>
			<%@ include file="/common/web/header_withoutslider.jsp"%>

		</c:when>

		<c:when
			test="${fn:startsWith(pageContext.request.requestURI, '/user')}">
			<%@ include file="/common/web/header_withoutslider.jsp"%>

		</c:when>

		<c:when
			test="${fn:startsWith(pageContext.request.requestURI, '/bill')}">
			<%@ include file="/common/web/header_withoutslider.jsp"%>

		</c:when>

		<c:when
			test="${fn:startsWith(pageContext.request.requestURI, '/search')}">
			<%@ include file="/common/web/header_withoutslider.jsp"%>

		</c:when>

		<c:when
			test="${fn:startsWith(pageContext.request.requestURI, '/contact')}">
			<%@ include file="/common/web/header_withoutslider.jsp"%>

		</c:when>

		<c:when
			test="${fn:startsWith(pageContext.request.requestURI, '/news')}">
			<%@ include file="/common/web/header_withoutslider.jsp"%>

		</c:when>

		<c:when
			test="${fn:startsWith(pageContext.request.requestURI, '/detail')}">
			<%@ include file="/common/web/header_withoutslider.jsp"%>

		</c:when>

		<c:when
			test="${fn:startsWith(pageContext.request.requestURI, '/cart')}">
			<%@ include file="/common/web/header_withoutslider.jsp"%>
			<link rel="stylesheet" href="<c:url value='/template/css/cart.css'/>">



		</c:when>

		<c:when
			test="${fn:startsWith(pageContext.request.requestURI, '/about-us')}">
			<%@ include file="/common/web/header_withoutslider.jsp"%>

		</c:when>



	</c:choose>




	<dec:body />

	<div id="toasts">
		<input id="message" type="hidden" value="${msg}">
	</div>
	<input id="cartItem" type="hidden" value="${cartItem}">

	<security:authorize access="isAuthenticated()">
		<input id="userId" type="hidden"
			value="<%=SecurityUtils.getPrincipal().getId()%>">
	</security:authorize>
	<security:authorize access="isAnonymous()">
		<input id="userId" type="hidden" value="">
	</security:authorize>


	<c:choose>

		<c:when
			test="${not fn:startsWith(pageContext.request.requestURI, '/accessDenied')}">
			<%@ include file="/common/web/footer.jsp"%>

		</c:when>


	</c:choose>

	<%@ include file="/common/web/boxchat.jsp"%>
	<div class="goToTopBtn">^</div>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>

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

		function addToCart(id) {

			$.ajax({
				url : "/api/cart",
				type : "POST",
				data : {
					idP : id

				},
				success : function(data) {

					$('#count_in_cart').html(data.items.length)
					$('#message').val("success_add_to_cart");
					let msg = document.getElementById("message");
					if (msg.value !== "") {
						createToast(msg.value);
						msg.value = "";
					}

				}

			});
			
			

		}
		window.addEventListener('load', (event) => {
		    const redirectTo = localStorage.getItem('redirectTo');
		    if (redirectTo) {
		        localStorage.removeItem('redirectTo'); // Clean up
		        window.location.href = redirectTo; // Redirect to the stored URL
		    }
		});
	</script>
	<script
		src="<c:url value='/template/paging/jquery.twbsPagination.js'/>"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.4/sockjs.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>

	<script src="<c:url value='/template/js/boxchat.js'/>"></script>
	<script src="<c:url value='/template/js/searchByName.js'/>"></script>



	<script src="<c:url value='/template/js/toastmessage.js'/>"></script>


	<script src="<c:url value='/template/js/showMenuMobile.js'/>"></script>



	<!-- 

	 <script src="resources/js/loadmore.js"></script>
        <script src="resources/js/addToCart.js"></script>
        <script src="resources/js/loadsumary.js"></script>
        <script src="resources/js/category.js"></script>
      
        -->
</body>
</html>
