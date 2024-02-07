document.addEventListener('DOMContentLoaded', (event) => {
    let minusButton = document.querySelector('.btn-minute');
    let plusButton = document.querySelector('.btn-plus');
    let quantityNumber = document.querySelector('.number');
   
    minusButton.addEventListener('click', function(e) {
        let currentQuantity = parseInt(quantityNumber.textContent, 10);
        if (currentQuantity > 1) { 
            quantityNumber.textContent = currentQuantity - 1;
            const itemElement = e.target.closest('li');
            const productId = itemElement.querySelector('.productIdClass').value;
            const quantity = 1; 
            const quantityDisplay = e.target.closest('.q-inner').querySelector('.number');
            quantityDisplay.textContent = quantityNumber.textContent;
            minusQuanityRelatedProduct(productId);
        }
    });

    plusButton.addEventListener('click', function(e) {
        let currentQuantity = parseInt(quantityNumber.textContent, 10);
        quantityNumber.textContent = currentQuantity + 1;
        const itemElement = e.target.closest('li');
        const productId = itemElement.querySelector('.productIdClass').value;
        const quantity = 1; 
        const quantityDisplay = e.target.closest('.q-inner').querySelector('.number');
        quantityDisplay.textContent = quantityNumber.textContent;
        addToCart(productId, quantity);
       
    });
  
});



let addToCartBtn = document.querySelector('#addToCartBtn');
addToCartBtn.addEventListener('click', function() {
	let productId = document.querySelector('#productCommentId').value;
	let quantity = parseInt(document.getElementById('productQuantity').textContent, 10);
	addToCartDetail(productId,quantity);
})


function addToCartDetail(productId,quantity) {
	  
	$.ajax({
		url : "/api/cart",
		type : "POST",
		data : {
			idP : productId,
			quantity:quantity

		},
		success : function(data) {
			 let quantityNumber = document.querySelector('.number');
			 
			 quantityNumber.textContent = 1;

			$('#count_in_cart').html(data.items.length)
			$('#message').val("success_add_to_cart");
			let msg = document.getElementById("message");
			if (msg.value !== "") {
				createToast(msg.value);
				msg.value = "";
			}

		}

	});

}

document.addEventListener('DOMContentLoaded', function() {
   
    const optionsContainer = document.querySelector('.add-opts');

    optionsContainer.addEventListener('click', function(e) {
     
        const isPlusButton = e.target.classList.contains('btn-plus');
        const isMinusButton = e.target.classList.contains('btn-minute');

        if (isPlusButton || isMinusButton) {
         
            const quantityDisplay = e.target.closest('.q-inner').querySelector('.number');
            let quantity = parseInt(quantityDisplay.textContent, 10);

            if (isPlusButton) {
                quantity++;
              
            } else if (isMinusButton && quantity > 0) { 
                quantity--;
            }

          
            quantityDisplay.textContent = quantity;
        }
     
        if (e.target.classList.contains('btn-plus')) {
        	const itemElement = e.target.closest('li');
            const productId = itemElement.querySelector('.productIdClass').value;
            const quantity = 1; 
          
            addToCart(productId, quantity);
        	   
        }
        
        if (e.target.classList.contains('btn-minute')) {
        	const itemElement = e.target.closest('li');
            const productId = itemElement.querySelector('.productIdClass').value;
            const quantity = 1; 
           
            minusQuanityRelatedProduct(productId);
           
        }
        
    });
    
   
});


function  minusQuanityRelatedProduct(id) {
    $.ajax({
        url: "/api/cart",
        type: "get",
        data: {
            idP: id,
            mode: "minusQuantity"
        },

        success: function (data) {
        	 var listItem = data.items
            
        	
        		$('#count_in_cart').html(data.items.length)

        }
    });
}

function renderQuantityRelatedProduct() {
	$.ajax({
		url : "/api/cart",
		type : "GET",
	
		success : function(data) {
			var listProduct = data.items
			
		
          
			document.querySelectorAll('.list-options.add-opts li').forEach((itemElement) => {
                const productId = itemElement.querySelector('.productIdClass').value;
                const quantityElement = itemElement.querySelector('.number');
              
                const cartItem = listProduct.find(item => item.productDTO.id == productId);
                	


               
                if (cartItem) {
                    quantityElement.textContent = cartItem.quantity;
                } else {
                    quantityElement.textContent = '0';
                }
            });
		}
	
	});

}

window.onload = ()=>{
	renderQuantityRelatedProduct();
}