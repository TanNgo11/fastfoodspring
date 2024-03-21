var homeConfig = {
		pageSize : 10,
		currentPage : 1
}

window.onload= renderProducts();

function formatVND(amount) {
	 
	  amount = parseInt(amount, 10);

	  
	  return amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
	}

 function renderProducts() {
	
						$.ajax({
								url : "/admin/api/v1/draft/products",
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
										console.log(listResult)
									for (let item of listResult) {
										let status = "Pending";
										let statusCSS = "product-status-remove";
										if(item.status==0){
											 status = "Removed";
											 statusCSS = "order-status-canceled"
										}else if(item.status==3){
											 status = "Schedule";
											 statusCSS = "product-status-schedule"
										}
										let salesPrice = formatVND(item.salePrice)
										let price =  formatVND(item.price)
										
										str+=`<tr>
											<td>${item.id}</td>
											<td>${item.productName}</td>
											<td><img src="http://localhost:8080/${item.listImage[0].imageURL}" alt=""></td>
											<td>${item.price}</td>
											<td>${item.salePrice}</td>
											<td><span class="order-status ${statusCSS}">${status}</span></td>
												
											<td><a style="color:black" href="/admin/product/edit?id=${item.id}" 
												class="edit"><i class="fa fa-pencil mr-4 ml-2 "></i></a>
												</td>
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






	



