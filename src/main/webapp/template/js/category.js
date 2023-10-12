function loadByCategory(type) {

    $.ajax({
        url: "/FastFoodRestaurant/category",
        type: "get",
        data: {
            cateID: type

        },
        success: function (data) {
            console.log(data)
            data = JSON.parse(data);
            var rowDrink = document.getElementById("rowDrink");
            var rowFood = document.getElementById("rowFood");
            var sectionFood = document.getElementById("section_food");
            var sectionDrink = document.getElementById("section_drink");
            let str = "";

            for (var items of data) {
                str += `<div class="col-md-3 col-12 mt-4">
                                <div class="card h-100 drinkCard">
                                    <a href="detail?pid=${items.id}">
                                        <img class="card-img-top" src="${items.img}" alt="Card image cap">
                                    </a>

                                    <div class="card-body">
                                        <h5 class="card-title">${items.productName}</h5>
                                        <p class="card-text">${items.price}$</p>
                                       <button style="width: 90%" onClick="addToCart(${items.id})" class="btn btn-primary addToCart">Add to cart</button>
                                    </div>
                                </div>
                            </div>`
            }
            if (type === 1) {
                rowFood.innerHTML = str;
                sectionDrink.innerHTML = ` <h3 class="mt-5 title">DRINK</h3>
                    <div class="row mt-2 d-flex align-items-stretch" id="rowDrink">`;
            } else if (type === 2) {
                rowDrink.innerHTML = str;
                sectionFood.innerHTML = `<h3 class="mt-5 title foodTitle">FOOD</h3>
                        <div class="row mt-2 d-flex align-items-stretch" id="rowFood">`;
            }


        }

    });


}
