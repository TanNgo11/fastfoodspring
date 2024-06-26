package com.fastfood.mongo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fastfood.mongo.entity.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
	List<Comment> findByProductId(String productId);

	Optional<Comment> findById(String commentId);

	List<Comment> findByParent(String commentId);
}