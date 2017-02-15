package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.NotBlank;;
import import javax.validation.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String name;
	private String description;
	private String picture_url;
	
	// Relationships ---------------------------------------------------------
	private Item item;
	
	// Constructors -----------------------------------------------------------
	public Category() {
		super();		
	}
	
	
	@NotBlank
	@Url
	public getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@NotBlank
	@Url
	public getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@NotBlank
	@Url
	public getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	
	
	@OneToMany(mappedBy = "category", optional = false)
	@Valid
	public Collection<Item>getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
}