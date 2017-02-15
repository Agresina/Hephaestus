package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.NotBlank;;
import import javax.validation.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Supermarket extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String name;
	private String description;
	private String logo;
	private String welcomeMessage;
	
	// Relationships ---------------------------------------------------------
	
	// Constructors -----------------------------------------------------------
	public Supermarket() {
		super();		
	}
	
	
	@NotBlank
	@Url
	public getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@NotBlank
	@Url
	public getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@NotBlank
	@Url
	public getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
	@NotBlank
	@Url
	public getWelcomemessage() {
		return welcomemessage;
	}

	public void setWelcomemessage(String welcomemessage) {
		this.welcomemessage = welcomemessage;
	}
	
	
}