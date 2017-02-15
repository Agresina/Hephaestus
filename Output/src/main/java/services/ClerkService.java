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
public class ClerkService {

	// Managed Repository ------------------------
	@Autowired
	private ClerkRepository clerkRepository;

	// Constructor -------------------------------
	public ClerkService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Clerk create() {
		return new Clerk();
	}

	public Clerk findOne(int clerkId) {
		Clerk result;

		result = clerkRepository.findOne(clerkId);

		return result;
	}

	public Collection<Clerk> findAll() {
		Collection<Clerk> result;

		result = clerkRepository.findAll();

		return result;
	}

	public Clerk save(Clerk clerk) {
		Assert.notNull(clerk);
		return clerkRepository.save(clerk);
	}

	// Other business methods -----------------------

}