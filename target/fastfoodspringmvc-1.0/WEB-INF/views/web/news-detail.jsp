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
	<div style="margin-top: 54px" class="container " id="section_food">
		<nav class="mt-3" aria-label="breadcrumb">
			<ol style="background-color: white; padding-left: 0px;"
				class="breadcrumb">
				<li class="breadcrumb-item"><a style="color: #007bff;"
					href="/home">Home</a></li>
				<li class="breadcrumb-item"><a href="#">News</a></li>
				<li class="breadcrumb-item"><a href="#"><c:forEach
							items="${news}" var="o">
			${o.title}
		</c:forEach></a></li>

			</ol>
		</nav>
		<h1>
			<c:forEach items="${news}" var="o">
			${o.title}
		</c:forEach>
		</h1>






		<c:forEach items="${news}" var="o">
			${o.description}
		</c:forEach>


	</div>




	</div>

</body>
</html>