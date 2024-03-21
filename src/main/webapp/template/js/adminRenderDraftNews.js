var homeConfig = {
    pageSize: 5,
    currentPage: 1
}

window.onload = loadAllNews();


function formatDateTime(timestamp) {
	  const date = new Date(timestamp);
	  const year = date.getUTCFullYear();
	  const month = String(date.getUTCMonth() + 1).padStart(2, '0');
	  const day = String(date.getUTCDate()).padStart(2, '0');
	  const hours = String(date.getUTCHours()).padStart(2, '0');
	  const minutes = String(date.getUTCMinutes()).padStart(2, '0');
	  const seconds = String(date.getUTCSeconds()).padStart(2, '0');

	  return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
	}



function loadAllNews() {


    $.ajax({
        url: "/admin/api/v1/draft/news",
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
          console.log(listResult)
            const content = document.querySelector("#contentTable");
            var i = 1;
            for (let item of listResult) {
            	let status = "Pending";
				let statusCSS = "product-status-remove";
				if(item.status==0){
					 status = "Removed";
					 statusCSS = "order-status-canceled"
				}else if(item.status==3){
					 status = "Schedule";
					 statusCSS = "product-status-schedule"
				}
            	
				let truncatedTitle = truncateTitle(item.title);
            item.createdDate = formatDateTime(item.createdDate)

                str += `<tr>
                    <td style="width:50px" scope="row">${item.id}</td>
                    <td>${truncatedTitle}</td>
                 
                    <td>${item.createdDate}</td>
                    <td>${item.createdBy}</td>
                    <td><span class="order-status ${statusCSS}">${status}</span></td>
                    <td><a style="color:black" href="/admin/news/edit?id=${item.id}"><i class="fa fa-pencil mr-4 ml-2 "></i></a>
                          </td>
                </tr>`
            }
            paging(totalPages, currentPage, function () {
                loadAllNews()
            });
            content.innerHTML = str;

        }

    });
}

function disableNewsByID(id) {
    $.ajax({
        url: "/admin/api/v1/news/" + id,
        type: "delete",
        success: function (data) {
            console.log(data);
            window.onload = loadAllNews();
        }

    });
}
function activeNewsByID(id) {
    $.ajax({
        url: "/admin/api/v1/news/" + id,
        type: "put",
        success: function (data) {
            console.log(data);
            window.onload = loadAllNews();



        }

    });
}

function truncateTitle(title, limit = 5) {
    const words = title.split(' '); 
    if (words.length > limit) {
        return words.slice(0, limit).join(' ') + '...';
    }
    return title; 
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