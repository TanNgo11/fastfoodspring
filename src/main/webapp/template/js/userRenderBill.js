var homeConfig = {
		pageSize : 10,
		currentPage : 1
}

window.onload= renderOrders();


 function renderOrders() {
	
						$.ajax({
								url : "/api/v1/orders",
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
									
									
									let noStart = 1 * homeConfig.currentPage
		
									if(homeConfig.currentPage!=1)
										noStart = homeConfig.pageSize * (homeConfig.currentPage - 1) +1
									for (let item of listResult) {
										let date = formatDate(item.createdDate)
										let totalPay = formatToVND(item.totalPay)
										let status = "Pending";
										let statusCSS = "order-status-pending";
										if(item.status==0){
											 status = "Canceled";
											 statusCSS = "order-status-canceled"
										}else if(item.status==1){
											 status = "Complete";
											 statusCSS = "order-status-complete"
										}else if(item.status==2){
											 status = "Delivery";
											 statusCSS = "order-status-delivery"
										}
										str+=`<tr>
											<td>${noStart++}</td>
											<td>${item.id}</td>
											<td>${item.customerName}</td>
											<td>${date}</td>
											<td>${totalPay}</td>
												<td><span class="order-status ${statusCSS}">${status}</span></td>

											<td><a style="color:black" href="/bill/${item.id}" 
												class="edit"><i class="fa fa-pencil mr-4 ml-2 "></i></a>
												</td>
										</tr>`
									}
									paging(totalPages,currentPage,function(){
										renderOrders()
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

function formatDate(timestamp) {
    const date = new Date(timestamp);
    const year = date.getFullYear();
    const month = date.getMonth() + 1; 
    const day = date.getDate();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();

   
    const formattedMonth = month < 10 ? `0${month}` : month;
    const formattedDay = day < 10 ? `0${day}` : day;
    const formattedHours = hours < 10 ? `0${hours}` : hours;
    const formattedMinutes = minutes < 10 ? `0${minutes}` : minutes;
  

    // Combine into desired format: YYYY-MM-DD HH:MM:SS
    return `${year}-${formattedMonth}-${formattedDay} ${formattedHours}:${formattedMinutes}`;
}
function formatToVND(number) {
    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(number);
}






	



