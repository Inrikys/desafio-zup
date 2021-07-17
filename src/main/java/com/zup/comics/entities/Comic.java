package com.zup.comics.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zup.comics.entities.enums.DiscountDay;
import com.zup.comics.util.DateUtil;

@Entity
@Table(name = "tb_comic")
public class Comic implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String name;
	private Double price;
	private String isbn;

	@Column(columnDefinition = "LONGVARCHAR")
	private String description;

	@JsonIgnore
	@ManyToMany(mappedBy = "comics")
	private Set<User> owners = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "tb_comic_creator", joinColumns = @JoinColumn(name = "comic_id"), inverseJoinColumns = @JoinColumn(name = "creator_id"))
	private Set<Creator> creators = new HashSet<>();

	public Comic() {

	}

	public Comic(Long id, String name, Double price, String isbn, String description) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.isbn = isbn;
		this.description = description;
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
		if(this.getDiscountActive()) {
			return price -= 0.1 * price;
		}
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean getDiscountActive() {
		if (this.getDiscountDay() != null) {
			return DateUtil.getCurrentDayOfWeek() == this.getDiscountDay();
		}
		return false;
	}

	public Integer getDiscountDay() {
		if (!this.isbn.trim().equals("")) {
			Character lastCharacter = this.isbn.charAt(this.isbn.length() - 1);

			if (lastCharacter != null) {
				if (lastCharacter == '0' || lastCharacter == '1') {
					return DiscountDay.MONDAY.getCode();
				} else if (lastCharacter == '2' || lastCharacter == '3') {
					return DiscountDay.TUESDAY.getCode();
				} else if (lastCharacter == '4' || lastCharacter == '5') {
					return DiscountDay.WEDNESDAY.getCode();
				} else if (lastCharacter == '6' || lastCharacter == '7') {
					return DiscountDay.THURSDAY.getCode();
				} else if (lastCharacter == '8' || lastCharacter == '9') {
					return DiscountDay.FRIDAY.getCode();
				}
			}
		}
		return null;
	}

	public Set<User> getOwners() {
		return owners;
	}

	public Set<Creator> getCreators() {
		return creators;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comic other = (Comic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
