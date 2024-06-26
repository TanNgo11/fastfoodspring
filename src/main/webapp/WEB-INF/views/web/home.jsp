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
	<main>


	<section class="mt-5">
		<div class="container">
			<div class="row g-4">
				<div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.1s"
					style="visibility: visible; animation-delay: 0.1s; animation-name: fadeInUp;">
					<div style="height: 315px;" class="service-item rounded pt-3">
						<div class="p-4">

							<i style="color: #FEA116" class="fa fa-user-circle fa-3x mb-3"
								aria-hidden="true"></i>
							<h5 class="mt-3">Master Chefs</h5>
							<p>Embark on a culinary adventure with Master Chefs, where
								every dish is a masterpiece of creativity and skill.</p>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.3s"
					style="visibility: visible; animation-delay: 0.3s; animation-name: fadeInUp;">
					<div style="height: 315px;" class="service-item rounded pt-3">
						<div class="p-4">
							<i style="color: #FEA116" class="fa fa-cutlery fa-3x mb-3"
								aria-hidden="true"></i>
							<h5 class="mt-3">Quality Food</h5>
							<p>Experience the essence of quality food, where every
								ingredient is selected with care and every dish crafted to
								perfection, embodying both taste and tradition.</p>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.5s"
					style="visibility: visible; animation-delay: 0.5s; animation-name: fadeInUp;">
					<div style="height: 315px;" class="service-item rounded pt-3">
						<div class="p-4">
							<i style="color: #FEA116" class="fa fa-cart-plus fa-3x mb-3 "></i>
							<h5 class="mt-3">Online Order</h5>
							<p>Indulge in the convenience of online ordering, bringing
								the excellence of quality food directly to your doorstep with
								just a few clicks.</p>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-sm-6 wow fadeInUp" data-wow-delay="0.7s"
					style="visibility: visible; animation-delay: 0.7s; animation-name: fadeInUp;">
					<div style="height: 315px;" class="service-item rounded pt-3">
						<div class="p-4">
							<i style="color: #FEA116" class="fa fa-phone fa-3x mb-3"></i>
							<h5 class="mt-3">24/7 Service</h5>
							<p>Enjoy the unparalleled convenience of 24/7 service,
								ensuring that exquisite culinary experiences are available to
								you at any hour, day or night.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section id="about" class="about mt-5">
		<div class="container">
			<div class="container aos-init aos-animate" data-aos="fade-up">

				<div class="section-header">
					<h2 class="text-center">About Us</h2>
					<p class="text-center">
						Learn More <span>About Us</span>
					</p>
				</div>

				<div class="row gy-4">
					<div
						class="col-lg-7 position-relative about-img aos-init aos-animate"
						style="background-image: url(<c:url value='/inc/Other/about.jpg'/>) ;"
						data-aos="fade-up" data-aos-delay="150"></div>
					<div class="col-lg-5 d-flex align-items-end aos-init aos-animate"
						data-aos="fade-up" data-aos-delay="300">
						<div class="content ps-0 ps-lg-5">
							<p class="fst-italic">At the core of our fast food ethos, we
								blend speed with unmatched quality, transforming every meal into
								a culinary delight.</p>
							<ul>
								<li><i class="bi bi-check2-all"></i> Our commitment to
									excellence is evident in our carefully sourced ingredients,
									innovative recipes, and the seamless online ordering system
									that caters to your cravings 24/7.</li>
								<li><i class="bi bi-check2-all"></i> We're more than just
									fast food; we're a movement towards elevating quick dining to
									gourmet experiences.</li>
								
							</ul>
							<p>Join us in redefining convenience with a taste of luxury, anytime, anywhere.</p>

							<div class="position-relative mt-4">
								<img src="<c:url value='/inc/Other/about-2.jpg'/>"
									class="img-fluid" alt=""> <a
									href="https://www.youtube.com/watch?v=LXb3EKWsInQ"
									class="glightbox play-btn"></a>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</section>




	<div class="container" id="top4Product">

		<h4 class="mt-5 title foodTitle">Top Sales Product</h4>
		<div class="row mt-2 d-flex align-items-stretch" id="topSales">


			<c:forEach items="${top4ProductSales.listResult}" var="o">


				<div class="col-md-3 col-6 mt-4">

					<div class="card h-100 topSales">
						<a href="/detail/${o.slug}"> <img class="card-img-top"
							src="${o.listImage[0].imageURL}" alt="Card image cap">

						</a>

						<div class="card-body">
							<h4 class="card-title">${o.productName}</h4>
							<c:choose>
								<c:when test="${o.salePrice < o.price && o.salePrice != 0}">
									<p class="card-text sale-price">
										<fmt:formatNumber value="${o.salePrice}" type="currency"
											currencyCode="VND" pattern="###,###₫" />

									</p>
									<p class="card-text price">
										<i style="color: #ff5b6a; margin-right: 5px"
											class='bx bxs-discount bx-flip-horizontal bx-tada'></i>
										<fmt:formatNumber value="${o.price}" type="currency"
											currencyCode="VND" pattern="###,###₫" />
									</p>

								</c:when>
								<c:otherwise>

									<p class="card-text sale-price">
										<fmt:formatNumber value="${o.price}" type="currency"
											currencyCode="VND" pattern="###,###₫" />
									</p>
								</c:otherwise>
							</c:choose>



							<c:choose>
								<c:when test="${o.inStock>0}">
									<button style="width: 90%" onClick="addToCart(${o.id})"
										class="btn btn-primary addToCart">Add to cart</button>
								</c:when>
								<c:otherwise>
									<p class="text-danger"
										style="margin-bottom: 6px; width: 100%; text-align: center; font-weight: bold;">Out
										of Stock</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>



				</div>



			</c:forEach>
		</div>

		<button style="color: #FF5B6A; border-color: #FF5B6A"
			class="btn btn-outline-primary mt-3 loadmore"
			onClick="loadMoreProduct('topSales')">Load more</button>


	</div>



	<div class="container" id="section_food">




		<h4 class="mt-5 title foodTitle">FOOD</h4>
		<div class="row mt-2 d-flex align-items-stretch" id="rowFood">


			<c:forEach items="${foodModel.listResult}" var="o">


				<div class="col-md-3 col-6 mt-4">

					<div class="card h-100 foodCard">
						<a href="/detail/${o.slug}"> <img class="card-img-top"
							src="${o.listImage[0].imageURL}" alt="Card image cap">

						</a>

						<div class="card-body">
							<h5 class="card-title">${o.productName}</h5>
							<c:choose>
								<c:when test="${o.salePrice < o.price && o.salePrice != 0}">
									<p class="card-text sale-price">
										<fmt:formatNumber value="${o.salePrice}" type="currency"
											currencyCode="VND" pattern="###,###₫" />

									</p>
									<p class="card-text price">
										<i style="color: #ff5b6a; margin-right: 5px"
											class='bx bxs-discount bx-flip-horizontal bx-tada'></i>
										<fmt:formatNumber value="${o.price}" type="currency"
											currencyCode="VND" pattern="###,###₫" />
									</p>

								</c:when>
								<c:otherwise>

									<p class="card-text sale-price">
										<fmt:formatNumber value="${o.price}" type="currency"
											currencyCode="VND" pattern="###,###₫" />
									</p>
								</c:otherwise>
							</c:choose>



							<c:choose>
								<c:when test="${o.inStock>0}">
									<button style="width: 90%" onClick="addToCart(${o.id})"
										class="btn btn-primary addToCart">Add to cart</button>
								</c:when>
								<c:otherwise>
									<p class="text-danger"
										style="margin-bottom: 6px; width: 100%; text-align: center; font-weight: bold;">Out
										of Stock</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>



				</div>



			</c:forEach>
		</div>

		<button style="color: #FF5B6A; border-color: #FF5B6A"
			class="btn btn-outline-primary mt-3 loadmore"
			onClick="loadMoreProduct('food')">Load more</button>


	</div>
	<div class="container" id="section_drink">

		<h4 class="mt-5 title">DRINK</h4>
		<div class="row mt-2 d-flex align-items-stretch" id="rowDrink">


			<c:forEach items="${drinkModel.listResult}" var="o">

				<div class="col-md-3 col-6 mt-4">

					<div class="card h-100 drinkCard">
						<a href="/detail/${o.slug}"> <img class="card-img-top"
							src="${o.listImage[0].imageURL}" alt="Card image cap">
						</a>

						<div class="card-body">
							<h5 class="card-title">${o.productName}</h5>
							<c:choose>
								<c:when test="${o.salePrice < o.price && o.salePrice != 0}">
									<p class="card-text sale-price">
										<fmt:formatNumber value="${o.salePrice}" type="currency"
											currencyCode="VND" pattern="###,###₫" />

									</p>
									<p class="card-text price">
										<i style="color: #ff5b6a; margin-right: 5px"
											class='bx bxs-discount bx-flip-horizontal bx-tada'></i>${o.price}$</p>

								</c:when>
								<c:otherwise>

									<p class="card-text sale-price">
										<fmt:formatNumber value="${o.price}" type="currency"
											currencyCode="VND" pattern="###,###₫" />
									</p>
								</c:otherwise>
							</c:choose>



							<c:choose>
								<c:when test="${o.inStock>0}">
									<button style="width: 90%" onClick="addToCart(${o.id})"
										class="btn btn-primary addToCart">Add to cart</button>
								</c:when>
								<c:otherwise>
									<p class="text-danger"
										style="width: 90%; text-align: center; font-weight: bold;">Out
										of Stock</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>



				</div>

			</c:forEach>

		</div>

		<button style="color: #FF5B6A; border-color: #FF5B6A"
			class="btn btn-outline-primary mt-3 loadmore"
			onClick="loadMoreProduct('drink')">Load more</button>


	</div>
	<div class="container">
		<h3 class="mt-5 title">NEWS</h3>
		<div class="row mb-2">
			<c:forEach items="${listNews}" var="news">

				<div class="col-md-6 mt-3">

					<div style="max-height: 300px;"
						class="card flex-md-row mb-4 box-shadow h-md-250">
						<div class="card-body d-flex flex-column align-items-start">
							<strong class="d-inline-block mb-2 text-primary">News</strong>
							<h3 class="mb-0">
								<a style="font-size: 24px; font-weight: bold;" class="text-dark"
									href="/news/${news.slug}">${news.title}</a>
							</h3>
							<div class="mb-1 text-muted">${news.dateFormat}</div>
							<div
								style="font-size: 14px; margin-top: 10px; display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 1; overflow: hidden;"
								class="card-text mb-auto">${news.description}</div>
						</div>
						<img class="card-img-right flex-auto d-none d-md-block"
							data-src="holder.js/200x250?theme=thumb"
							alt="Thumbnail [200x250]" style="width: 200px; height: 250px;"
							src="${news.imageURL}" data-holder-rendered="true">
					</div>

				</div>

			</c:forEach>


		</div>
		<p class="text-center">
			<a href="/news" class="text-center"
				style="display: block; cursor: pointer; text-decoration: underline;">See
				more</a>
		</p>



	</div>
	<div class="ui-rounded">

		<div class="section-100 bg-white position-relative overflow-hidden">
			<span class="slider-backgound-cicrle-small z-0"></span>
			<div
				class="background bottom-right background-h-100 z-0 d-none d-lg-block"
				style="background-image: url(&quot;<c:url value='/inc/Other/banner.png'/>&quot;);">
				<img src="<c:url value='/inc/Other/banner.png'/>" alt=""
					style="display: none;">
			</div>
			<div class="container z-1">
				<div class="row justify-content-center ">
					<div
						class="col-12 col-md-11 col-lg-10 col-xl-7 align-self-center text-center">
						<h2 class="f-50">Grab your 50% off discount Coupon direct to
							your E-mail now</h2>
					</div>
					<div class="w-100 my-3"></div>
					<div class="col-12 col-md-auto align-self-center my-2">
						<input class="form-control form-control-lg" type="email"
							placeholder="Your email address">
					</div>
					<div class="col-12 col-md-auto align-self-center my-2">
						<button type="button" class="btn btn-lg btn-block btn-default">
							Get Discount<span class="ml-2 icon arrow_right"></span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	




	</main>
	<script src="<c:url value='/template/js/loadmore.js'/>"></script>

</body>
</html>