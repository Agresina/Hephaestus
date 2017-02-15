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
public class TaxService {

	// Managed Repository ------------------------
	@Autowired
	private TaxRepository taxRepository;

	// Constructor -------------------------------
	public TaxService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Tax create() {
		return new Tax();
	}

	public Tax findOne(int taxId) {
		Tax result;

		result = taxRepository.findOne(taxId);

		return result;
	}

	public Collection<Tax> findAll() {
		Collection<Tax> result;

		result = taxRepository.findAll();

		return result;
	}

	public Tax save(Tax tax) {
		Assert.notNull(tax);
		return taxRepository.save(tax);
	}

	// Other business methods -----------------------

}