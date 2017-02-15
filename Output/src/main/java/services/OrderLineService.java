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
public class OrderLineService {

	// Managed Repository ------------------------
	@Autowired
	private OrderLineRepository orderLineRepository;

	// Constructor -------------------------------
	public OrderLineService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public OrderLine create() {
		return new OrderLine();
	}

	public OrderLine findOne(int orderLineId) {
		OrderLine result;

		result = orderLineRepository.findOne(orderLineId);

		return result;
	}

	public Collection<OrderLine> findAll() {
		Collection<OrderLine> result;

		result = orderLineRepository.findAll();

		return result;
	}

	public OrderLine save(OrderLine orderLine) {
		Assert.notNull(orderLine);
		return orderLineRepository.save(orderLine);
	}

	// Other business methods -----------------------

}