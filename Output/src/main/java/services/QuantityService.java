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
public class QuantityService {

	// Managed Repository ------------------------
	@Autowired
	private QuantityRepository quantityRepository;

	// Constructor -------------------------------
	public QuantityService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Quantity create() {
		return new Quantity();
	}

	public Quantity findOne(int quantityId) {
		Quantity result;

		result = quantityRepository.findOne(quantityId);

		return result;
	}

	public Collection<Quantity> findAll() {
		Collection<Quantity> result;

		result = quantityRepository.findAll();

		return result;
	}

	public Quantity save(Quantity quantity) {
		Assert.notNull(quantity);
		return quantityRepository.save(quantity);
	}

	// Other business methods -----------------------

}