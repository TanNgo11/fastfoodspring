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
	<div style="margin-top: 100px" class="container " id="section_food">
		<nav class="mt-4" aria-label="breadcrumb">
			<ol style="background-color: white; padding-left: 0px;"
				class="breadcrumb">
				<li class="breadcrumb-item"><a href="/home">Home</a></li>
				<li class="breadcrumb-item"><a href="#">News</a></li>

			</ol>
		</nav>
		<div class="row mb-2" id="contentNews">


			

			




		</div>
		
		<ul id="pagination" class="pagination"></ul>
	</div>
	<script src="<c:url value='/template/js/webnews.js'/>"></script>

</body>
</html>