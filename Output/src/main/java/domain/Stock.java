package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Stock extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private Integer amount;
	
	// Relationships ---------------------------------------------------------
	private Item item;
	private Warehouse warehouse;
	
	// Constructors -----------------------------------------------------------
	public Stock() {
		super();		
	}
	
	
	@NotNull
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
	
	
	@ManyToOne(mappedBy = "stock")
	@Valid
	public WarehousegetWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	
}