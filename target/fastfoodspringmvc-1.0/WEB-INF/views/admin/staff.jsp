<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<li><a href="#">Dashboard</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a class="active" href="/admin/staffs">Staffs</a></li>
			</ul>
		</div>

	</div>



	<div class="table-data">
		<div class="order">
			<div class="head">
				<h3>Staff list</h3>
				<i class='bx bx-search'></i> <i class='bx bx-filter'></i> <a
					href="/admin/staffs/create" id="add"> <i
					class="fa fa-plus-circle fa-2x mr-2"></i> <span>Create new account</span>
				</a>
			</div>





			<table>
				<thead>
					<tr>
						<th style="width: 50px">#</th>
						<th>Staff Name</th>
						<th>Address</th>
						<th>Email</th>
						<th>Phone Number</th>
						<th>Role</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody id="contentTable">






				</tbody>

			</table>
			<ul id="pagination" class="pagination"></ul>






		</div>

	</div>

	</main>
	<script src="/template/js/adminRenderStaff.js"></script>



</body>
</html>