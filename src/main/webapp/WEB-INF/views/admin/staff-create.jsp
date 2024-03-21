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
	<div class="head-title">
		<div class="left">
			<h1>Dashboard</h1>
			<ul class="breadcrumb">
				<li><a href="#">Dashboard</a></li>
				<li><i class='bx bx-chevron-right'></i></li>

				<li><a class="active" href="/admin/staffs">Staffs</a></li>
				<li><i class='bx bx-chevron-right'></i></li>
				<li><a class="active" href="/admin/staffs/create">Create</a></li>
			</ul>
		</div>

	</div>




	<div class="table-data">
		<div class="order">
			<form:form modelAttribute="user" ccsClass="needs-validation mt-5"
				action="/user/" method="POST">
			
				<div class="form-row">
					<div class="col-md-4 mb-3">
						<label for="fullName">Full Name</label>
						<form:input path="fullName" type="text" cssClass="form-control"
							id="fullName" placeholder="full name" />
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">Please choose a name.</div>
					</div>

					<div class="col-md-4 mb-3">
						<label for="fullName">Role</label> <select class="custom-select"
							id="role-select">
							<option value="STAFF">STAFF</option>
							<option value="ADMIN">ADMIN</option>
						</select>
					</div>
					<div class="col-md-4 mb-3">
						<label for="validationCustom02">Username</label>
						<input id="username" type="text" class="form-control"
							id="validationCustom02" placeholder="User Name" />
						<div class="valid-feedback">Looks good!</div>
						<div class="invalid-feedback">Please choose a last name.</div>
					</div>


					<div class="col-md-6 mb-3">
						<label for="password">Password</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroupPrepend"><i
									class="fa fa-phone" aria-hidden="true"></i></span>
							</div>
							<input  type="password" class="form-control"
								id="password" placeholder="Password" />
							<div class="invalid-feedback">Please choose a phone number.
							</div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="rePassword">Repeat Password</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroupPrepend">@</span>
							</div>
							<input type="password" class="form-control" id="rePassword"
								placeholder="rePassword" />
							<div class="invalid-feedback">Please choose an email
								address.</div>
						</div>
					</div>



					<div class="col-md-6 mb-3">
						<label for="phoneNumber">Phone Number</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroupPrepend"><i
									class="fa fa-phone" aria-hidden="true"></i></span>
							</div>
							<form:input path="phoneNumber" type="number"
								cssClass="form-control" id="phoneNumber"
								placeholder="Phone Number" />
							<div class="invalid-feedback">Please choose a phone number.
							</div>
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="email">Email</label>
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroupPrepend">@</span>
							</div>
							<form:input path="email" type="email" cssClass="form-control"
								id="email" placeholder="Your Email" />
							<div class="invalid-feedback">Please choose an email
								address.</div>
						</div>
					</div>
					<div class="col-md-12 mb-3">

						<div class="row">
							<div class="col-md-4">
								<label class="mr-sm-2" for="inlineFormCustomSelect">City</label>
								<select class="custom-select mr-sm-2" id="city" required="">
									<option value="" selected>Choose province</option>
								</select>

							</div>
							<div class="col-md-4">
								<label class="mr-sm-2" for="inlineFormCustomSelect">District</label>
								<select class="custom-select mr-sm-2" id="district" required="">
									<option value="" selected>Choose District</option>
								</select>
							</div>
							<div class="col-md-4">
								<label class="mr-sm-2" for="inlineFormCustomSelect">Ward</label>
								<select class="custom-select mr-sm-2" id="ward" required="">
									<option value="" selected>Choose Ward</option>
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-12 mb-3">
						<form:textarea path="address" id="result" cssClass="form-control"
							placeholder="Your Street" rows="6" cols="30" />
					</div>
				</div>


				<div class="text-right">
					<button id="changeUserProfile" class="btn btn-primary btn-sm"
						type="submit">Submit form</button>
				</div>
			</form:form>






		</div>

	</div>

	</main>

	<script>
		$('#changeUserProfile').click(function(e) {
			e.preventDefault();

			var data = {};
		
			var fullName = $('#fullName').val().trim();
			var email = $('#email').val().trim();
			var address = $('#result').val().trim();
			var phoneNumber = $('#phoneNumber').val().trim();
			var roleName = $('#role-select').val();
			var password = $('#password').val().trim();
			var rePassword = $('#rePassword').val().trim();
			var username = $('#username').val().trim();
			if (password === rePassword) {
				
				data["fullName"] = fullName
				data["email"] = email;
				data["address"] = address;
				data["phoneNumber"] = phoneNumber;
				data["roleName"] = roleName;
				data["password"] = password;
				data["username"] = username;

				createUser(data)
			}

		});
		function createUser(data) {
			$.ajax({
				url : '/admin/api/v1/staffs',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					window.location.href = "/admin/staffs/" + result.id
							+ "?msg=success_change";
				},
				error : function(error) {
					window.location.href = "/admin/staffs/" + result.id
							+ "?msg=error_change";
				}
			});
		}
	</script>
	<script src="<c:url value='/template/js/addressVNAPI.js'/>"></script>




</body>
</html>