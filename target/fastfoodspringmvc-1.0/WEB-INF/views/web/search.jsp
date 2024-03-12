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

	<div style="margin-top: 54px" class="container " id="section_food">
		<nav class="mt-3" aria-label="breadcrumb">
			<ol style="background-color: white; padding-left: 0px;"
				class="breadcrumb">
				<li class="breadcrumb-item"><a style="color: #007bff;"
					href="/home">Home</a></li>
				<li class="breadcrumb-item"><a style="color: #007bff;" href="#">Search</a></li>
				<li id="breadcrumb-data" class="breadcrumb-item active"
					aria-current="page">Data</li>
			</ol>
		</nav>

		<h1 class="entry-title">
			<span>Fast Food</span>
		</h1>






		<div class="col-3" style="padding-left: 0px; padding-right: 0px;">
			<span><label class="mr-sm-2" for="inlineFormCustomSelect">Sort
					By</label> </span><select id="sorts" style="width: 50%"
				class="custom-select mr-sm-2">

				<option value="1" data-sort-by="original-order" selected>Default
				</option>
				<option value="2" data-sort-by="name">Name</option>
				<option value="2" data-sort-by="price">Price</option>
			</select>
		</div>



		<h3 class="mt-2 title foodTitle">
			SEARCH RESULT:
			<c:out value="${searchKey}" />
		</h3>
		<div class="grid" style="position: static !important">
			<div class="row mt-2 d-flex align-items-stretch" id="rowFood">
				<c:forEach items="${listProduct}" var="o">


					<div class="col-md-3 col-12 mt-4">

						<div class="card h-100 foodCard">
							<a href="/detail/${o.slug}"> <img class="card-img-top"
								src="${o.listImage[0].imageURL}" alt="Card image cap">

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
		</div>




	</div>










</body>
</html>