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


	<div class="container" id="section_food">

		<h3 class="mt-5 title foodTitle">FOOD</h3>
		<div class="row mt-2 d-flex align-items-stretch" id="rowFood">


			<c:forEach items="${foodModel.listResult}" var="o">
				
				<div class="col-md-3 col-12 mt-4">

					<div class="card h-100 foodCard">
						<a href="detail?pid=${o.id}"> <img class="card-img-top"
						
							src="${o.img}" alt="Card image cap">
							
						</a>

						<div class="card-body">
							<h5 class="card-title">${o.productName}</h5>
							<p class="card-text">${o.price}$</p>
							<button style="width: 90%" onClick="addToCart(${o.id})"
								class="btn btn-primary addToCart">Add to cart</button>
						</div>
					</div>



				</div>

			</c:forEach>
		</div>

		<button class="btn btn-outline-primary mt-3"
			onClick="loadMoreProduct('food')">Load more</button>


	</div>
	<div class="container" id="section_drink">

		<h3 class="mt-5 title">DRINK</h3>
		<div class="row mt-2 d-flex align-items-stretch" id="rowDrink">


			<c:forEach items="${drinkModel.listResult}" var="o">

				<div class="col-md-3 col-12 mt-4">

					<div class="card h-100 drinkCard">
						<a href="detail?pid=${o.id}"> <img class="card-img-top"
							src="${o.img}" alt="Card image cap">
						</a>

						<div class="card-body">
							<h5 class="card-title">${o.productName}</h5>
							<p class="card-text">${o.price}$</p>
							<button style="width: 90%" onClick="addToCart(${o.id})"
								class="btn btn-primary addToCart">Add to cart</button>
						</div>
					</div>



				</div>

			</c:forEach>

		</div>

		<button class="btn btn-outline-primary mt-3"
			onClick="loadMoreProduct('drink')">Load more</button>


	</div>
	<div class="container">
		<h3 class="mt-5 title">NEWS</h3>
		<div class="row mt-2 d-flex align-items-stretch"></div>
	</div>


	</main>
</body>
</html>