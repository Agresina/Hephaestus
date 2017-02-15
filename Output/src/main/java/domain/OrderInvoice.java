package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.persistence.Column;
import import javax.validation.constraints.NotNull;
import import javax.validation.constraints.Past;
import import javax.validation.constraints.NotBlank;;

@Entity
@Access(AccessType.PROPERTY)
public class OrderInvoice extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String ticket;
	private Date placementMoment;
	private String address;
	private Date momentCanceled;
	private Credit Card creditCard;
	private Date deliveredMoment;
	
	// Relationships ---------------------------------------------------------
	private Clerk clerk;
	private Consumer consumer;
	private OrderLine orderLine;
	
	// Constructors -----------------------------------------------------------
	public OrderInvoice() {
		super();		
	}
	
	
	@Column(unique=true)
	@Pattern{\
	@NotNull
	@Past
	@NotBlank
	public getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	
	@Column(unique=true)
	@Pattern{\
	@NotNull
	@Past
	@NotBlank
	public getPlacementmoment() {
		return placementmoment;
	}

	public void setPlacementmoment(Date placementmoment) {
		this.placementmoment = placementmoment;
	}
	
	
	@Column(unique=true)
	@Pattern{\
	@NotNull
	@Past
	@NotBlank
	public getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public getMomentcanceled() {
		return momentcanceled;
	}

	public void setMomentcanceled(Date momentcanceled) {
		this.momentcanceled = momentcanceled;
	}
	
	
	@Column(unique=true)
	@Pattern{\
	@NotNull
	@Past
	@NotBlank
	public getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(CreditCard creditcard) {
		this.creditcard = creditcard;
	}
	
	
	@Column(unique=true)
	@Pattern{\
	@NotNull
	@Past
	@NotBlank
	public getDeliveredmoment() {
		return deliveredmoment;
	}

	public void setDeliveredmoment(Date deliveredmoment) {
		this.deliveredmoment = deliveredmoment;
	}
	
	
	@ManyToOne(optional = false)
	@Valid
	public ClerkgetClerk() {
		return clerk;
	}

	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}
	
	
	@ManyToOne(mappedBy = "orderinvoice")
	@Valid
	public ConsumergetConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	
	
	@OneToMany(mappedBy = "orderinvoice", optional = false)
	@Valid
	public Collection<Orderline>getOrderline() {
		return orderline;
	}

	public void setOrderline(Orderline orderline) {
		this.orderline = orderline;
	}
	
	
}