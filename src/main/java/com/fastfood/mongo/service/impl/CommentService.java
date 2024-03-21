package com.fastfood.mongo.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fastfood.dto.ApiResponse;
import com.fastfood.mongo.entity.Comment;
import com.fastfood.mongo.repository.CommentRepository;
import com.fastfood.mongo.service.ICommentService;

@Service
public class CommentService implements ICommentService {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private CommentRepository commentRepository;

	@Override
	public Comment save(Comment comments) {
//		List<String> userUpvotes = comments.getUserUpvotes();
//		userUpvotes.add(comments.getUserId());
//		comments.setUserUpvotes(userUpvotes);
		comments.setActive(true);
		comments = commentRepository.save(comments);
		comments.setCreator(comments.getId());
		return commentRepository.save(comments);
	}

	@Override
	public List<Comment> findAll() {
		return commentRepository.findAll().stream().filter(Comment::isActive).collect(Collectors.toList());
	}

	@Override
	public Comment updateCommentById(Comment comments) {
		comments.setActive(true);
		return commentRepository.save(comments);
	}

	@Override
	public Comment findCommentById(String id) {

		return commentRepository.findOne(id);
	}

	@Override
	public ApiResponse deleteCommentById(String id) {
//		Comment deletedComment = commentRepository.findOne(id);
//		deletedComment.setContent("[This comment has been deleted.]");
//		deletedComment.setActive(false);
//		commentRepository.save(deletedComment);
		
		softDeleteComment(id);

		return new ApiResponse().builder().http(HttpStatus.OK).message("Deleted successfull!").success(true).build();
	}

	@Override
	public List<Comment> findByProductId(String productId, String userId) {
		List<Comment> comments = commentRepository.findByProductId(productId);
		for (Comment comment : comments) {
			boolean userHasUpvoted = comment.getUserUpvotes().contains(userId);
			comment.setUser_has_upvoted(userHasUpvoted);
		}
		return comments.stream().filter(Comment::isActive).collect(Collectors.toList());
	}

	@Override
	public ApiResponse upvotedComment(String id, String userId) {
		Query query = new Query(Criteria.where("id").is(id).andOperator(Criteria.where("userUpvotes").ne(userId)));
		Update update = new Update().inc("upvote_count", 1).addToSet("userUpvotes", userId);
		FindAndModifyOptions returnNew = FindAndModifyOptions.options().returnNew(true);

		Comment updatedComment = mongoTemplate.findAndModify(query, update, returnNew, Comment.class);

		if (updatedComment == null) {
			return ApiResponse.builder().http(HttpStatus.OK).message("User already upvoted or comment not found.")
					.success(false).build();
		}

		return ApiResponse.builder().http(HttpStatus.OK).message("Upvoted successfully!").success(true).build();
	}

	@Override
	public ApiResponse deleteVotedComment(String id, String userId) {
		Query query = new Query(Criteria.where("id").is(id).and("userUpvotes").is(userId));
		Update update = new Update().inc("upvote_count", -1).pull("userUpvotes", userId);
		FindAndModifyOptions returnNew = FindAndModifyOptions.options().returnNew(true);

		Comment updatedComment = mongoTemplate.findAndModify(query, update, returnNew, Comment.class);

		if (updatedComment == null) {
			return ApiResponse.builder().http(HttpStatus.OK).message("User had not upvoted or comment not found.")
					.success(false).build();
		}

		return ApiResponse.builder().http(HttpStatus.OK).message("Removed upvote successfully!").success(true).build();
	}
	
	public void softDeleteComment(String commentId) {
	    Optional<Comment> commentOpt = commentRepository.findById(commentId);
	    if (!commentOpt.isPresent()) {
	        throw new NoSuchElementException("Comment not found");
	    }

	    Comment comment = commentOpt.get();
	    comment.setActive(false);
	    commentRepository.save(comment);

	   
	    List<Comment> childComments = commentRepository.findByParent(commentId);
	    for (Comment childComment : childComments) {
	        softDeleteComment(childComment.getId());
	    }
	}

}