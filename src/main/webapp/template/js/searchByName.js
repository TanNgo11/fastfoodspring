var suggestions = [
];

function callAPISearchProduct(searchQuery, callback) {
    $.ajax({
        url: "/api/v1/products/search?q=" + searchQuery,
        type: "GET",
        success: function(data) {
           
            callback(data); 
        }
    });
}
	

const searchInput = document.querySelector(".searchInput");
const input = searchInput.querySelector("input");
const resultBox = searchInput.querySelector(".resultBox");
const icon = searchInput.querySelector(".icon");
let linkTag = searchInput.querySelector("a");
let webLink;

// if user press any key and release
input.onkeyup = (e)=>{
    let userData = e.target.value; // user enetered data
    let emptyArray = [];
    if (userData) {
        callAPISearchProduct(userData, function(suggestions) {
            let suggestionArray = suggestions.map((data) => {
            	let detailLink  = "/detail/"+ data.slug;
                return '<li><a href="'+detailLink+'">' + data.productName + '</a></li>';
            });
            searchInput.classList.add("active");
            showSuggestions(suggestionArray);
            let allList = resultBox.querySelectorAll("li");
            for (let i = 0; i < allList.length; i++) {
                allList[i].setAttribute("onclick", "select(this)");
            }
        });
    } else {
        searchInput.classList.remove("active");
    }
}

function showSuggestions(list){
    let listData;
    if(!list.length){
        userValue = inputBox.value;
        listData = '<li>'+ userValue +'</li>';
    }else{
        listData = list.join('');
    }
    resultBox.innerHTML = listData;
}

const searchLink = document.getElementById("searchLink");
input.addEventListener('input', function() {
  
    searchLink.href = "/search?q=" + input.value;
});