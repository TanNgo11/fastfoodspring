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
        url: "/admin/api/v1/news",
        type: "GET",
        data: {
            page: homeConfig.currentPage,
            limit: homeConfig.pageSize
        },

        success: function (data) {

            var totalPages = data.totalPage;
            var currentPage = data.page;
            let listResult = data.listResult;
         
            var str = "";
      
            const content = document.querySelector("#contentTable");
            var i = 1;
            for (let item of listResult) {
            	let truncatedTitle = truncateTitle(item.title);
            item.createdDate = formatDateTime(item.createdDate)

                str += `<tr>
                    <td style="width:50px" scope="row">${item.id}</td>
                    <td>${truncatedTitle}</td>
                  
                    
                    <td>${item.createdDate}</td>
                    <td>${item.createdBy}</td>
                    <td><a style="color:black" href="/admin/news/edit?id=${item.id}"><i class="fa fa-pencil mr-4 ml-2 "></i></a>
                          <span onclick ="deleteNews(${item.id})"><i class="fa fa-trash"></i></span></td>
                </tr>`
            }
            paging(totalPages, currentPage, function () {
                loadAllNews()
            });
            content.innerHTML = str;

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