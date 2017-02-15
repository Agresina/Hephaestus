package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.Size;

@Entity
@Access(AccessType.PROPERTY)
public class UserAccount extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String username;
	private String password;
	
	// Relationships ---------------------------------------------------------
	
	// Constructors -----------------------------------------------------------
	public UserAccount() {
		super();		
	}
	
	
	@Unique, NotBlank,Size(5,32)
	@NotBlank,Size(5,32)
	public getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	@Unique, NotBlank,Size(5,32)
	@NotBlank,Size(5,32)
	public getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}