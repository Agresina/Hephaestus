package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Quantity extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private Integer amount;
	
	// Relationships ---------------------------------------------------------
	private Item item;
	
	// Constructors -----------------------------------------------------------
	public Quantity() {
		super();		
	}
	
	
	@Min(1)
	public getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
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