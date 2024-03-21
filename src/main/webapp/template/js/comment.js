$(document).ready(function() {
    var productId = $("#productCommentId").val();
    var commentuserId = $("#commentUserId").val();
    var commentuserName = $("#commentFullname").val();
    
 
    if(commentuserId!=0){
    	 $('#comments-container').comments({
    	        profilePictureURL: 'https://cdn-icons-png.flaticon.com/512/9131/9131529.png',
    	        textareaPlaceholderText: 'Leave a comment',
    	        roundProfilePictures: true,
    	       
    	       
    	      
    	        refresh: function() {
    	            $('#comments-container').addClass('rendered');
    	        },
    	        getComments: function(success, error) {
    	            $.ajax({
    	                type: 'get',
    	                url: '/api/v1/comments/productId/' + productId,
    	                data:{
    	                	userId:commentuserId
    	                },
    	                success: function(commentsArray) {
    	                	commentsArray.map(function(comment) {
    	                		
    	                	    comment.created_by_current_user = comment.userId === commentuserId;
    	                	    return comment;
    	                	});
    	                	
    	                    success(commentsArray);
    	                    
    	                },
    	                error: function(xhr, status, err) {
    	                    error(xhr, status, err);
    	                    console.log(err);
    	                }
    	            });
    	        },
    	        postComment: function(commentJSON, success, error) {
    	            commentJSON.productId = productId;
    	            commentJSON.userId = commentuserId;
    	            commentJSON.fullname = commentuserName;
    	            commentJSON.upvote_count = 0;
    	            commentJSON.user_has_upvoted = false
    	            commentJSON.created_by_admin = false;
    	            commentJSON.created_by_current_user = true;
    	           
    	            commentJSON = JSON.stringify(commentJSON);
    	           
    	            
    	            $.ajax({
    	                type: 'post',
    	                url: '/api/v1/comment',
    	                data: commentJSON,
    	                contentType: 'application/json',
    	                success: function(comment) {
    	                
    	                    success(comment);
    	                    console.log(comment);
    	                },
    	                error: function(xhr, status, err) {
    	                    error(xhr, status, err);
    	                    console.log(err);
    	                }
    	            });
    	        },
    	      
  	          
  	          putComment: function(commentJSON, success, error) {
  	        	  commentJSON.userId = commentuserId;
  	        	  commentJSON.productId = productId;
  	        	  commentJSON = JSON.stringify(commentJSON);
  	        	    $.ajax({
  	        	      type: 'put',
  	        	      url: '/api/v1/comments/' + commentJSON.id,
  	        	      data: commentJSON,
  	        	      contentType: 'application/json',
  	        	      success: function(comment) {
  	        	        success(comment)
  	        	      },
  	        	      error: error
  	        	    });
  	        	  },
  	        	  
  	        	deleteComment: function(commentJSON, success, error) {
  	        	    $.ajax({
  	        	      type: 'delete',
  	        	      url: '/api/v1/comments/' + commentJSON.id,
  	        	      contentType: 'application/json',
  	        	      success: success,
  	        	      error: error
  	        	    });
  	        	  },
    	        
    	        upvoteComment: function(commentJSON, success, error) {
    	            var commentURL = '/api/v1/comments/' + commentJSON.id;
    	            var upvotesURL = commentURL + '/upvotes/?userId=' + commentuserId; 
    	            
    	            if(commentJSON.user_has_upvoted) {
    	              $.ajax({
    	                type: 'post',
    	                url: upvotesURL,
    	                
    	                success: function() {
    	                  success(commentJSON)
    	                },
    	                error: error
    	              });
    	            } else {
    	              $.ajax({
    	                type: 'delete',
    	                url: upvotesURL,
    	               
    	                success: function() {
    	                  success(commentJSON)
    	                },
    	                error: error
    	              });
    	            }
    	          }
    	    });
    }

   
});
