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

	<div id="logreg-forms">

		<c:if test="${param.incorrectAccount != null}">
			<div class="alert alert-danger">Username or password incorrect
			</div>
		</c:if>
		<c:if test="${param.accessDenied != null}">
			<div class="alert alert-danger">you Not authorize</div>
		</c:if>
		<form class="form-signin" action="/admin/j_spring_security_check"
			method="POST">
			<input type="hidden" name="mode" value="login">

			<h1 class="h3 mb-3 font-weight-normal" style="text-align: center">
				Sign in</h1>
			
			<span class="errorMessage">${msgError}</span> <input
				value="${usernameC}" type="text" id="inputEmail"
				class="form-control" placeholder="Account" required="" autofocus=""
				name="j_username"> <input value="${passwordC}"
				type="password" id="inputPassword" name="j_password"
				class="form-control" placeholder="Password" required="">
			<div class="form-check mb-0 mt-1">
				<input name="remember" style="margin-top: 6px"
					class="form-check-input me-2 align-self-lg-center" type="checkbox"
					value="" id="form2Example3" /> <label class="form-check-label"
					for="form2Example3"> Remember me </label>
			</div>
			<button class="btn btn-success btn-block" type="submit">
				<i class="fas fa-sign-in-alt"></i> Sign in
			</button>
			

		</form>




		
		

	</div>

	<div id="toasts"></div>
	<c:if test="${param.msg != null}">
		<input id="message" type="hidden" value="${param.msg}">
	</c:if>



</body>
</html>