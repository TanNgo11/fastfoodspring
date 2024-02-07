
var totalPayment = $("#totalPayment").val()


var formatter = new Intl.NumberFormat('vi-VN', {
			    style: 'currency',
			    currency: 'VND',
			});


function validateForm() {
    var phoneNumber = document.getElementById('validationCustomPhoneNumber').value;
    var email = document.getElementById('validationCustomEmail').value;
    var address = document.getElementById('result').value;
    
  
    var phoneRegex = /^0\d{9,10}$/;
    if (!phoneRegex.test(phoneNumber)) {
    	let msg = document.getElementById("message");
		msg.value= "error_validation_phonenumber"
		if(msg.value!==""){
			createToast(msg.value);
			msg.value = "";	
		}
        return false;
    }
    
   
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
    	let msg = document.getElementById("message");
		msg.value= "error_validation_email"
		if(msg.value!==""){
			createToast(msg.value);
			msg.value = "";	
		}
        
        return false;
    }
    
  
    if (address.trim() === "") {
    	let msg = document.getElementById("message");
		msg.value= "error_validation_address"
		if(msg.value!==""){
			createToast(msg.value);
			msg.value = "";	
		}
        return false;
    }
    
    return true; 
}


$("#submitOrder").on("click", function(e){
    e.preventDefault();
    totalPayment = $("#totalPayment").val();
	   let  email = $("#validationCustomEmail").val();
	   let phonenumber = $("#validationCustomPhoneNumber").val();
	   let address = $("#result").val();
	  
	   
	    
	   
    $.ajax({
        url: "/api/v1/payment/neworder",
        type: "POST",
        data: {
            amount: totalPayment,
            email:email,
            phonenumber:phonenumber,
            address:address
            
            
        },
        success: function(href) {
        	if($("#cartItem").val()==0||  $("#totalPayment").val()==0){
        		let msg = document.getElementById("message");
				msg.value= "error_empty"
				if(msg.value!==""){
					createToast(msg.value);
					msg.value = "";	
				}
        	}
        	
        	
        	else if($("#userId").val()!="" &&  $("#totalPayment").val()!==0){
        		if(validateForm()){
        			window.location.href = href;
        		}
    	
        	}
        	
        	else{
        		
				let msg = document.getElementById("message");
				msg.value= "error_login"
				if(msg.value!==""){
					createToast(msg.value);
					msg.value = "";	
				}
        	}
          
        }
    });
});


function minusCountInCart() {
    
    var data = $('.cc_cart_count_child').html();
    
    data--;
    $('.cc_cart_count_child').html(data);
    
}

function renderSumary() {
    
    $.ajax({
        url: "/api/cart",
        type: "GET",
        success: function (data) {
            var listItem = data.items
            doRenderSummary(listItem)
            renderTotalPayment(data)
            totalPayment = data.totalPay
          
        }
    });
    
}
function doRenderSummary(listItem) {
	   let row = document.getElementById("summary-content");
	   if(row!=null){
		   var str=""
				for (let item of listItem) {
					
					let price = formatter.format(item.productDTO.price);
					if(item.productDTO.salePrice!=0 &&item.productDTO.salePrice<item.productDTO.price){
						price = formatter.format(item.productDTO.salePrice);
					}
					str+=`<div class="row no-gutters mt-3">
		         <div class="col-md-6 col-6">${item.productDTO.productName}</div>
		         <div class="col-md-3 col-3">${item.quantity}</div>
		         <div class="col-md-3 col-3">${price}</div>
		         </div>`
				}
	   }
	 row.innerHTML = str;
}

function  doFunctionInCartByMode(id, mode) {
    $.ajax({
        url: "/api/cart",
        type: "get",
        data: {
            idP: id,
            mode: mode
        },

        success: function (data) {
        	 var listItem = data.items
             doRenderSummary(listItem)
        	 renderTotalPayment(data)
        	 doRenderProduct(listItem)
        	 $("#totalPayment").val(data.totalPay)
        	
        		$('#count_in_cart').html(data.items.length)

        }
    });
}

function  renderTotalPayment(data){
	 var content = document.getElementById("totalPay");
    if(content!=null){
    	let totalPrice = formatter.format(data.totalPay)
    	content.innerHTML = `<h5>${totalPrice}</h5>`
    }
}




function renderProducts() {
	$.ajax({
		url : "/api/cart",
		type : "GET",
	
		success : function(data) {
			var listProduct = data.items
			 $("#totalPayment").val(data.totalPay)
			doRenderProduct(listProduct)
		}
	
	});

}
function doRenderProduct(listProduct) {
	var row = document.getElementById("content");
	if(row !=null){
		var str = ""
			
		for (let item of listProduct) {
			var type ="Food"
				if(item.productDTO.categoryID ===2){
					type ="Drink"
				}
			let price = formatter.format(item.productDTO.price);
			if(item.productDTO.salePrice!=0 &&item.productDTO.salePrice<item.productDTO.price){
				price = formatter.format(item.productDTO.salePrice);
			}
			
			
			
			
	
			str+=`<div class="row mb-4 d-flex justify-content-between align-items-center customDetail">
            <div class="col-md-2 col-4 col-lg-2  col-xl-2">
                <img src="${item.productDTO.listImage[0].imageURL}" class="mb-3 img-fluid" alt="">
            </div>
            <div class="col-md-3 col-8 col-lg-3  col-xl-3 hrbefore">
                <h6 class="text-muted cart-name">${type}</h6>
                <h6 class="text-black mb-3 cart">${item.productDTO.productName}</h6>
            </div>

            <div class="col-md-3 col-5 col-lg-3 col-xl-2 d-flex">
                <button class="btn btn-link px-2"
                    onclick="this.parentNode.querySelector('input[type=number]').stepDown(); doFunctionInCartByMode(${item.productDTO.id},'minusQuantity')">
                    <i class="fa fa-minus"></i>
                </button>

                <input style="width: 50px;" id="form1" min="0" name="quantity"
                    value="${item.quantity}" type="number" class="form-control form-control-sm" />

                <button class="btn btn-link px-2"
                    onclick="this.parentNode.querySelector('input[type=number]').stepUp(); doFunctionInCartByMode(${item.productDTO.id},'plusQuantity')">
                    <i class="fa fa-plus"></i>
                </button>
            </div>
            <div class="col-md-3 col-4 col-lg-2  col-xl-2 offset-lg-1 ">
                <h6 class="mb-0">${price}</h6>
            </div>
            <div style="cursor: pointer;" class="col-md-1 col-3 col-lg-1 col-xl-1 text-end ">
                <a onclick="doFunctionInCartByMode(${item.productDTO.id},'delete'); minusCountInCart()" class="text-muted"><i class="fa fa-trash-o  "></i></a>
            </div>
        </div>
        <hr class="my-4">`
		}
		row.innerHTML = str
		
		
		
	}
	
}
window.onload = ()=>{
	renderProducts()
	renderSumary()
}







