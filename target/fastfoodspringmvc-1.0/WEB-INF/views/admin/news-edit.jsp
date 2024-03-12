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
	<div class="container">
		<div class="row">
			<form:form modelAttribute="news" enctype="multipart/form-data">
				<div class="col-md-8">
					<div class="input-group mb-3">
						<div class="input-group-prepend">
							<span class="input-group-text">Title</span>
						</div>
						<form:input id="title" path="title" type="text"
							cssClass="form-control" />
					</div>
					<div class="form-group">
						<label for="description">Editor:</label>
						<form:textarea path="description" cssClass="form-control"
							rows="50" id="description" />
					</div>

				</div>
				<div class="col-md-4 p-3 mb-2 bg-primary text-white">
					<input id="btnAddOrUpdateNews" type="submit"
						class="btn btn-primary" value="Submit">
					<div class="form-group col-md-6">
						<label for="id">ID</label>
						<form:input id="id" path="id" cssClass="form-control" type="text"
							readOnly="true" />
					</div>
					<div class="form-group col-md-4 file">
						<label for="exampleFormControlFile1">Picture</label> <input
							type="file" class="form-control-file" accept="image/*"
							onchange="loadFile(this)" name="files"> <img
							class="output" src="" alt=""
							style="width: 300px; margin-top: 10px">
					</div>

					<div class="form-group col-md-6">
						<label for="slug">Slug</label>
						<form:input path="slug" id="slug" cssClass="form-control"
							type="text" readOnly="true" />
					</div>





				</div>
			</form:form>
		</div>



	</div>
	<script>
		var myEditor = CKEDITOR.replace("description");

		CKFinder.setupCKEditor(myEditor,
				'${pageContext.request.contextPath}/template/ckfinder');

		var formData = new FormData();
		var loadFile = function(obj) {
			var obj = obj.parentElement.getElementsByClassName("output")
			var output = obj[0]
			output.src = URL.createObjectURL(event.target.files[0]);
			formData.append("files", event.target.files[0])
			output.onload = function() {
				URL.revokeObjectURL(output.src)

			}
		};

		$('#btnAddOrUpdateNews').click(function(e) {
			e.preventDefault();
			const editorData = myEditor.getData();
			formData.append("title", $("#title").val())
			formData.append("description", editorData)

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
	</script>
</body>
</html>