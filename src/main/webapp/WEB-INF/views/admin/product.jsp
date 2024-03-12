<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main>
	<div class="head-title">
		<div class="left">
			<h1>Dashboard</h1>
			<ul class="breadcrumb">
				<li><a href="/admin/home">Dashboard</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a class="active" href="/admin/products">Product</a></li>
			</ul>
		</div>
		<a href="#" class="btn-download"> <i class='bx bxs-cloud-download'></i>
			<span class="text">Download PDF</span>
		</a>
	</div>



	<div class="table-data">
		<div class="order">
			<div class="head">
				<h3>Product list</h3>
				<i class='bx bx-search'></i> <i class='bx bx-filter'></i>
				<!-- add Product -->
				<a  href="/admin/product/edit" id="add"> <i class="fa fa-plus-circle fa-2x mr-2"></i>
					<span>Add new product</span>
				</a>
			</div>





			<table >
				<thead>
					<tr>
						<th>ID</th>
						<th>Product Name</th>
						<th>Image</th>
						<th>Price</th>
						<th>Sale Price</th>
						<th>Functions</th>
					</tr>
				</thead>
				<tbody id="contentTable">






				</tbody>

			</table>
			<ul id="pagination" class="pagination"></ul>






		</div>

	</div>

	</main>
	<script src="/template/js/adminRenderProduct.js"></script>

	<script>
		function deleteProduct(id) {

			$.confirm({
				title : 'Confirm!',
				content : 'Simple confirm!',
				buttons : {
					confirm : function() {
						$.alert('Confirmed!');
						doDeleteById(id)
					},
					cancel : function() {
						$.alert('Canceled!');
					},
					somethingElse : {
						text : 'Something else',
						btnClass : 'btn-blue',
						keys : [ 'enter', 'shift' ],
						action : function() {
							$.alert('Something else?');
						}
					}
				}
			});

		}
		function doDeleteById(id) {
			$.ajax({
				url : '/admin/api/v1/products/' + id,
				type : 'DELETE',
				contentType : 'application/json',
				success : function(result) {
					document.getElementById("message").value = "success_delete"
					window.onload = renderProducts();
				},
				error : function(error) {
					document.getElementById("message").value = "error_delete"
					let msg = document.getElementById("message");
					createToast(msg.value);
					msg.value = "";
				}
			});
		}
	</script>

</body>
</html>