package com.fastfood.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fastfood.constant.SystemConstant;
import com.fastfood.entity.NewsEntity;
import com.fastfood.entity.ProductEntity;
import com.fastfood.repository.NewsRepository;
import com.fastfood.repository.ProductRepository;

@EnableScheduling
@Configuration
public class ScheduleConfig {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private NewsRepository newsRepository;

	@Scheduled(fixedRate = 60000)
	public void publishProducts() {
		Date now = new Date();
		List<ProductEntity> productsToPublish = productRepository.findByPublishDateLessThanEqualAndStatus(now,
				SystemConstant.SCHEDULED);

		List<NewsEntity> newsToPublish = newsRepository.findByPublishDateLessThanEqualAndStatus(now,
				SystemConstant.SCHEDULED);

		for (ProductEntity product : productsToPublish) {

			productRepository.updateProductStatusAndPublishDate(product.getId(), SystemConstant.PUBLIC, null);
		}

		for (NewsEntity newsEntity : newsToPublish) {
			
			newsRepository.updateNewsStatusAndPublishDate(newsEntity.getId(), SystemConstant.PUBLIC, null);
		}
	}

}
