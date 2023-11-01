var homeConfig = {
		pageSize : 10,
		currentPage : 1
}

window.onload= renderProducts();


 function renderProducts() {
	
						$.ajax({
								url : "/admin/api/v1/orders",
								type : "GET",
								data:{
									page:homeConfig.currentPage,
									limit:homeConfig.pageSize
								},
								success : function(data) {
									var totalPages = data.totalPage;
									var currentPage = data.currentPage
									 
									var listResult = data.listResult
									var row = document.getElementById("contentTable")
									
									var str = ""
									for (let item of listResult) {
										str+=`<tr>
											<td>${item.id}</td>
											<td>${item.customerName}</td>
											<td>${item.address}</td>
											<td>${item.email}$</td>
											<td>${item.phonenumber}$</td>
											<td>${item.totalPay}$</td>

											<td><a href="#" onclick="viewProduct(${item.id})"
												class="edit"><i class="fa fa-pencil mr-4 ml-2 "></i></a>
												<a href="#" onclick="deleteProduct(${item.id})"><i class="fa fa-trash"></i></a></td>
										</tr>`
									}
									paging(totalPages,currentPage,function(){
										renderProducts()
									});
									
									row.innerHTML = str
									
									

								}
							});
	

				}


function paging(totalPages,currentPage,callback) {
	
		 $('#pagination').twbsPagination({
			totalPages : totalPages,
			visiblePages : 10,
			startPage : currentPage,
			onPageClick : function(event, page) {
				homeConfig.currentPage = page;
				setTimeout(callback, 100);
			}
		});
	
}






	



