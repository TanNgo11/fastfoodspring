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
			<div class="col-md-6 col-12 col-product">
				<div class="product-detail">
					<h1 class="title-detail">${productDetail.getProductName()}</h1>
					<c:choose>
						<c:when
							test="${productDetail.salePrice < productDetail.price && productDetail.salePrice != 0}">
							<p class="sale-price">
								<fmt:formatNumber value="${productDetail.getSalePrice()}"
									type="currency" currencyCode="VND" />

							</p>
							<p class="price">
								<i style="color: #ff5b6a; margin-right: 5px"
									class='bx bxs-discount bx-flip-horizontal bx-tada'></i>
								<fmt:formatNumber value="${productDetail.getPrice()}"
									type="currency" currencyCode="VND" />
							</p>

						</c:when>
						<c:otherwise>


							<p class="card-text sale-price">
								<fmt:formatNumber value="${productDetail.getPrice()}"
									type="currency" currencyCode="VND" />
							</p>

							<p style="visibility: hidden;" class="price">
								<i style="color: #ff5b6a; margin-right: 5px"
									class='bx bxs-discount bx-flip-horizontal bx-tada'></i>
								<fmt:formatNumber value="${productDetail.getPrice()}"
									type="currency" currencyCode="VND" />
							</p>
						</c:otherwise>
					</c:choose>




					<p class="description">${productDetail.getDescription()}</p>



					<c:choose>
						<c:when test="${productDetail.inStock>0}">
							<div class="mobile-addToCard">
								<div class="qty mt-8-custom">
									<span class="lbl">Số lượng</span>
									<div class="q-inner">
										<button class="btn-minute" type="button">-</button>
										<span id="productQuantity" class="number">1</span>
										<button class="btn-plus" type="button">+</button>
									</div>
								</div>
								<button id="addToCartBtn"
									class="btn btn-primary addToCart addToCartDetail">Add
									to Cart</button>
							</div>
						</c:when>
						<c:otherwise>
							<button onClick="showOutOfStockMessage()"
								class="btn btn-primary addToCart addToCartDetail">Out
								of stock</button>
						</c:otherwise>
					</c:choose>





				</div>

			</div>
		</div>
		<c:if test="${not empty productDetail.relatedProducts && productDetail.inStock>0}">
			<div class="options-product">
				<div class="headline">
					<h4>Ngon hơn khi ăn kèm</h4>
				</div>
				<ul class="list-options add-opts">
					<c:forEach items="${productDetail.relatedProducts}" var="product">

						<li style="padding-left: 16px;"><div class="product-name">
								<img style="margin-top: 5px" loading="lazy" alt="Gà Lắc"
									src="${product.listImage.get(0).imageURL}"><span
									class="txt">${product.productName}</span>
							</div> <input class="productIdClass" type="hidden"
							value="${product.getId()}">
							<div class="r-info">
								<div class="inner">
									<div style="float: left">




										<c:choose>
											<c:when
												test="${product.salePrice < product.price && product.salePrice != 0}">
												<span class="sale-price">+ <fmt:formatNumber
														value="${product.salePrice}" type="currency"
														currencyCode="VND" /></span>



												<span style="font-size: 12px !important" class="price">+
													<fmt:formatNumber value="${product.price}" type="currency"
														currencyCode="VND" />
												</span>


											</c:when>
											<c:otherwise>

												<span style="display: block; margin-top: 8px;"
													class="sale-price">+ <fmt:formatNumber
														value="${product.price}" type="currency"
														currencyCode="VND" />
												</span>
											</c:otherwise>
										</c:choose>

									</div>
									<div class="qty sm-type">
										<div class="q-inner">
											<button class="btn-minute" type="button">-</button>
											<span class="number">0</span>
											<button class="btn-plus" type="button">+</button>
										</div>
									</div>
								</div>
							</div></li>
					</c:forEach>

				</ul>
			</div>
		</c:if>

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
	<script src="<c:url value='/template/js/detail.js'/>"></script>
	<script>
		function showOutOfStockMessage() {
			let msg = document.getElementById("message");
			msg.value = "error_instock"
			if (msg.value !== "") {
				createToast(msg.value);
				msg.value = "";
			}
		}
	</script>




</body>
</html>