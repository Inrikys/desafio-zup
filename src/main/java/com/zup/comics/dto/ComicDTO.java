package com.zup.comics.dto;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

import com.zup.comics.entities.Comic;


public class ComicDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Double price;
	private String isbn;
	private Integer discountDay;
	private boolean discountActive;
	private String description;
	
	private Set<CreatorDTO> creators;
	
	public ComicDTO() {

	}

	public ComicDTO(Long id, String name, Double price, String isbn, Integer discountDay, boolean discountActive,
			String description) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.isbn = isbn;
		this.discountDay = discountDay;
		this.discountActive = discountActive;
		this.description = description;
	}
	
	public ComicDTO(Comic entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.price = entity.getPrice();
		this.isbn = entity.getIsbn();
		this.discountDay = entity.getDiscountDay();
		this.discountActive = entity.getDiscountActive();
		this.description = entity.getDescription();
		
		if(entity.getCreators() != null) {
			this.creators = entity.getCreators().stream().map(x -> new CreatorDTO(x)).collect(Collectors.toSet());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getDiscountDay() {
		return discountDay;
	}

	public void setDiscountDay(Integer discountDay) {
		this.discountDay = discountDay;
	}

	public boolean isDiscountActive() {
		return discountActive;
	}

	public void setDiscountActive(boolean discountActive) {
		this.discountActive = discountActive;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<CreatorDTO> getCreators() {
		return creators;
	}
}
