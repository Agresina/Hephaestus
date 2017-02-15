package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.NotBlank;;

@Entity
@Access(AccessType.PROPERTY)
public class Warehouse extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String address;
	private String name;
	
	// Relationships ---------------------------------------------------------
	private Stock stock;
	
	// Constructors -----------------------------------------------------------
	public Warehouse() {
		super();		
	}
	
	
	@NotBlank
	public getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@OneToMany(mappedBy = "warehouse")
	@Valid
	public Collection<Stock>getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
}