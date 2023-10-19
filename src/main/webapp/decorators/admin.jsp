<%@ include file="/common/taglib.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.css">

<!-- Boxicons -->
<link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css'
	rel='stylesheet'>
<!-- My CSS -->
<link rel="stylesheet"
	href="<c:url value='/template/css/admin_home.css'/>">
<link rel="stylesheet"
	href="<c:url value='/template/css/toastmsg.css'/>">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"
	integrity="sha512-3P8rXCuGJdNZOnUx/03c1jOTnMn3rP63nBip5gOP2qmUh5YAdVAvFZ1E+QLZZbC1rtMrQb+mah3AfYW11RUrWA=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>




<title>AdminPage</title>
<style>
a, a:hover {
	color: black;
	text-decoration: none;
}

ul li {
	list-style-type: none;
}

.overlay {
	background-color: #ffffff;
	display: none;
	position: fixed;
	top: 50px;
	left: 35%;
	right: 50%;
	bottom: 40px;
	width: 400px;
	padding: 20px;
	z-index: 9999;
	border-radius: 3px;
	-webkit-box-shadow: 3px 3px 24px 0px rgba(0, 0, 0, 0.59);
	-moz-box-shadow: 3px 3px 24px 0px rgba(0, 0, 0, 0.59);
	box-shadow: 3px 3px 24px 0px rgba(0, 0, 0, 0.59);
}

.overlayEdit .closeBtnEdit, .overlay .closeBtn {
	position: absolute;
	top: -20px;
	right: 2px;
	font-size: 40px;
	cursor: pointer;
}

.overlayEdit .closeBtnEdit, .overlay .closeBtn:hover {
	opacity: 0.6;
}

.overlayEdit {
	background-color: #ffffff;
	display: none;
	position: fixed;
	top: 50px;
	left: 35%;
	right: 50%;
	bottom: 40px;
	width: 400px;
	padding: 20px;
	z-index: 9999;
	border-radius: 3px;
	-webkit-box-shadow: 3px 3px 24px 0px rgba(0, 0, 0, 0.59);
	-moz-box-shadow: 3px 3px 24px 0px rgba(0, 0, 0, 0.59);
	box-shadow: 3px 3px 24px 0px rgba(0, 0, 0, 0.59);
}

.overlayEdit .closeBtn {
	position: absolute;
	top: -20px;
	right: 2px;
	font-size: 40px;
	cursor: pointer;
}

.overlayEdit .closeBtn:hover {
	opacity: 0.6;
}

.canvasjs-chart-credit {
	display: none
}
</style>


</head>

<body>
	<jsp:include page="/common/admin/slider_admin.jsp"></jsp:include>




	<!-- CONTENT -->
	<section id="content">
		<jsp:include page="/common/admin/navbar_admin.jsp"></jsp:include>

		<!-- MAIN -->
		<dec:body />





		<footer>
			

		</footer>
		<section>
			<div class="overlayEdit">
				<span class="closeBtnEdit">&times;</span>
				<form action="edit" method="POST">
					<input type="hidden" name="mode" value="edit">
					<div class="form-group">
						<label for="name">ID</label> <input name="productID" type="text"
							id="productID" class="form-control" readonly>
					</div>
					<div class="form-group">
						<label for="name">Name of product</label> <input
							name="productName" type="text" class="form-control" id="editName"
							placeholder="Enter name of product">
					</div>
					<div class="form-group">
						<label for="name">Description</label>
						<textarea rows="2" name="description" class="form-control"
							id="editDescription" placeholder="Enter name of product"></textarea>
					</div>
					<div class="form-group">
						<label for="price">Price</label> <input name="productPrice"
							type="text" class="form-control" id="editPrice"
							placeholder="Enter the price...">
					</div>
					<div class="form-group">
						<label for="quantity">Sale Price</label> <input
							name="productSalePrice" type="text" class="form-control"
							id="editSalePrice" placeholder="Enter the Sale Price">
					</div>
					<div class="form-group">
						<label for="quantity">Categories</label>
						<div class="btn-group btn-group-toggle ml-2" data-toggle="buttons">
							<label class="btn btn-primary active"> <input value="1"
								type="radio" name="editCate" id="value1"> Food
							</label> <label class="btn btn-primary"> <input Value="2"
								type="radio" name="editCate" id="value2"> Drink
							</label>

						</div>
					</div>
					<div class="form-group">
						<input type="file" name="fileX">
					</div>
					<button type="submit" class="btn btn-primary">Change</button>
				</form>
			</div>

			</footer>
			<!-- MAIN -->
		</section>
		<!-- CONTENT -->



		<div id="toasts">
			<input id="message" type="hidden" value="${msg}">
		</div>


		<script src="<c:url value='/template/js/toastmessage.js'/>"></script>

		<script
			src="<c:url value='/template/paging/jquery.twbsPagination.js'/>"></script>
		<script src="<c:url value='/template/js/admin_home.js'/>"></script>
		
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"></script>
		<script src="<c:url value='/template/js/toastmessage.js'/>"></script>
		<script src="https://cdn.canvasjs.com/canvasjs.min.js"></script>
</body>

</html>