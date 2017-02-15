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
public class IdentificatorService {

	// Managed Repository ------------------------
	@Autowired
	private IdentificatorRepository identificatorRepository;

	// Constructor -------------------------------
	public IdentificatorService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Identificator create() {
		return new Identificator();
	}

	public Identificator findOne(int identificatorId) {
		Identificator result;

		result = identificatorRepository.findOne(identificatorId);

		return result;
	}

	public Collection<Identificator> findAll() {
		Collection<Identificator> result;

		result = identificatorRepository.findAll();

		return result;
	}

	public Identificator save(Identificator identificator) {
		Assert.notNull(identificator);
		return identificatorRepository.save(identificator);
	}

	// Other business methods -----------------------

}