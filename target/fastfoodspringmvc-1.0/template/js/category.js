var categoryName = null;
function loadByCategory(categoryName) {

	$.ajax({
		url : "/api/v1/products/category/" + categoryName,
		type : "get",
		
		success : function(data) {
			let res = data.listResult;
			let str = "";
			for (let o of res) {
				str += `<div  style="position: static !important" class="col-md-3 col-12 mt-4 element-item" data-category="food">

					<div class="card h-100 foodCard">
						<a href="detail?pid=${o.id}"> <img class="card-img-top"
							src="http://localhost:8080/${o.listImage[0].imageURL}"
							alt="Card image cap">

						</a>

						<div class="card-body">
							<h5 class="card-title name">${o.productName}</h5>
							<p style="font-size: 16px!important" class="card-text price">$${o.price}</p>
							<button style="width: 90%" onClick="addToCart(${o.id})"
								class="btn btn-primary addToCart">Add to cart</button>
						</div>
					</div>



				</div>
			` 

		
			}
			$("#rowFood").html(str);
			setTimeout(() => {
				isotope()
			}, 100);
			
		}

	});

}
window.onload = ()=>{
	categoryName = getCategoryNameFromUrl()
	loadByCategory(categoryName)
	loadBreadCrumb()
	loadTypeOfProductTitle()
}

function loadBreadCrumb(){
	$("#breadcrumb-data").html(getCategoryNameFromUrl().charAt(0).toUpperCase() + getCategoryNameFromUrl().slice(1))
}
function loadTypeOfProductTitle(){
	$(".title").html(getCategoryNameFromUrl().charAt(0).toUpperCase() + getCategoryNameFromUrl().slice(1))
}


function getCategoryNameFromUrl(){
	
	const currentUrl = window.location.href;

	const urlParts = currentUrl.split('/');
	const category = urlParts[urlParts.indexOf('category') + 1];

	return category;
}


function isotope(){
	
	var $grid = $('.grid').isotope({
		itemSelector : '.element-item',
		layoutMode : 'fitRows',
		getSortData : {
			name : '.name',
			price : function(itemElem) {
				var priceText = $(itemElem).find('.price').text();
				var match = priceText.match(/\$(\d+)/);

				
				if (match) {
				    var price = parseFloat(match[1]);
				    console.log(price);
				   
				return price;
				}
			
			}
		}
	});
	
	
	$('#sorts').on('change', function () {
        var sortByValue = $(this).find(':selected').data('sort-by');
        $grid.isotope({
            sortBy: sortByValue
        });
       
    });
	
}

/*
 * jQuery Nice Select - v1.0
 * https://github.com/hernansartorio/jquery-nice-select Made by HernÃ¡n Sartorio
 */
!function(e){e.fn.niceSelect=function(t){function s(t){t.after(e("<div></div>").addClass("nice-select").addClass(t.attr("class")||"").addClass(t.attr("disabled")?"disabled":"").attr("tabindex",t.attr("disabled")?null:"0").html('<span class="current"></span><ul class="list"></ul>'));var s=t.next(),n=t.find("option"),i=t.find("option:selected");s.find(".current").html(i.data("display")||i.text()),n.each(function(t){var n=e(this),i=n.data("display");s.find("ul").append(e("<li></li>").attr("data-value",n.val()).attr("data-display",i||null).addClass("option"+(n.is(":selected")?" selected":"")+(n.is(":disabled")?" disabled":"")).html(n.text()))})}if("string"==typeof t)return"update"==t?this.each(function(){var t=e(this),n=e(this).next(".nice-select"),i=n.hasClass("open");n.length&&(n.remove(),s(t),i&&t.next().trigger("click"))}):"destroy"==t?(this.each(function(){var t=e(this),s=e(this).next(".nice-select");s.length&&(s.remove(),t.css("display",""))}),0==e(".nice-select").length&&e(document).off(".nice_select")):console.log('Method "'+t+'" does not exist.'),this;this.hide(),this.each(function(){var t=e(this);t.next().hasClass("nice-select")||s(t)}),e(document).off(".nice_select"),e(document).on("click.nice_select",".nice-select",function(t){var s=e(this);e(".nice-select").not(s).removeClass("open"),s.toggleClass("open"),s.hasClass("open")?(s.find(".option"),s.find(".focus").removeClass("focus"),s.find(".selected").addClass("focus")):s.focus()}),e(document).on("click.nice_select",function(t){0===e(t.target).closest(".nice-select").length&&e(".nice-select").removeClass("open").find(".option")}),e(document).on("click.nice_select",".nice-select .option:not(.disabled)",function(t){var s=e(this),n=s.closest(".nice-select");n.find(".selected").removeClass("selected"),s.addClass("selected");var i=s.data("display")||s.text();n.find(".current").text(i),n.prev("select").val(s.data("value")).trigger("change")}),e(document).on("keydown.nice_select",".nice-select",function(t){var s=e(this),n=e(s.find(".focus")||s.find(".list .option.selected"));if(32==t.keyCode||13==t.keyCode)return s.hasClass("open")?n.trigger("click"):s.trigger("click"),!1;if(40==t.keyCode){if(s.hasClass("open")){var i=n.nextAll(".option:not(.disabled)").first();i.length>0&&(s.find(".focus").removeClass("focus"),i.addClass("focus"))}else s.trigger("click");return!1}if(38==t.keyCode){if(s.hasClass("open")){var l=n.prevAll(".option:not(.disabled)").first();l.length>0&&(s.find(".focus").removeClass("focus"),l.addClass("focus"))}else s.trigger("click");return!1}if(27==t.keyCode)s.hasClass("open")&&s.trigger("click");else if(9==t.keyCode&&s.hasClass("open"))return!1});var n=document.createElement("a").style;return n.cssText="pointer-events:auto","auto"!==n.pointerEvents&&e("html").addClass("no-csspointerevents"),this}}(jQuery);

