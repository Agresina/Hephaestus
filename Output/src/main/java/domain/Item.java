package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.NotBlank;;
import import javax.validation.constraints.NotNull;
import import javax.validation.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Item extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String SKU;
	private String name;
	private String description;
	private Double price;
	private String tags;
	private String picture_url;
	private boolean canceled;
	
	// Relationships ---------------------------------------------------------
	private Comment comment;
	private Stock stock;
	private Category category;
	private Tax tax;
	private Quantity quantity;
	
	// Constructors -----------------------------------------------------------
	public Item() {
		super();		
	}
	
	
	@Pattern(\
	@NotBlank
	@NotNull
	@Url
	public getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	
	@Pattern(\
	@NotBlank
	@NotNull
	@Url
	public getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Pattern(\
	@NotBlank
	@NotNull
	@Url
	public getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Pattern(\
	@NotBlank
	@NotNull
	@Url
	public getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	public getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
	@Pattern(\
	@NotBlank
	@NotNull
	@Url
	public getPicture_url() {
		return picture_url;
	}

	public void setPicture_url(String picture_url) {
		this.picture_url = picture_url;
	}
	
	
	public getCanceled() {
		return canceled;
	}

	public void setCanceled(Boolean canceled) {
		this.canceled = canceled;
	}
	
	
	@OneToMany(mappedBy = "item", optional = false)
	@Valid
	public Collection<Comment>getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
	
	@OneToMany(mappedBy = "item", optional = false)
	@Valid
	public Collection<Stock>getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
	@ManyToOne(optional = false)
	@Valid
	public CategorygetCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	@ManyToOne(optional = false)
	@Valid
	public TaxgetTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}
	
	
	@OneToMany(mappedBy = "item", optional = false)
	@Valid
	public Collection<Quantity>getQuantity() {
		return quantity;
	}

	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
	
	
}