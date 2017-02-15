package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class ExchangeRateTable extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String currency;
	private double value;
	
	// Relationships ---------------------------------------------------------
	
	// Constructors -----------------------------------------------------------
	public ExchangeRateTable() {
		super();		
	}
	
	
	@Min(0)
	public getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	public getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
	
}