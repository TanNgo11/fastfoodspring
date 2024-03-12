<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

	<main>
	<div class="table-data">
		<div class="order">
			<div class="head">
				<h3>Add Product</h3>
			</div>


			<div class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="detail-product">

							<div class="table-responsive ps">




								<div class="container">
									<form:form modelAttribute="product"
										enctype="multipart/form-data">
										<div class="form-row">
											<div class="form-group col-md-6">
												<label for="productName">Product Name</label>
												<form:input path="productName" id="productName"
													cssClass="form-control" type="text" />
											</div>
											<div class="form-group col-md-6">
												<label for="id">ID</label>
												<form:input id="id" path="id" cssClass="form-control"
													type="text" readOnly="true" />
											</div>
										</div>
										<div class="form-row">

											<div class="form-group col-md-6">
												<label for="inStock">In Stock</label>
												<form:input id="inStock" path="inStock"
													cssClass="form-control" type="number" />
											</div>
											<div class="form-group col-md-6">
												<label for="slug">Slug</label>
												<form:input path="slug" id="slug" cssClass="form-control"
													type="text" readOnly="true" />
											</div>

										</div>






										<div class="form-row">
											<div class="form-group col-md-4">
												<label for="price">Price</label>
												<form:input id="price" path="price" cssClass="form-control"
													type="number" />
											</div>
											<div class="form-group col-md-4">
												<label for="salePrice">Sale Price</label>
												<form:input path="salePrice" id="salePrice"
													cssClass="form-control" type="number" />
											</div>
											<div class="form-group col-md-4">
												<label for="category">Category</label>
												<form:select path="categoryDTO" id="category"
													cssClass="form-control">
													<form:option value="" label="-- Choose type of product --" />
													<form:options items="${categories}" />
												</form:select>
											</div>
										</div>

										<div class="form-row">
											<div class="form-group col-md-12">
												<label for="description">Description</label>

												<form:textarea path="description" rows="30" cols="10"
													cssClass="form-control" id="description" />
											</div>


										</div>
										<div class="form-row">
											<div class="form-group col-md-12">
												<select multiple name="relatedProductsId"
													id="relatedProductsId">
													<c:forEach var="entry" items="${mapProduct}">
														<option value="${entry.key}">${entry.value}</option>

													</c:forEach>
												</select>
											</div>
										</div>

										<div class="form-row">
											<c:forEach items="${listImg}" var="img">
												<div class="form-group col-md-4 file">
													<label for="exampleFormControlFile1">Picture</label> <input
														type="file" class="form-control-file" accept="image/*"
														onchange="loadFile(this)" name="files"> <img
														class="output"
														src="http://localhost:8080/${img.getImageURL()}" alt=""
														style="width: 300px; margin-top: 10px">
												</div>
											</c:forEach>
											<c:if test="${listImg.size()!=3}">
												<c:forEach var="i" begin="1" end="${3-listImg.size()}">
													<div class="form-group col-md-4 file">
														<label for="exampleFormControlFile1">Picture
															${3-listImg.size()-1+i}</label> <input type="file"
															class="form-control-file" accept="image/*"
															onchange="loadFile(this)" name="files"> <img
															class="output" src="" alt=""
															style="width: 300px; margin-top: 10px">
													</div>
												</c:forEach>
											</c:if>



										</div>


										<div style="text-align: left;">
											<select style="width: 200px" class="custom-select"
												id="action-status">
												<c:forEach var="entry" items="${mapAction}">
													<option value="${entry.key}">${entry.value}</option>

												</c:forEach>
											</select> <input style="width: 200px; display: none" type="text"
												class="form-control" id="datepicker"> <input
												id="btnAddOrUpdateNew" type="submit" class="btn btn-primary"
												value="Proceed">
										</div>





									</form:form>
								</div>








							</div>

						</div>

					</div>

				</div>


			</div>
		</div>
	</main>
	<!-- 
	<script>
	function setPredefinedFileFromUrl(url) {
	    var inputElement = document.querySelector('input[type="file"]');

	    fetch(url)
	        .then(response => response.blob())
	        .then(blob => {
	            var filename = url.substring(url.lastIndexOf('/') + 1);
	            var predefinedFile = new File([blob], filename, { type: "application/octet-stream" });

	            // Create a new FileList
	            var newFileList = new DataTransfer().files;
	            newFileList[0] = predefinedFile;

	            // Use the DataTransfer object to set the files
	            inputElement.files = newFileList;

	            // Trigger the change event on the file input
	            var event = new Event('change', { bubbles: true });
	            inputElement.dispatchEvent(event);
	        })
	        .catch(error => {
	            console.error('Error fetching the file:', error);
	        });
	}

	window.onload = function() {
	    var imageUrl = '/file/productImages/43';
	    setPredefinedFileFromUrl(imageUrl);
	};
</script>

 -->


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
		var formattedDateData;

		$('#btnAddOrUpdateNew').click(function(e) {
			e.preventDefault();
			const editorData = myEditor.getData();
			formData.append("productName", $("#productName").val())
			formData.append("price", $("#price").val())
			formData.append("salePrice", $("#salePrice").val())
			formData.append("category", $("#category").val())
			formData.append("inStock", $("#inStock").val())
			formData.append("status", $("#action-status").val())
			formData.append("publishDate", formattedDateData);
			console.log(formattedDateData)
			
			let jsonString = relatedProductsId.getSelectedOptionsAsJson(true) 
			 let data = JSON.parse(jsonString);
			 let relatedProductsIdArray = data.relatedProductsId.map(id => Number(id));
			
		
			formData.append("relatedProductsId", relatedProductsIdArray)
			

			
			formData.append("description", editorData)

			var id = $('#id').val();

			if (id === "") {
				addProduct(formData);
			} else {

				updateProduct(formData, id);

			}

		});

		function addProduct(formData) {

			$.ajax({
				url : '/admin/api/v1/products',
				type : 'POST',
				data : formData,
				enctype : 'multipart/form-data',
				cache : false,
				contentType : false,
				processData : false,
				success : function(result) {

					window.location.href = "/admin/product/edit?id="
							+ result.id + "&msg=add_success";
				},
				error : function(error) {

					window.location.href = "/admin/product/edit?msg=add_error";
				}
			});
		}

		function updateProduct(formData, id) {

			$.ajax({
				url : '/admin/api/v1/products/product/' + id,
				type : 'POST',
				data : formData,
				enctype : 'multipart/form-data',
				cache : false,
				contentType : false,
				processData : false,
				success : function(result) {
					window.location.href = "/admin/product/edit?id="
							+ result.id + "&msg=update_success";
				},
				error : function(error) {
					console.log(error)

					window.location.href = "/admin/product/edit?msg=update_error";
				}
			});
		}

		const relatedProductsId = $('#relatedProductsId').filterMultiSelect();
		
			  
		
	
			  window.onload = function() {
				    
				    document.getElementById("action-status").addEventListener("change", function() {
				      // Get the value of the selected option
				      var selectedValue = this.value;
				      if(selectedValue==3){
				    	  $('#datepicker').show()
				    	  $('#datepicker').css('display', 'inline-block');
				    	  $('#datepicker').datetimepicker();
				    		$('#datepicker').datetimepicker().on("dp.change",function() {
				    			var momentDate =$('#datepicker').data("DateTimePicker").viewDate()
				    			var formattedDate = momentDate.format('YYYY-MM-DD HH:mm:ss');
				    			formattedDateData  = formattedDate
				    		



				    			})


				        } else {
				           
				            $('#datepicker').hide();
				        }
				    	 
				    });
				  };
		

		
		
	</script>

</body>
</html>