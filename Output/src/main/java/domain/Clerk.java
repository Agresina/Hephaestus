package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Clerk extends Actor {
	
	// Attributes ------------------------------------------------------------
	
	// Relationships ---------------------------------------------------------
	private OrderInvoice orderInvoice;
	
	// Constructors -----------------------------------------------------------
	public Clerk() {
		super();		
	}
	
	
	@OneToMany(mappedBy = "clerk", optional = false)
	@Valid
	public Collection<Orderinvoice>getOrderinvoice() {
		return orderinvoice;
	}

	public void setOrderinvoice(Orderinvoice orderinvoice) {
		this.orderinvoice = orderinvoice;
	}
	
	
}