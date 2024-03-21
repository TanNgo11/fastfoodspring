package com.fastfood.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fastfood.dto.ApiResponse;
import com.fastfood.mongo.entity.Comment;
import com.fastfood.mongo.service.ICommentService;

@RestController
public class CommentController {

	@Autowired
	private ICommentService commentService;

	@PostMapping("/api/v1/comment")
	public Comment createUser(@RequestBody Comment comment) {
		return commentService.save(comment);
	}

	@GetMapping("/api/v1/comments")
	public List<Comment> loadComment() {
		return commentService.findAll();
	}

	@GetMapping("/api/v1/comments/productId/{productId}")
	public List<Comment> loadCommentByProductID(@PathVariable String productId, @RequestParam String userId) {

		return commentService.findByProductId(productId, userId);
	}

	@PutMapping("/api/v1/comments/{id}")
	public Comment updateComment(@PathVariable String id, @RequestBody Comment comment) {
	
		return commentService.updateCommentById(comment);
	}

	@DeleteMapping("/api/v1/comments/{id}")
	public ApiResponse deleteComment(@PathVariable String id) {
		return commentService.deleteCommentById(id);
	}

	@PostMapping("/api/v1/comments/{id}/upvotes/")
	public ApiResponse updateVote(@PathVariable String id, @RequestParam String userId) {
		return commentService.upvotedComment(id, userId);
	}
	
	@DeleteMapping("/api/v1/comments/{id}/upvotes/")
	public ApiResponse deleteVote(@PathVariable String id, @RequestParam String userId) {
		return commentService.deleteVotedComment(id, userId);
	}
}