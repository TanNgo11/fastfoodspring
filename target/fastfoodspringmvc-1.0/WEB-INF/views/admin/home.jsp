<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main>
	<div class="head-title">
		<div class="left">
			<h1>Dashboard</h1>
			<ul class="breadcrumb">
				<li><a href="/admin/home">Dashboard</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a class="active" href="/admin/home">Home</a></li>
			</ul>
		</div>

	</div>

	<ul class="box-info">
		<li><i class='bx bxs-calendar-check'></i> <span class="text">
				<h3>${currentTotalSales}</h3>
				<p>Daily Sales</p>
		</span></li>
		<li><i class='bx bxs-group'></i> <span class="text">
				<h3>${totalAccount}</h3>
				<p>total Accounts</p>
		</span></li>
		<li><i class='bx bxs-dollar-circle'></i> <span class="text">
				<h3>${totalSales}</h3>
				<p>Total Sales</p>
		</span></li>
	</ul>


	<div class="row">
		<div style="margin-right: 20px; margin-left: 16px;"
			class="col-md-5 background-border ">

			<div id="chartContainer" style="height: 370px; width: 100%;"></div>

		</div>


		<div style="margin-left: 20px; width: 640px"
			class="background-border ">
			<div class="table-data">
				<div class="order">
					<div class="head">
						<h3 style="color: #3C91E6">The most user spent</h3>
						<i class='bx bx-search'></i> <i class='bx bx-filter'></i>

					</div>
					<table>
						<thead>
							<tr>
								<th style="width: 20px">#</th>
								<th>Customer Name</th>
								<th>Email</th>
								<th>Total Spent</th>
							</tr>
						</thead>
						<tbody id="contentTable">

						</tbody>

					</table>
				</div>

			</div>



		</div>

	</div>


	<div class="col-md-12 background-border mt-5">

		<div id="chartTop10ProductSalesContainer"
			style="height: 370px; width: 100%;"></div>

	</div>
	</main>


	<script type="text/javascript">
		window.onload = function() {
			renderRevenueByCategoryChart()
			loadAllTop10Spenders()
			renderTop10ProductSalesChart()
		}
		function renderRevenueByCategoryChart(){

			var dps = [ [] ];
			var chart = new CanvasJS.Chart("chartContainer", {
				exportEnabled : true,
				animationEnabled : true,
				theme : "light2", // "light1", "dark1", "dark2"
				title : {
					text : "Revenue By Category"
				},
				subtitles : [ {
					text : ""
				} ],
				data : [ {
					type : "pie",
					yValueFormatString : "#,##0\"%\"",
					indexLabel : "{label} - {y}",
					dataPoints : dps[0]
				} ]
			});

			var yValue;
			var label;

			<c:forEach items="${dataPointsList}" var="dataPoints" varStatus="loop">
			<c:forEach items="${dataPoints}" var="dataPoint">
			yValue = parseFloat("${dataPoint.y}");
			label = "${dataPoint.label}";
			dps[parseInt("${loop.index}")].push({
				label : label,
				y : yValue,
			});
			</c:forEach>
			</c:forEach>

			chart.render();
		}
		
		
		function renderTop10ProductSalesChart(){
			 
			var dps = [[]];
			var chart = new CanvasJS.Chart("chartTop10ProductSalesContainer", {
				theme: "light2", // "light1", "light2", "dark1"
				animationEnabled: true,
				title: {
					text: "Top 10 products sales"
				},
				axisY: {
					title: "The number of product sales",
					includeZero: true,
					suffix: " items"
				},
				data: [{
					type: "bar",
					yValueFormatString: "# items",
					indexLabel: "{y}",
					dataPoints: dps[0]
				}]
			});
			 
			var yValue;
			var label;
			 
			<c:forEach items="${dataPointsListOfTop10ProductSales}" var="dataPoints" varStatus="loop">	
				<c:forEach items="${dataPoints}" var="dataPoint">
					yValue = parseFloat("${dataPoint.y}");
					label = "${dataPoint.label}";
					dps[parseInt("${loop.index}")].push({
						label : label,
						y : yValue
					});		
				</c:forEach>	
			</c:forEach> 
			 
			chart.render();
		}

		function loadAllTop10Spenders() {

			$.ajax({
				url : "/admin/api/v1/users/top-spenders",
				type : "GET",

				success : function(data) {
					
					var listResult = data
					var row = document.getElementById("contentTable")
					
					var str = ""
					console.log(listResult)
					
					let i =1;

					for (let item of listResult) {
						let totalSpent  = formatVND(item.totalSpent)
						str+=`<tr>
							<td>\${i++}</td>
							<td>\${item.accountDTO.fullName}</td>
							<td>\${item.accountDTO.email}</td>
							<td>\${totalSpent}</td>
							
							</tr>`
					}
					
					
					row.innerHTML = str
				}

			});
		}
		
		function formatVND(value) {
		    return new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(value);
		}
	</script>




</body>
</html>