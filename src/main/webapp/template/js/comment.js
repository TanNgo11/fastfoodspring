$(document).ready(function() {
    var productId = $("#productCommentId").val();
    var commentuserId = $("#commentUserId").val();
    var commentuserName = $("#commentFullname").val();

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
                success: function(commentsArray) {
                    success(commentsArray);
                    console.log(commentsArray);
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
        }
    });
});
