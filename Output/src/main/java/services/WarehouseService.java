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
public class WarehouseService {

	// Managed Repository ------------------------
	@Autowired
	private WarehouseRepository warehouseRepository;

	// Constructor -------------------------------
	public WarehouseService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Warehouse create() {
		return new Warehouse();
	}

	public Warehouse findOne(int warehouseId) {
		Warehouse result;

		result = warehouseRepository.findOne(warehouseId);

		return result;
	}

	public Collection<Warehouse> findAll() {
		Collection<Warehouse> result;

		result = warehouseRepository.findAll();

		return result;
	}

	public Warehouse save(Warehouse warehouse) {
		Assert.notNull(warehouse);
		return warehouseRepository.save(warehouse);
	}

	// Other business methods -----------------------

}