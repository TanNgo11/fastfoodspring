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
	<section class="h-100 h-custom">
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12">
					<div class="card card-registration card-registration-2">
						<div class="card-body p-0">
							<div class="row g-0">
								<div class="col-lg-8">
									<div class="p-5">
										<div
											class="d-flex justify-content-between align-items-center mb-5">
											<h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>
										</div>
										<div id="content"></div>







									</div>
								</div>



								<div style="background-color: #f4e533;" class="col-lg-4 ">
									<form action="cart" method="POST">
										<div class="p-3">
											<h5 style="color: red">${msgError}</h5>
											<h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
											<div class="d-flex justify-content-between mb-4 text-center">
												<div class="container-fluid">
													<div class="row no-gutters">
														<div style="font-weight: bold;" class="col-md-6 col-6">Name</div>
														<div style="font-weight: bold;" class="col-md-3 col-3">Quantity</div>
														<div style="font-weight: bold;" class="col-md-3 col-3">Price</div>
													</div>
													<div id="summary-content"></div>



												</div>

											</div>






											<div class="row">
												<div class="col-md-12 mb-3">
													<label for="validationCustomUsername">Phone Number</label>
													<div class="input-group">
														<div class="input-group-prepend">
															<span class="input-group-text" id="inputGroupPrepend"><i
																class="fa fa-phone" aria-hidden="true"></i></span>
														</div>
														<input type="text" class="form-control"
															id="validationCustomUsername" placeholder="Phone Number"
															aria-describedby="inputGroupPrepend" required>
														<div class="invalid-feedback">Please choose a phone
															number.</div>
													</div>
												</div>
												<div class="col-md-12 mb-3">
													<label for="validationCustomEmail">Email</label>
													<div class="input-group">
														<div class="input-group-prepend">
															<span class="input-group-text" id="inputGroupPrepend">@</span>
														</div>
														<input type="email" class="form-control"
															id="validationCustomEmail" placeholder="Your Email"
															aria-describedby="inputGroupPrepend" required>
														<div class="invalid-feedback">Please choose an email
															address.</div>
													</div>
												</div>
												<div class="col-md-12 mb-3">

													<div class="row">
														<div class="col-md-4">
															<label class="mr-sm-2" for="inlineFormCustomSelect">City</label>
															<select class="custom-select mr-sm-2" id="city">
																<option value="" selected>City</option>
															</select>

														</div>
														<div class="col-md-4">
															<label class="mr-sm-2" for="inlineFormCustomSelect">District</label>
															<select class="custom-select mr-sm-2" id="district">
																<option value="" selected>District</option>
															</select>
														</div>
														<div class="col-md-4">
															<label class="mr-sm-2" for="inlineFormCustomSelect">Ward</label>
															<select class="custom-select mr-sm-2" id="ward">
																<option value="" selected>Ward</option>
															</select>
														</div>
													</div>
												</div>
												<div class="col-md-12 mb-3">
													<textarea id="result" type="email" class="form-control"
														placeholder="Your Street">${address}</textarea>
												</div>
											</div>

											<hr class="my-4">

											<div class="d-flex justify-content-between mb-5">
												<h5 class="text-uppercase">Total price</h5>
												<div id="totalPay"></div>
											</div>
											<input id="message" type="hidden" value="${msg}">
											<div id="toasts"></div>
											<c:if test="${param.msg != null}">
												<input id="message" type="hidden" value="${param.msg}">
											</c:if>
											<button type="submit" class="btn btn-dark btn-block btn-lg"
												data-mdb-ripple-color="dark">Buy Now</button>
										</div>
									</form>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	</main>

</body>
</html>