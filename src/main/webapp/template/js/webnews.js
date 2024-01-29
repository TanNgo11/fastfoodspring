var homeConfig = {
    pageSize: 2,
    currentPage: 1
}

window.onload = loadAllNews();






function loadAllNews() {


    $.ajax({
        url: "/api/v1/news",
        type: "GET",
        data: {
            page: homeConfig.currentPage,
            limit: homeConfig.pageSize
        },

        success: function (data) {

            var totalPages = data.totalPage;
            var currentPage = data.page;
            var listResult = data.listResult;
          
            var str = "";

            const content = document.querySelector("#contentNews");
            var i = 1;
            for (let item of listResult) {
            	
            

                str += `<div class="col-md-6 mt-3">
					
						<div class="card flex-md-row mb-4 box-shadow h-md-250">
							<div class="card-body d-flex flex-column align-items-start">
								<strong class="d-inline-block mb-2 text-primary">News</strong>
								<h3 class="mb-0">
									<a class="text-dark" href="/news/${item.slug}">${item.title}</a>
								</h3>
								<div class="mb-1 text-muted">${item.dateFormat}</div>
								<div
									style="font-size: 14px; margin-top: 10px; display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 1; overflow: hidden;"
									class="card-text mb-auto">${item.description}</div>
							</div>
							<img class="card-img-right flex-auto d-none d-md-block"
								data-src="holder.js/200x250?theme=thumb"
								alt="Thumbnail [200x250]" style="width: 200px; height: 250px;"
								src="${item.imageURL}" data-holder-rendered="true">
						</div>
				
				</div>`
            }
            paging(totalPages, currentPage, function () {
                loadAllNews()
            });
            content.innerHTML = str;

        }

    });
}





function paging(totalPages, currentPage, callback) {

    $('#pagination').twbsPagination({
        totalPages: totalPages,
        visiblePages: 10,
        startPage: currentPage,
        onPageClick: function (event, page) {
            homeConfig.currentPage = page;
            setTimeout(callback, 100);
        }
    });

}