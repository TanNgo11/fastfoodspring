package com.fastfood.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewsDTO extends AbstractDTO<NewsDTO> {
	private String title;
	private String description;
	private AccountDTO accountDTO;
	private int status;
	private String slug;
	private String imageURL;
	private String dateFormat;
	private Date publishDate;
}
