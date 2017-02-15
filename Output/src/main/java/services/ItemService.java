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
public class ItemService {

	// Managed Repository ------------------------
	@Autowired
	private ItemRepository itemRepository;

	// Constructor -------------------------------
	public ItemService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Item create() {
		return new Item();
	}

	public Item findOne(int itemId) {
		Item result;

		result = itemRepository.findOne(itemId);

		return result;
	}

	public Collection<Item> findAll() {
		Collection<Item> result;

		result = itemRepository.findAll();

		return result;
	}

	public Item save(Item item) {
		Assert.notNull(item);
		return itemRepository.save(item);
	}

	// Other business methods -----------------------

}