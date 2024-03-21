package com.fastfood.mongo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
public class Comment {

	@Id
	private String id;
	private String userId;
	private String productId;
	private String parent;
	private String content;
	private String creator;
	private Date created;
	private Date modified;
	private String fullname;
	private boolean isActive;
	private boolean created_by_current_admin;
	private boolean created_by_current_user;
	private int upvote_count;
	private boolean user_has_upvoted;
	private List<String> userUpvotes = new ArrayList<String>(); 

}