package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Consumer extends Actor {
	
	// Attributes ------------------------------------------------------------
	
	// Relationships ---------------------------------------------------------
	private OrderInvoice orderInvoice;
	private ShoppingCart shoppingCart;
	
	// Constructors -----------------------------------------------------------
	public Consumer() {
		super();		
	}
	
	
	@OneToMany(mappedBy = "consumer")
	@Valid
	public Collection<Orderinvoice>getOrderinvoice() {
		return orderinvoice;
	}

	public void setOrderinvoice(Orderinvoice orderinvoice) {
		this.orderinvoice = orderinvoice;
	}
	
	
	@OneToOne(optional = false)
	@Valid
	public ShoppingcartgetShoppingcart() {
		return shoppingcart;
	}

	public void setShoppingcart(Shoppingcart shoppingcart) {
		this.shoppingcart = shoppingcart;
	}
	
	
}