<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/common/taglib.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Hello</h2>
	<c:forEach items="${model}" var="model">
    Key = ${model.key}, value = ${model.value}<br>
</c:forEach>

</body>
</html>