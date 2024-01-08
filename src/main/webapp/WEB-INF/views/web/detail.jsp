<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<main class="detail">
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-12">
				<input type="radio" id="image1" name="image" checked> <input
					type="radio" id="image2" name="image"> <input type="radio"
					id="image3" name="image">

				<div class="container">
					<div class="featured-wrapper">
						<ul class="featured-list">

						</ul>
						<ul class="arrows">

						</ul>
						<ul class="dots">

						</ul>
					</div>
					<ul class="thumb-list">

					</ul>
				</div>
				<input id="listimg" type="hidden"
					value="${productDetail.getListImage().get(0).getImageURL()}">

			</div>
			<div class="col-md-6 col-12">
				<div class="product-detail">
					<h1 class="title-detail">${productDetail.getProductName()}</h1>
					<p class="price">${productDetail.getPrice()}$</p>
					<p class="description">${productDetail.getDescription()}</p>
					<button onClick="addToCart(${productDetail.getId()})"
						class="btn btn-primary addToCart addToCartDetail">Add to
						Cart</button>
				</div>

			</div>
		</div>
		<div style="margin-top: 100px" id="comments-container"></div>
	</div>
	</main>



	<script>
		$('#comments-container')
				.comments(
						{
							profilePictureURL : 'https://cdn-icons-png.flaticon.com/512/9131/9131529.png',
							currentUserIsAdmin : true,
							textareaPlaceholderText : 'Leave a comment',
							popularText : 'Most popular',
							sendText : 'Comment',
							oldestText : 'Old',
							youText : 'Me',
							highlightColor : '#23A6F0',
							currentUserIsAdmin : true,
							forceResponsive : true,

							refresh : function() {
								$('#comments-container').addClass('rendered');
							},
							getComments : function(success, error) {
								var commentsArray = [ {
									id : 1,
									created : '2015-10-01',
									content : 'Lorem ipsum dolort sit amet',
									fullname : 'Simon Powell',
									upvote_count : 2,
									user_has_upvoted : false,
									userId : 1

								},

								{
									id : 2,
									parent:1,
									createdByAdmin : 'created_by_admin',
									created : '2015-10-01',
									content : 'Lorem ipsum dolort sit amet',
									fullname : 'test',
							
									upvote_count : 2,
									user_has_upvoted : false
								} ,
								
								{
									id : 5,
									parent:2,
									createdByAdmin : 'created_by_admin',
									created : '2015-10-01',
									content : 'Lorem ipsum dolort sit amet',
									fullname : 'test',
									created_by_admin : true,
									upvote_count : 2,
									user_has_upvoted : false
								}
								];
								success(commentsArray);
							}
						}

				);
	</script>

</body>
</html>