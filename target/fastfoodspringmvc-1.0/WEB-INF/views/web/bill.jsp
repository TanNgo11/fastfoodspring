<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<main>
	<div class="container">

		<nav style="margin-top: 54px" aria-label="breadcrumb">
			<ol style="background-color: white; padding-left: 0px;"
				class="breadcrumb">
				<li class="breadcrumb-item"><a style="color: #007bff;"
					href="/home">Home</a></li>
				<li class="breadcrumb-item"><a href="#">Order History</a></li>

			</ol>
		</nav>
		<h1 class="text-md-center">Order History</h1>
		<div class="table-responsive">
			<table
				class="table table-striped table-borderless border-0 border-b-2 brc-default-l1">
				<thead class="bg-none bgc-default-tp1">
					<tr class="text-white">
						<th>#</th>
						<th class="opacity-2">OrderID</th>
						<th>Customer Name</th>
						<th>Order Date</th>
						<th>Status</th>
						<th width="140">Total Pay</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody id="contentTable" class="text-95 text-secondary-d3">

				</tbody>
			</table>
			<ul id="pagination" class="pagination"></ul>
		</div>
	</div>
	</main>
	<script src="/template/js/userRenderBill.js"></script>



</body>
</html>