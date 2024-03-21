<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="style.css">
<script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- bootstrap 4 -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<body>
	<main>
	<div class="head-title">
		<div class="left">
			<h1>Dashboard</h1>
			<ul class="breadcrumb">
				<li><a href="/admin/home">Dashboard</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a href="/admin/home">News</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a class="active" href="">edit</a></li>
			</ul>
		</div>

	</div>

	<div class="news-edit">
		<form:form modelAttribute="news" enctype="multipart/form-data">
			<div class="table-data">
				<div class="order">
					<h3>Title</h3>
					<div class="row">
						<div class="col-md-8 main-edit-news">


							<div class="input-group mb-3">
								<form:input path="title" type="text" class="form-control"
									placeholder="Enter New Post here" />
							</div>

							<div class="form-group">
								<h4>Content</h4>
								<form:textarea path="description" ccsClass="form-control"
									rows="50" id="description" />
							</div>



						</div>

						<div style="width: 356px" class="ml-3 sidebar-edit-news">

							<div class="features col-md-12 p-4">
								<h4>Features</h4>

								<div class="form-group">
									<div class="row">



										<div class="col-md-12">
											<label for="id">ID:</label>
											<form:input
												style="max-width: 200px;padding-left:12px;margin-left:32px"
												path="id" type="text" readOnly="true" />
										</div>
									</div>
								</div>


								<div class="form-group">
									<div class="row">

										<div class="col-md-12">
											<label for="id">Slug:</label>
											<form:input
												style="max-width: 200px;padding-left:12px;    margin-left: 14px;"
												path="slug" type="text" readOnly="true" />
										</div>

									</div>



								</div>
								<div class="form-group">

									<div style="text-align: left;">
										<label for="exampleFormControlFile1">Status: </label> <select
											style="width: 200px" class="custom-select" id="action-status">
											<c:forEach var="entry" items="${mapAction}">
												<option value="${entry.key}">${entry.value}</option>

											</c:forEach>
										</select> 
										<label style="margin-top: 20px" for="exampleFormControlFile1">Schedule:
										</label> <input
											style="display: none; background-color: white; border: 1px solid black;"
											type="text" class="form-control" id="datepicker">
									</div>

								</div>

								<div class="form-group ">
									<label>Thumbnail</label>
									<div class="drag-image" data-image-url="${news.getImageURL()}">
										<div class="icon">
											<i class="fas fa-cloud-upload-alt"></i>
										</div>
										<h6>Drag & Drop File Here</h6>
										<span>OR</span>
										<button>Browse File</button>
										<input type="file" hidden>
									</div>
								</div>
								<input id="btnAddOrUpdateNews" type="submit"
									class="btn btn-primary" value="Submit">

							</div>


						</div>


					</div>
				</div>
			</div>
		</form:form>

	</div>
	</main>
	<script src="/template/js/dragimage.js"></script>
	<script>
		var myEditor = CKEDITOR.replace("description");

		CKFinder.setupCKEditor(myEditor,
				'${pageContext.request.contextPath}/template/ckfinder');

		var formData = new FormData();
		var formattedDateData;
		$('#btnAddOrUpdateNews').click(function(e) {
			e.preventDefault();
			const editorData = myEditor.getData();
			formData.append("title", $("#title").val())
			formData.append("description", editorData)
			formData.append("status", $("#action-status").val())

			if (formattedDateData) {
				formData.append("publishDate", formattedDateData);
			}
			

			var id = $('#id').val();
			if (id === "") {
				addNews(formData);
			} else {

				updateNews(formData, id);

			}

		});

		function addNews(formData) {

			$.ajax({
				url : '/admin/api/v1/news',
				type : 'POST',
				data : formData,
				enctype : 'multipart/form-data',
				cache : false,
				contentType : false,
				processData : false,
				success : function(result) {

					window.location.href = "/admin/news/edit?id=" + result.id
							+ "&msg=add_success";
				},
				error : function(error) {

					window.location.href = "/admin/news/edit?msg=add_error";
				}
			});
		}

		function updateNews(formData, id) {

			$.ajax({
				url : '/admin/api/v1/news/' + id,
				type : 'POST',
				data : formData,
				enctype : 'multipart/form-data',
				cache : false,
				contentType : false,
				processData : false,
				success : function(result) {

					window.location.href = "/admin/news/edit?id=" + result.id
							+ "&msg=success_update";
				},
				error : function(error) {

					window.location.href = "/admin/news/edit?msg=update_error";
				}
			});
		}
		window.onload = function() {

			document.getElementById("action-status").addEventListener(
					"change",
					function() {
						// Get the value of the selected option
						var selectedValue = this.value;
						if (selectedValue == 3) {
							$('#datepicker').show()
							$('#datepicker').css('display', 'inline-block');
							$('#datepicker').datetimepicker();
							$('#datepicker').datetimepicker().on(
									"dp.change",
									function() {
										var momentDate = $('#datepicker').data(
												"DateTimePicker").viewDate()
										var formattedDate = momentDate
												.format('YYYY-MM-DD HH:mm:ss');
										formattedDateData = formattedDate

									})

						} else {

							$('#datepicker').hide();
						}

					});
		};
	</script>
</body>

</html>