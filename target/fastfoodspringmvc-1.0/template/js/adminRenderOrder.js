var homeConfig = {
		pageSize : 10,
		currentPage : 1
}
const date = new Date();

const year = date.getFullYear();
const month = getMonthName(date.getMonth() + 1);

function getMonthName(monthNumber) {
	 
	  return date.toLocaleString('en-US', {
	    month: 'long',
	  });
	}


window.onload= renderProducts(month,year);


 function renderProducts(month,year) {
	 
	
						$.ajax({
								url : "/admin/api/v1/orders",
								type : "GET",
								data:{
									month:month,
									year:year,
									page:homeConfig.currentPage,
									limit:homeConfig.pageSize
									
								},
								success : function(data) {
									
									displayData(data)
									
									let totalPages = data.totalPage;
									let currentPage = data.page
					 	
									paging(totalPages,currentPage,function(){
										renderProducts(month,year);
										
									});
								},
								error: function(data) {
									let msg = document.getElementById("message");
									if (msg.value == "") {
										createToast("error_notfound");
										msg.value = "";
									}

								}
							});
	

				}
 
 
 function renderOrdersWithStatus(month,year,status) {
	 
		
		$.ajax({
				url : "/admin/api/v1/orders",
				type : "GET",
				data:{
					month:month,
					year:year,
					page:homeConfig.currentPage,
					limit:homeConfig.pageSize,
					status:status
				},
				success : function(data) {
					
					displayData(data)
					let totalPages = data.totalPage;
					let currentPage = data.page
	 	
					paging(totalPages,currentPage,function(){
						renderOrdersWithStatus(month,year,status);
						
					});
					

				}
			});


}
 
 function displayData(data){
	 	
		 
		var listResult = data.listResult
		var row = document.getElementById("contentTable")
		
		
		let noStart = 1 * homeConfig.currentPage
		
		if(homeConfig.currentPage!=1)
			noStart = homeConfig.pageSize * (homeConfig.currentPage - 1) +1
		

		
		var str = ""
		for (let item of listResult) {
			let date = formatDate(item.createdDate);
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
				
			str+=`<tr onclick="navigateToOrderDetails('${item.id}')">
				<td>${noStart}</td>
				<td>${item.id}</td>
				<td>${item.customerName}</td>
				<td>${item.address}</td>
				<td>${date}</td>
				<td>${totalPay}</td>
				<td><span class="order-status ${statusCSS}">${status}</span></td>

				
			</tr>`
				noStart++;
		}
	 	
		
		
		row.innerHTML = str
 }
// <td><a href="#" onclick="viewProduct(${item.id})"
// class="edit"><i class="fa fa-pencil mr-4 ml-2 "></i></a>
// <a href="#" onclick="deleteProduct(${item.id})"><i class="fa
// fa-trash"></i></a></td>
 function formatToVND(number) {
	    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(number);
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



function paging(totalPages,currentPage,callback) {
	
	 $('#pagination').twbsPagination('destroy');
    if ($('#pagination').data('twbs-pagination') && homeConfig.currentPage === currentPage) {
        return;
    }

    $('#pagination').twbsPagination('destroy');
    $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 10,
        startPage: currentPage,
        onPageClick: function (event, page) {
           
            if (homeConfig.currentPage !== page) {
                homeConfig.currentPage = page;
              
                setTimeout(callback, 100);
            }
        }
    });

	
}
$("#inlineFormCustomSelect").on("change", function() {
	var str = "";
	$("select option:selected").each(function() {
		str += $(this).text() + " ";
	});
	const monthAndYear = str.split(" ");
	renderProducts(monthAndYear[0],monthAndYear[1]);
	
	const exportExcelLink = document.getElementById('exportExcelLink');


	exportExcelLink.href = `/admin/api/v1/orders/excel?month=${monthAndYear[0]}&year=${monthAndYear[1]}`;
	
}).trigger("change");




var socket = new SockJS('/ws');
var stompClient = Stomp.over(socket);

stompClient.connect({}, function (frame) {
    stompClient.subscribe('/topic/orders', function (order) {
        var orderData = JSON.parse(order.body);
        
        
 if(homeConfig.currentPage === 1){
	
	 renderProducts(month,year);
 }

      
        
    });
});

$('.nav-head span').click(function() {
   
    $('.nav-head span').removeClass('active');
    
   
    $(this).addClass('active');
    
    let orderStatus = $(this).text();
    homeConfig.currentPage = 1;
	
    if(orderStatus=="Completed"){
    	renderOrdersWithStatus(month,year,1)
    }else if(orderStatus=="Delivery"){
    	renderOrdersWithStatus(month,year,2)
    }else if(orderStatus=="Cancel"){
    	renderOrdersWithStatus(month,year,0)
    }else if(orderStatus=="Pending"){
    	renderOrdersWithStatus(month,year,3)
    }else 
    	renderProducts(month,year);
    
   
    	
});


function navigateToOrderDetails(orderId) {
   
    window.location.href = `/admin/order/${orderId}`;
    
}











	



