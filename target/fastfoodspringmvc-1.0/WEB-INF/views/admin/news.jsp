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
				<li><a href="/admin/home">Dashboard</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a class="active" href="/admin/news">News</a></li>
			</ul>
		</div>
		
	</div>

	


	<div class="table-data">
		<div class="order">
			<div class="head">
				<h3>News list</h3>
				<i class='bx bx-search'></i> <i class='bx bx-filter'></i>
				<!-- add Product -->
				<a href="/admin/news/edit" id="add"> <i class="fa fa-plus-circle fa-2x mr-2"></i>
					<span>Add new post</span>
				</a>
			</div>





			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Title</th>
						<th>Created Day</th>
						<th>Author</th>

						<th>Functions</th>
					</tr>
				</thead>
				<tbody id="contentTable">






				</tbody>

			</table>
			<ul id="pagination" class="pagination"></ul>






		</div>

	</div>

	</main>
	<script src="/template/js/adminRenderNews.js"></script>

	

</body>
</html>