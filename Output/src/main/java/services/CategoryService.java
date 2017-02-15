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
public class CategoryService {

	// Managed Repository ------------------------
	@Autowired
	private CategoryRepository categoryRepository;

	// Constructor -------------------------------
	public CategoryService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Category create() {
		return new Category();
	}

	public Category findOne(int categoryId) {
		Category result;

		result = categoryRepository.findOne(categoryId);

		return result;
	}

	public Collection<Category> findAll() {
		Collection<Category> result;

		result = categoryRepository.findAll();

		return result;
	}

	public Category save(Category category) {
		Assert.notNull(category);
		return categoryRepository.save(category);
	}

	// Other business methods -----------------------

}