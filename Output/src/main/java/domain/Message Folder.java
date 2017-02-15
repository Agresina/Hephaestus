package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Message Folder extends DomainEntity {
	
	// Attributes ------------------------------------------------------------
	private String name;
	
	// Relationships ---------------------------------------------------------
	private Actor actor;
	private Message message;
	
	// Constructors -----------------------------------------------------------
	public Message Folder() {
		super();		
	}
	
	
	public getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@ManyToOne(optional = false)
	@Valid
	public ActorgetActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	
	@OneToMany(mappedBy = "message folder", optional = false)
	@Valid
	public Collection<Message>getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	
}