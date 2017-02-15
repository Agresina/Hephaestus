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
public class OrderInvoiceService {

	// Managed Repository ------------------------
	@Autowired
	private OrderInvoiceRepository orderInvoiceRepository;

	// Constructor -------------------------------
	public OrderInvoiceService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public OrderInvoice create() {
		return new OrderInvoice();
	}

	public OrderInvoice findOne(int orderInvoiceId) {
		OrderInvoice result;

		result = orderInvoiceRepository.findOne(orderInvoiceId);

		return result;
	}

	public Collection<OrderInvoice> findAll() {
		Collection<OrderInvoice> result;

		result = orderInvoiceRepository.findAll();

		return result;
	}

	public OrderInvoice save(OrderInvoice orderInvoice) {
		Assert.notNull(orderInvoice);
		return orderInvoiceRepository.save(orderInvoice);
	}

	// Other business methods -----------------------

}