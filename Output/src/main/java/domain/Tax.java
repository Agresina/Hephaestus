package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.Range;
import import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Tax extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String name;
	private Integer value;
	
	// Relationships ---------------------------------------------------------
	private Item item;
	
	// Constructors -----------------------------------------------------------
	public Tax() {
		super();		
	}
	
	
	public getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Range(min=0, max=100)
	@NotNull
	public getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	@OneToMany(mappedBy = "tax", optional = false)
	@Valid
	public Collection<Item>getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	
}