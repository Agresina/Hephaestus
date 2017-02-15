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
public class Subsystem0Service {

	// Managed Repository ------------------------
	@Autowired
	private Subsystem0Repository subsystem0Repository;

	// Constructor -------------------------------
	public Subsystem0Service() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Subsystem0 create() {
		return new Subsystem0();
	}

	public Subsystem0 findOne(int subsystem0Id) {
		Subsystem0 result;

		result = subsystem0Repository.findOne(subsystem0Id);

		return result;
	}

	public Collection<Subsystem0> findAll() {
		Collection<Subsystem0> result;

		result = subsystem0Repository.findAll();

		return result;
	}

	public Subsystem0 save(Subsystem0 subsystem0) {
		Assert.notNull(subsystem0);
		return subsystem0Repository.save(subsystem0);
	}

	// Other business methods -----------------------

}