var homeConfig = {
		pageSize : 10,
		currentPage : 1
}

window.onload= renderCategories();


 function renderCategories() {
	
						$.ajax({
								url : "/admin/api/v1/categories",
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
									
									let noStart = 1 * homeConfig.currentPage
		
									if(homeConfig.currentPage!=1)
										noStart = homeConfig.pageSize * (homeConfig.currentPage - 1) +1
									for (let item of listResult) {
										let status = "Publish"
										if(item.status==0){
											 status = "Inactive"
										}
										
										str+=`<tr>
											<td>${noStart++}</td>
											<td>${item.type}</td>
											<td>${status}</td>
											<td><a style="color:black" href="/admin/users/${item.id}" 
												class="edit"><i class="fa fa-pencil mr-4 ml-2 "></i></a>
												<a style="color:black" href="#" onclick="deleteCategory(${item.id}, '${item.type}')"><i class="fa fa-trash"></i></a>
												
												</td>
										</tr>`
									}
									paging(totalPages,currentPage,function(){
										renderCategories()
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






	



