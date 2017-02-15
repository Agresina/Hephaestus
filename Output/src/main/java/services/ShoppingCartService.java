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
public class ShoppingCartService {

	// Managed Repository ------------------------
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	// Constructor -------------------------------
	public ShoppingCartService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public ShoppingCart create() {
		return new ShoppingCart();
	}

	public ShoppingCart findOne(int shoppingCartId) {
		ShoppingCart result;

		result = shoppingCartRepository.findOne(shoppingCartId);

		return result;
	}

	public Collection<ShoppingCart> findAll() {
		Collection<ShoppingCart> result;

		result = shoppingCartRepository.findAll();

		return result;
	}

	public ShoppingCart save(ShoppingCart shoppingCart) {
		Assert.notNull(shoppingCart);
		return shoppingCartRepository.save(shoppingCart);
	}

	// Other business methods -----------------------

}