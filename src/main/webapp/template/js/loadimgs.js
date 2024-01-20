var listImgValues = $(".listimg").map(function() {
  return $(this).val();
}).get();

var strThumbList ="";
var strFeaturedList=""
var strDots=""
var i =1;
for (let img of listImgValues) {
	
	strFeaturedList+=`<li>
								<figure>
									<img
										src="${img}"
										alt="">
								</figure>
							</li>
							`
		
		strThumbList+=`<li><label for="image${i}"> <img
								src="${img}"
								alt="">

						</label></li>
						
					`
			strDots+=`<li><label for="image${i}"></label></li>`
				
				
			i++;	
}

$(".featured-list").html(strFeaturedList)
$(".thumb-list").html(strThumbList)
$(".dots").html(strDots)
$(".arrows").html(strDots)




