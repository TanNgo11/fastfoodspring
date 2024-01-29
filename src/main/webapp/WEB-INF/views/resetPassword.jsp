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

		<form style="display: block;" action="/resetPassword"
			class="form-reset" method="POST">
			<input name="email" type="email" id="resetEmail" class="form-control"
				placeholder="Email address" required="" autofocus="" value="${email}"> <input
				name="username" type="text" class="form-control"
				placeholder="Account" required="" autofocus="" value="${username}"> <input
				name="password" type="password" id="password" class="form-control"
				placeholder="Password" required autofocus=""> <input
				type="password" id="confirm-password" class="form-control"
				placeholder="Repeat Password" required autofocus=""> <span
				id="passwordError" style="color: red;"></span>
			<button onclick="checkPassword(event)"
				class="btn btn-primary btn-block" type="submit">Reset
				Password</button>

		</form>





	</div>

	<div id="toasts"></div>
	<c:if test="${param.msg != null}">
		<input id="message" type="hidden" value="${param.msg}">
	</c:if>

	<script>
		function checkPassword(event) {
			var password = document.getElementById("password").value;
			var confirmPassword = document.getElementById("confirm-password").value;
			var errorElement = document.getElementById("passwordError");

			if (password !== confirmPassword) {
				errorElement.textContent = "Passwords do not match";
				event.preventDefault();
			} else {
				errorElement.textContent = "";
			}
		}
	</script>



</body>
</html>