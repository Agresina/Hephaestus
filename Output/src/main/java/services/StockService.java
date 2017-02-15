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
public class StockService {

	// Managed Repository ------------------------
	@Autowired
	private StockRepository stockRepository;

	// Constructor -------------------------------
	public StockService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Stock create() {
		return new Stock();
	}

	public Stock findOne(int stockId) {
		Stock result;

		result = stockRepository.findOne(stockId);

		return result;
	}

	public Collection<Stock> findAll() {
		Collection<Stock> result;

		result = stockRepository.findAll();

		return result;
	}

	public Stock save(Stock stock) {
		Assert.notNull(stock);
		return stockRepository.save(stock);
	}

	// Other business methods -----------------------

}