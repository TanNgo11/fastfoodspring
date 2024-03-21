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
	<div class="head-title">
		<div class="left">
			<h1>Dashboard</h1>
			<ul class="breadcrumb">
				<li><a href="#">Dashboard</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a class="active" href="/admin/categories">Category</a></li>
			</ul>
		</div>

	</div>





	<div class="row">
		<div class="col-md-6">
			<div class="table-data">
				<div class="order">
					<div class="head">
						<h3>Category list</h3>


					</div>

					<table>
						<thead>
							<tr>
								<th style="width: 50px">#</th>
								<th>Category Name</th>
								<th>Status</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody id="contentTable">






						</tbody>

					</table>
					<ul id="pagination" class="pagination"></ul>






				</div>

			</div>
		</div>

		<div class="col-md-6">
			<div class="table-data">
				<div class="order">
					<div style="justify-content: flex-end" class="head">
						<a href="#" id="addNewCategory"> <i
							class="fa fa-plus-circle fa-2x mr-2"></i> <span>Add new
								category</span>
						</a>
					</div>


					<div class="form-row">
						<div class="col-md-12 mb-3">
							<div class="row">
								<div class="col-md-6">
									<label for="categoryName">Category Name</label>
								</div>
								<div class="col-md-6">
									<input type="text" class="form-control" id="categoryName"
										placeholder="Category Name" />
									<div class="valid-feedback">Looks good!</div>
									<div class="invalid-feedback">Please choose a name.</div>
								</div>
							</div>

						</div>



					</div>






				</div>


			</div>



		</div>

	</div>

	</main>

	<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Edit Category</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">Category
								Name:</label> <input type="text" class="form-control"
								id="recipient-name">
						</div>

						<div class="form-group">
							<label for="exampleFormControlFile1">Status: </label> <select
								class="custom-select" id="action-status">
								<c:forEach var="entry" items="${mapAction}">
									<option value="${entry.key}">${entry.value}</option>

								</c:forEach>
							</select>
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button id="editBtn" type="button" class="btn btn-primary">Change</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		$('#addNewCategory').click(function(e) {
			e.preventDefault();

			var data = {};

			var categoryName = $('#categoryName').val().trim();

			data["type"] = categoryName

			console.log(data)

			addNewCategory(data)

		});
		function addNewCategory(data) {
			$.ajax({
				url : '/admin/api/v1/category',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					$('#message').val("success_add");
					let msg = document.getElementById("message");
					if (msg.value !== "") {
						createToast(msg.value);
						msg.value = "";
					}
					renderCategories()
				},
				error : function(error) {
					$('#message').val("error_add");
					let msg = document.getElementById("message");
					if (msg.value !== "") {
						createToast(msg.value);
						msg.value = "";
					}

				}
			});
		}

		function deleteCategory(id, categoryName) {

			$.confirm({
				title : 'Delete Confirmation!',
				content : 'Do you want to delete the category: ' + categoryName
						+ '?',
				buttons : {
					confirm : function() {
						$.alert('Confirmed!');
						doDeleteById(id)
					},
					cancel : function() {
						$.alert('Canceled!');
					}
				}
			});

		}
		function doDeleteById(id) {
			$.ajax({
				url : '/admin/api/v1/category/' + id,
				type : 'DELETE',
				contentType : 'application/json',
				success : function(result) {

					createToast("success_delete");
					document.getElementById("message").value = ""
					window.onload = renderCategories();
				},
				error : function(error) {
					document.getElementById("message").value = "error_delete"
					let msg = document.getElementById("message");
					createToast(msg.value);
					msg.value = "";
				}
			});
		}
		var currentCategoryId;
		var status;
		$('#editModal').on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget)
			var recipient = button.data('whatever')
			var categoryId = button.data('id');
			currentCategoryId = categoryId;
			status = $("#action-status").val();
			var modal = $(this)
			modal.find('.modal-title').text('Edit category: ' + recipient)
			modal.find('.modal-body input').val(recipient)
		})

		$("#editBtn").click(function() {

			var newName = $('#recipient-name').val().trim();
			if (newName.length > 0) {
				updateCategoryName(currentCategoryId, newName, status);
			}
		});

		function updateCategoryName(categoryId, newName, status) {
			console.log(status)
			$.ajax({
				url : '/admin/api/v1/categories/' + categoryId + "?type="
						+ newName + "&status=" + status,
				type : 'PUT',
				success : function(result) {
					$('#editModal').modal('hide');
					createToast("success_change");
					document.getElementById("message").value = ""
					window.onload = renderCategories();
				},
				error : function(error) {

					console.error("Error updating category:", error);
				}
			});
		}
	</script>
	<script src="/template/js/adminRenderCategory.js"></script>




</body>
</html>