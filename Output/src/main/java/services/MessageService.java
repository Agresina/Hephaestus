package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Administrator;
import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class MessageService {

	// Managed Repository ------------------------
	@Autowired
	private MessageRepository messageRepository;

	// Constructor -------------------------------
	public MessageService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Message create() {
		return new Message();
	}

	public Message findOne(int messageId) {
		Message result;

		result = messageRepository.findOne(messageId);

		return result;
	}

	public Collection<Message> findAll() {
		Collection<Message> result;

		result = messageRepository.findAll();

		return result;
	}

	public Message save(Message message) {
		Assert.notNull(message);
		return messageRepository.save(message);
	}

	// Other business methods -----------------------

}