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
	<div class="table-data">
		<div class="page-content container">
			<div class="page-header text-blue-d2">
				<h1 class="page-title text-secondary-d1">
					Order <small class="page-info"> <i
						class="fa fa-angle-double-right text-80"></i> ID:
						#${order.getId()}
					</small>
				</h1>


			</div>

			<div class="container px-0">
				<div class="row mt-4">
					<div class="col-12 col-lg-12">

						<!-- .row -->

						<hr class="row brc-default-l1 mx-n1 mb-4" />

						<div class="row">
							<div class="col-sm-6">
								<div>
									<span class="text-sm text-grey-m2 align-middle text-600">To:</span>
									<span class="text-600 text-110 text-blue align-middle">${order.getCustomerName()}</span>
								</div>
								<div class="text-grey-m2">

									<div class="my-1 ">
										<span class="text-600 ">Address: </span> ${order.getAddress()}
									</div>
									<div class="my-1 text-600 ">
										<span class="text-600 ">Email: </span> ${order.getEmail()}
									</div>
									<div class="my-1 text-600 ">
										<i class="fa fa-phone fa-flip-horizontal text-secondary"></i>
										<b class="text-600">${order.getPhonenumber()}</b>
									</div>
								</div>
							</div>
							<!-- /.col -->

							<div
								class="text-95 col-sm-6 align-self-start d-sm-flex justify-content-end">
								<hr class="d-sm-none" />
								<div class="text-grey-m2">
									<div class="mt-1 mb-2 text-secondary-m1 text-600 text-125">
										Invoice</div>

									<div class="my-2">
										<i class="fa fa-circle text-blue-m2 text-xs mr-1"></i> <span
											class="text-600 text-90">ID:</span> #${order.getId()}
									</div>

									<div class="my-2">
										<i class="fa fa-circle text-blue-m2 text-xs mr-1"></i> <span
											class="text-600 text-90">Date:</span>${order.getCreatedDate()}</div>

									<div class="my-2">
										<i class="fa fa-circle text-blue-m2 text-xs mr-1"></i> <span
											class="text-600 text-90">Status:</span> <span
											id="status-order" class="badge px-25"><div
												class="input-group">
												<select class="custom-select" id="orderStatusSelect">
													<option selected>${order.getStatusString()}</option>
													<option value="0">Canceled</option>
													<option value="1">Completed</option>
													<option value="2">Delivery</option>
													<option value="3">Pending</option>
												</select>

											</div></span>
									</div>
								</div>
							</div>
							<!-- /.col -->
						</div>



						<div class="row border-b-2 brc-default-l2"></div>

						<!-- or use a table instead -->

						<div class="table-responsive">
							<table
								class="table table-striped table-borderless border-0 border-b-2 brc-default-l1">
								<thead class="bg-none bgc-default-tp1">
									<tr class="text-white">
										<th class="opacity-2">#</th>
										<th>Item</th>
										<th>quantity</th>
										<th>Unit Price</th>
										<th width="140">Amount</th>
									</tr>
								</thead>

								<tbody class="text-95 text-secondary-d3">
									<c:forEach items="${order.getItems()}" var="o"
										varStatus="status">
										<tr></tr>
										<tr>
											<td>${status.count}</td>
											<td>${o.getProductDTO().getProductName()}</td>
											<td>${o.getQuantity()}</td>
											<td class="text-95"><fmt:formatNumber
													value="${o.getPrice()}" type="currency" currencyCode="VND"
													pattern="###,###₫" /></td>
											<td class="text-secondary-d2"><fmt:formatNumber
													value="${o.getTotalPay()}" type="currency"
													currencyCode="VND" pattern="###,###₫" /></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>


						<div class="row mt-3">
							<div class="col-12 col-sm-7 text-grey-d2 text-95 mt-2 mt-lg-0">

							</div>

							<div
								class="col-12 col-sm-5 text-grey text-90 order-first order-sm-last">


								<div class="row my-2 align-items-center bgc-primary-l3 p-2">
									<div class="col-7 text-right">Total Amount</div>
									<div class="col-5">
										<span class="text-150 text-success-d3 opacity-2"> <fmt:formatNumber
												value="${order.getTotalPay()}" type="currency"
												currencyCode="VND" pattern="###,###₫" />

										</span>
									</div>
								</div>
							</div>
						</div>

						<hr />

						<div>
							<span class="text-secondary-d1 text-105">Thank you for
								your business</span> <a href="#"
								onclick="changeStatusOrder(${order.id})"
								class="btn btn-info btn-bold px-4 float-right mt-3 mt-lg-0">Proceed</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>


	<script>
	
	function changeStatusOrder(id){
		var selectedStatus = $('#orderStatusSelect').val();
		var status;
		if(selectedStatus==0){
			status = "canceled"
		}else if(selectedStatus==1){
			status = "completed"
		}else if(selectedStatus==2){
			status = "delivery"
		}else {
			status = "pending"
		}
		
		
		
		$.ajax({
			url : "/admin/api/v1/orders/"+id+"?status="+status,
			type : "PUT",
			success : function(data) {
				
				if(data.success){
					$('#message').val("success_order_status");
					let msg = document.getElementById("message");
					
						createToast(msg.value);
						msg.value = "";
						 
						
						
					
				}
				
				
				
				

			},
		
			error : function(error) {
			
			console.log(data)
			

		}
		});
	}
	</script>


</body>
</html>