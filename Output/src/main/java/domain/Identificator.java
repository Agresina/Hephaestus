package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Identificator extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	
	// Relationships ---------------------------------------------------------
	
	// Constructors -----------------------------------------------------------
	public Identificator() {
		super();		
	}
	
	
}