package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.NotBlank;;
import import javax.validation.constraints.Email;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String name;
	private String surname;
	private String email;
	private String phone;
	
	// Relationships ---------------------------------------------------------
	private Message Folder message Folder;
	private UserAccount userAccount;
	private Message message;
	
	// Constructors -----------------------------------------------------------
	public Actor() {
		super();		
	}
	
	
	@NotBlank
	@Email
	public getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@NotBlank
	@Email
	public getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
	@NotBlank
	@Email
	public getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	@OneToMany(mappedBy = "actor", optional = false)
	@Valid
	public Collection<MessageFolder>getMessageFolder() {
		return messageFolder;
	}

	public void setMessageFolder(MessageFolder messageFolder) {
		this.messageFolder = messageFolder;
	}
	
	
	@OneToOne(optional = false)
	@Valid
	public UseraccountgetUseraccount() {
		return useraccount;
	}

	public void setUseraccount(Useraccount useraccount) {
		this.useraccount = useraccount;
	}
	
	
	@OneToMany(mappedBy = "actor")
	@Valid
	public Collection<Message>getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	
}