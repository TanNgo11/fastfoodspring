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
	<div class="table-data">
		<div class="order">
			<div class="head">
				<h3>Product list</h3>
				<!-- add Product -->
				<a href="#" id="add"> <i class="fa fa-plus-circle fa-2x mr-2"></i>
					<span>Add new product</span>
				</a>
			</div>


			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="detail-product">

							<div class="table-responsive ps">

								<table class="table table-hover size">
									<thead>
										<tr>
											<th>ID</th>
											<th>Name</th>
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

					</div>

				</div>


			</div>
		</div>
	</main>
	<script src= "/template/js/adminRenderProduct.js"></script>
	
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
	        url: '/admin/api/v1/products/' + id,
	        type: 'DELETE',
	        contentType: 'application/json',
	        success: function (result) {
	        	document.getElementById("message").value = "success_delete"
	        	window.onload= renderProducts();
	        },
	        error: function (error) {
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