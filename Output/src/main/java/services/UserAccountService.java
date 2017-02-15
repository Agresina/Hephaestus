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
public class UserAccountService {

	// Managed Repository ------------------------
	@Autowired
	private UserAccountRepository userAccountRepository;

	// Constructor -------------------------------
	public UserAccountService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public UserAccount create() {
		return new UserAccount();
	}

	public UserAccount findOne(int userAccountId) {
		UserAccount result;

		result = userAccountRepository.findOne(userAccountId);

		return result;
	}

	public Collection<UserAccount> findAll() {
		Collection<UserAccount> result;

		result = userAccountRepository.findAll();

		return result;
	}

	public UserAccount save(UserAccount userAccount) {
		Assert.notNull(userAccount);
		return userAccountRepository.save(userAccount);
	}

	// Other business methods -----------------------

}