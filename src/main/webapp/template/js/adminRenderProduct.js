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
								url : "/admin/api/v1/products",
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
										let salesPrice = formatVND(item.salePrice)
										let price =  formatVND(item.price)
										str+=`<tr>
											<td>${item.id}</td>
											<td>${item.productName}</td>
											<td><img src="${item.listImage[0].imageURL}" alt=""></td>
											<td>${price}</td>
											<td>${salesPrice}</td>

											<td><a style="color:black" href="/admin/product/edit?id=${item.id}" 
												class="edit"><i class="fa fa-pencil mr-4 ml-2 "></i></a>
												<a style="color:black" href="#" onclick="deleteProduct(${item.id})"><i class="fa fa-trash"></i></a></td>
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






	



