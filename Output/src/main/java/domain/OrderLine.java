package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.Min;
import import javax.validation.constraints.NotNull;
import import javax.validation.constraints.NotBlank;;
import import javax.validation.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class OrderLine extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private Integer amount;
	private Double price;
	private String SKU;
	private String name;
	private String description;
	private String nameTax;
	private Integer valueTax;
	
	// Relationships ---------------------------------------------------------
	
	// Constructors -----------------------------------------------------------
	public OrderLine() {
		super();		
	}
	
	
	@Min(0)
	@NotNull
	@Pattern(\
	@NotBlank
	@Range(min=0, max=100)
	public getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
	@Min(0)
	@NotNull
	@Pattern(\
	@NotBlank
	@Range(min=0, max=100)
	public getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	@Min(0)
	@NotNull
	@Pattern(\
	@NotBlank
	@Range(min=0, max=100)
	public getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}
	
	
	@Min(0)
	@NotNull
	@Pattern(\
	@NotBlank
	@Range(min=0, max=100)
	public getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Min(0)
	@NotNull
	@Pattern(\
	@NotBlank
	@Range(min=0, max=100)
	public getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@Min(0)
	@NotNull
	@Pattern(\
	@NotBlank
	@Range(min=0, max=100)
	public getNametax() {
		return nametax;
	}

	public void setNametax(String nametax) {
		this.nametax = nametax;
	}
	
	
	@Min(0)
	@NotNull
	@Pattern(\
	@NotBlank
	@Range(min=0, max=100)
	public getValuetax() {
		return valuetax;
	}

	public void setValuetax(Integer valuetax) {
		this.valuetax = valuetax;
	}
	
	
}