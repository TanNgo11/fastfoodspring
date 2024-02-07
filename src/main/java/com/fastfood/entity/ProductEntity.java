package com.fastfood.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.math3.stat.descriptive.summary.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "product")
public class ProductEntity extends BaseEntity {

	@Column(name = "productname")
	private String productName;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "price")
	private double price;

	@Column(name = "saleprice")
	private double salePrice;

	@Column(name = "status")
	private int status;
	
	@Column(name = "in_stock")
	private int inStock;
	
	@Column(name = "slug")
	private String slug;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private CategoryEntity category;

	@OneToMany(mappedBy = "productEntity")
	private List<ItemEntity> items = new ArrayList<>();

	@OneToMany(mappedBy = "productEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ImageEntity> imageEntities = new ArrayList<>();
	
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "product_relations",
        joinColumns = { @JoinColumn(name = "product_id") },
        inverseJoinColumns = { @JoinColumn(name = "related_product_id") }
    )
    private List<ProductEntity> relatedProducts = new ArrayList<>();



}
