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
	<section class="h-100 h-custom">
		<div class="container py-5 h-100">
			<nav class="mt-3" aria-label="breadcrumb">
				<ol style="background-color: white; padding-left: 0px;"
					class="breadcrumb">
					<li class="breadcrumb-item"><a style="color: #007bff;"
						href="/home">Home</a></li>
					<li class="breadcrumb-item"><a href="#">Cart</a></li>

				</ol>
			</nav>
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col-12">
					<div class="card card-registration card-registration-2">
						<div class="card-body p-0">
							<div class="row g-0">
								<div class="col-lg-7">
									<div class="p-5">
										<div
											class="d-flex justify-content-between align-items-center mb-5">
											<h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>
										</div>
										<div id="content"></div>







									</div>
								</div>



								<div style="background-color: #f4e533;" class="col-lg-5 ">

									<div class="p-3">
										<h5 style="color: red">${msgError}</h5>
										<h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
										<div class="d-flex justify-content-between mb-4 text-center">
											<div class="container-fluid">
												<div class="row no-gutters">
													<div style="font-weight: bold;" class="col-md-6 col-6">Product
														Name</div>
													<div style="font-weight: bold;" class="col-md-3 col-3">Quantity</div>
													<div style="font-weight: bold;" class="col-md-3 col-3">Price</div>
												</div>
												<div id="summary-content"></div>



											</div>

										</div>





										<form:form action="cart" method="POST"
											modelAttribute="account">
											<div class="row">
												<div class="col-md-12 mb-3">
													<label for="validationCustomPhoneNumber">Phone
														Number</label>
													<div class="input-group">
														<div class="input-group-prepend">
															<span class="input-group-text" id="inputGroupPrepend"><i
																class="fa fa-phone" aria-hidden="true"></i></span>
														</div>
														<form:input type="text" cssClass="form-control"
															id="validationCustomPhoneNumber"
															placeholder="Phone Number" path="phoneNumber" />

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
														<form:input type="email" path="email"
															cssClass="form-control" id="validationCustomEmail"
															placeholder="Your Email"
															aria-describedby="inputGroupPrepend" />
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
													<form:textarea path="address" id="result"
														cssClass="form-control" placeholder="Your Address" />
												</div>

												<div class="col-md-12 mb-3">

													<label class="mr-sm-2" for="">Payment</label> <select
														class="custom-select mr-sm-2" id="payment-type">
														<option value="vn-pay" selected>VNPAY</option>
													</select>

												</div>


											</div>

											<hr class="my-4">

											<div class="d-flex justify-content-between mb-5">
												<h5 class="text-uppercase">Total price</h5>
												<div id="totalPay"></div>
											</div>




											<button id="submitOrder" type="submit" class="Btn">
												Pay
												<svg class="svgIcon" viewBox="0 0 576 512">
													<path
														d="M512 80c8.8 0 16 7.2 16 16v32H48V96c0-8.8 7.2-16 16-16H512zm16 144V416c0 8.8-7.2 16-16 16H64c-8.8 0-16-7.2-16-16V224H528zM64 32C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64H512c35.3 0 64-28.7 64-64V96c0-35.3-28.7-64-64-64H64zm56 304c-13.3 0-24 10.7-24 24s10.7 24 24 24h48c13.3 0 24-10.7 24-24s-10.7-24-24-24H120zm128 0c-13.3 0-24 10.7-24 24s10.7 24 24 24H360c13.3 0 24-10.7 24-24s-10.7-24-24-24H248z"></path></svg>
											</button>

											<form:input type="hidden" path="id" />
										</form:form>
									</div>

								</div>
								<input id="totalPayment" type="hidden" value=0>


							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<div id="toasts">
		<input id="message" type="hidden" value="${msg}">
	</div>
	</main>
	<script src="<c:url value='/template/js/cart.js'/>"></script>
	<script src="<c:url value='/template/js/addressVNAPI.js'/>"></script>



</body>
</html>