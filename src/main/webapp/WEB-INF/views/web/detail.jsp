<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main class="detail">
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-12">
				<input type="radio" id="image1" name="image" checked> <input
					type="radio" id="image2" name="image"> <input type="radio"
					id="image3" name="image">

				<div class="container">
					<div class="featured-wrapper">
						<ul class="featured-list">

						</ul>
						<ul class="arrows">

						</ul>
						<ul class="dots">

						</ul>
					</div>
					<ul class="thumb-list">

					</ul>
				</div>
				<c:forEach var="img" items="${productDetail.getListImage()}">
					<input class="listimg" type="hidden" value="${img.getImageURL()}">
				</c:forEach>


			</div>
			<div class="col-md-6 col-12">
				<div class="product-detail">
					<h1 class="title-detail">${productDetail.getProductName()}</h1>
					<p class="price">${productDetail.getPrice()}$</p>
					<p class="description">${productDetail.getDescription()}</p>
					<button onClick="addToCart(${productDetail.getId()})"
						class="btn btn-primary addToCart addToCartDetail">Add to
						Cart</button>
				</div>

			</div>
		</div>
		<div style="margin-top: 100px" id="comments-container"></div>
	</div>
	</main>
	<input id="productCommentId" type="hidden"
		value="<c:out value='${productId}'/>">


	<security:authorize access="isAuthenticated()">

		<input id="commentUserId" type="hidden"
			value="<%=SecurityUtils.getPrincipal().getId()%>">
			
			<input id="commentFullname" type="hidden"
			value="<%=SecurityUtils.getPrincipal().getFullName()%>">
	</security:authorize>

	<script src="/template/js/comment.js"></script>
	<script src="<c:url value='/template/js/loadimgs.js'/>"></script>


	</script>

</body>
</html>