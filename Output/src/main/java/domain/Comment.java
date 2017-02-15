package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.NotBlank;;
import import javax.validation.constraints.Range;
import import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String title;
	private String text;
	private Integer rating;
	
	// Relationships ---------------------------------------------------------
	private Item item;
	
	// Constructors -----------------------------------------------------------
	public Comment() {
		super();		
	}
	
	
	@NotBlank
	@Range(min=0, max= 5)
	@NotNull
	public getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	@NotBlank
	@Range(min=0, max= 5)
	@NotNull
	public getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
	@NotBlank
	@Range(min=0, max= 5)
	@NotNull
	public getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
	
	
	@ManyToOne(optional = false)
	@Valid
	public ItemgetItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
}