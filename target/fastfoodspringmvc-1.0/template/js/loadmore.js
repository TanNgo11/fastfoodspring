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
								for (let item of listResult) {
									str+=`<div class="col-md-3 col-12 mt-4">

										<div class="card h-100 ${typeCard}">
											<a href="detail?pid=${item.id}"> <img class="card-img-top"
												src="${item.img}" alt="Card image cap">
											</a>

											<div class="card-body">
												<h5 class="card-title">${item.productName}</h5>
												<p class="card-text">${item.price}$</p>
												<button style="width: 90%" onClick="addToCart(${item.id})"
													class="btn btn-primary addToCart">Add to cart</button>
											</div>
										</div>



									</div>`
								}
								
								row.innerHTML += str
							}
							
							
							

						}
					});

		}



