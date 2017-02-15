package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends Message Folder {
	
	// Attributes ------------------------------------------------------------
	private Actor sender;
	private Actor recipient;
	private String subject;
	private String body;
	private Date moment;
	
	// Relationships ---------------------------------------------------------
	private Message Folder message Folder;
	private Actor actor;
	private Actor actor;
	
	// Constructors -----------------------------------------------------------
	public Message() {
		super();		
	}
	
	
	@NotNull
	public getSender() {
		return sender;
	}

	public void setSender(Actor sender) {
		this.sender = sender;
	}
	
	
	@NotNull
	public getRecipient() {
		return recipient;
	}

	public void setRecipient(Actor recipient) {
		this.recipient = recipient;
	}
	
	
	public getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	public getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
	public getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	
	@ManyToOne(optional = false)
	@Valid
	public MessageFoldergetMessageFolder() {
		return messageFolder;
	}

	public void setMessageFolder(MessageFolder messageFolder) {
		this.messageFolder = messageFolder;
	}
	
	
	@ManyToOne(mappedBy = "message")
	@Valid
	public ActorgetActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	
	@ManyToOne(mappedBy = "message")
	@Valid
	public ActorgetActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	
}