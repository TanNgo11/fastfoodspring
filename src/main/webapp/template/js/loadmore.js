function loadMoreProduct(type) {
		if(type=='food'){
			var page = Math.ceil(document.getElementsByClassName("foodCard").length / 4);
		}else if(type=='drink') {
			var page = Math.ceil(document.getElementsByClassName("drinkCard").length / 4);
		}else {
			var page = Math.ceil(document.getElementsByClassName("topSales").length / 4);
		}
	
		
				$.ajax({
						url : "/api/v1/products",
						type : "GET",
						data : {
							page : page,
							typeOfProduct : type
						},

						success : function(data) {
							var listResult = data.listResult
							if(listResult.length!=0){
								var type = listResult[0].typeOfProduct
								var row = document.getElementById("rowFood")
								console.log(type)
								var typeCard = "foodCard"
									if(type==="food"){
										var row = document.getElementById("rowFood")
										typeCard = "foodCard"
									}else if(type==="drink"){
										var row = document.getElementById("rowDrink")
										typeCard = "drinkCard"
									}else{
										var row = document.getElementById("topSales")
										typeCard = "topSales"
									}
								
							
								
								
								
								var str = ""
								for (let o of listResult) {
									let tempStr = "";
									let tempStrInStock = "";
									let salesPrice = formatVND(o.salePrice)
									let price =  formatVND(o.price)
									let instock = o.inStock;
									if(o.salePrice < o.price && o.salePrice != 0){
										
										tempStr=`<p class="card-text sale-price">
															${salesPrice}

														</p>
														<p class="card-text price">
															<i style="color: #ff5b6a; margin-right: 5px"
																class='bx bxs-discount bx-flip-horizontal bx-tada'></i>${price}</p>`
									}else{
										tempStr = `<p style="margin-bottom: 60px !important;" class="card-text sale-price">
															${price}
														</p>`
									}
									
									if(instock>0){
										tempStrInStock = `<button style="width: 90%" onClick="addToCart(${o.id})"
															class="btn btn-primary addToCart">Add to cart</button>`
									}else{
										tempStrInStock =`<p class="text-danger"
															style="width: 90%; text-align: center; font-weight: bold;">Out
															of Stock</p>`
									}
									
									
									
									
									str += `<div  style="position: static !important" class="col-md-3 col-6 mt-4 element-item" data-category="food">

										<div class="card h-100 foodCard">
											<a href="/detail/${o.slug}"> <img class="card-img-top"
												src="${o.listImage[0].imageURL}"
												alt="Card image cap">

											</a>

											<div style="height: 239px;" class="card-body">
												<h5 class="card-title name">${o.productName}</h5>
												
												`+`${tempStr}`+`${tempStrInStock}`+`
												
												
												
											</div>
										</div>



									</div>
								` 
								}
								
								row.innerHTML += str
							}
							
							
							

						}
					});

		}

function formatVND(amount) {
	 
	  amount = parseInt(amount, 10);

	  
	  return amount.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
	}

