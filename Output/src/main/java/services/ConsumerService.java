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
public class ConsumerService {

	// Managed Repository ------------------------
	@Autowired
	private ConsumerRepository consumerRepository;

	// Constructor -------------------------------
	public ConsumerService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Consumer create() {
		return new Consumer();
	}

	public Consumer findOne(int consumerId) {
		Consumer result;

		result = consumerRepository.findOne(consumerId);

		return result;
	}

	public Collection<Consumer> findAll() {
		Collection<Consumer> result;

		result = consumerRepository.findAll();

		return result;
	}

	public Consumer save(Consumer consumer) {
		Assert.notNull(consumer);
		return consumerRepository.save(consumer);
	}

	// Other business methods -----------------------

}