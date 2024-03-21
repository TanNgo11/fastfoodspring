package com.fastfood.mongo.service;

import java.util.List;

import com.fastfood.dto.ApiResponse;
import com.fastfood.mongo.entity.Comment;

public interface ICommentService {
	Comment save(Comment comments);

	List<Comment> findAll();

	List<Comment> findByProductId(String productId, String userId);

	Comment updateCommentById(Comment comments);

	Comment findCommentById(String id);

	ApiResponse deleteCommentById(String id);

	ApiResponse upvotedComment(String id, String userId);

	ApiResponse deleteVotedComment(String id, String userId);

}