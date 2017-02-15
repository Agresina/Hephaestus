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
public class SupermarketService {

	// Managed Repository ------------------------
	@Autowired
	private SupermarketRepository supermarketRepository;

	// Constructor -------------------------------
	public SupermarketService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Supermarket create() {
		return new Supermarket();
	}

	public Supermarket findOne(int supermarketId) {
		Supermarket result;

		result = supermarketRepository.findOne(supermarketId);

		return result;
	}

	public Collection<Supermarket> findAll() {
		Collection<Supermarket> result;

		result = supermarketRepository.findAll();

		return result;
	}

	public Supermarket save(Supermarket supermarket) {
		Assert.notNull(supermarket);
		return supermarketRepository.save(supermarket);
	}

	// Other business methods -----------------------

}