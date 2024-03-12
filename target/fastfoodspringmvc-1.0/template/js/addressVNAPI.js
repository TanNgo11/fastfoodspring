const host = "https://vapi.vnappmob.com/";
var callAPI = (api) => {
    return axios.get(api)
            .then((response) => {
                renderData(response.data.results, "city");
            });
}
callAPI('https://vapi.vnappmob.com/api/province/');
var callApiDistrict = (api) => {
    return axios.get(api)
            .then((response) => {
                renderData(response.data.results, "district");
            });
}
var callApiWard = (api) => {
    return axios.get(api)
            .then((response) => {
                renderData(response.data.results, "ward");
            });
}

var renderData = (array, select) => {
    let row = ' <option disable value="">Chọn</option>';
    if(select=="city"){
    	 array.forEach(element => {
    	        row += `<option data-id="${element.province_id}" value="${element.province_name}">${element.province_name}</option>`
    	    });
    }else if(select=="district"){
    	 array.forEach(element => {
 	        row += `<option data-id="${element.district_id}" value="${element.district_name}">${element.district_name}</option>`
 	    });
    }else if(select=="ward"){
   	 array.forEach(element => {
	        row += `<option data-id="${element.ward_id}" value="${element.ward_name}">${element.ward_name}</option>`
	    });
 }

   
    document.querySelector("#" + select).innerHTML = row
}

$("#city").change(() => {

    callApiDistrict(host + "api/province/district/" + $("#city").find(':selected').data('id'));
    printResult();
});
$("#district").change(() => {
    callApiWard(host + "api/province/ward/" + $("#district").find(':selected').data('id') + "?depth=2");
    printResult();
});
$("#ward").change(() => {
    printResult();
})

var printResult = () => {
    if ($("#district").find(':selected').data('id') != "" && $("#city").find(':selected').data('id') != "" &&
            $("#ward").find(':selected').data('id') != "") {
        let result = $("#city option:selected").text() +
                " , " + $("#district option:selected").text() + " , " +
                $("#ward option:selected").text();
        $("#result").val(result)
        
    }

}