package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class ShoppingCart extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String comment;
	
	// Relationships ---------------------------------------------------------
	private Quantity quantity;
	
	// Constructors -----------------------------------------------------------
	public ShoppingCart() {
		super();		
	}
	
	
	public getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	@OneToMany(mappedBy = "shoppingcart")
	@Valid
	public Collection<Quantity>getQuantity() {
		return quantity;
	}

	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
	
	
}