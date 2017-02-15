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
public class ExchangeRateTableService {

	// Managed Repository ------------------------
	@Autowired
	private ExchangeRateTableRepository exchangeRateTableRepository;

	// Constructor -------------------------------
	public ExchangeRateTableService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public ExchangeRateTable create() {
		return new ExchangeRateTable();
	}

	public ExchangeRateTable findOne(int exchangeRateTableId) {
		ExchangeRateTable result;

		result = exchangeRateTableRepository.findOne(exchangeRateTableId);

		return result;
	}

	public Collection<ExchangeRateTable> findAll() {
		Collection<ExchangeRateTable> result;

		result = exchangeRateTableRepository.findAll();

		return result;
	}

	public ExchangeRateTable save(ExchangeRateTable exchangeRateTable) {
		Assert.notNull(exchangeRateTable);
		return exchangeRateTableRepository.save(exchangeRateTable);
	}

	// Other business methods -----------------------

}