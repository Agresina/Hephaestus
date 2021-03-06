package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.${class.name};
import repositories.${class.name}Repository;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ${class.name}Service {

	// Managed Repository ------------------------
	@Autowired
	private ${class.name}Repository ${class.name?uncap_first}Repository;

	// Constructor -------------------------------
	public ${class.name}Service() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public ${class.name} create() {
		return new ${class.name}();
	}

	public ${class.name} findOne(int ${class.name?uncap_first}Id) {
		${class.name} result;

		result = ${class.name?uncap_first}Repository.findOne(${class.name?uncap_first}Id);

		return result;
	}

	public Collection<${class.name}> findAll() {
		Collection<${class.name}> result;

		result = ${class.name?uncap_first}Repository.findAll();

		return result;
	}

	public ${class.name} save(${class.name} ${class.name?uncap_first}) {
		Assert.notNull(${class.name?uncap_first});
		return ${class.name?uncap_first}Repository.save(${class.name?uncap_first});
	}
	
	public void delete(${class.name} ${class.name?uncap_first}) {
		Assert.notNull(${class.name?uncap_first});
		Assert.isTrue(${class.name?uncap_first}Repository.exists(${class.name?uncap_first}.getId()));
		${class.name?uncap_first}Repository.delete(${class.name?uncap_first});
	}

	// Other business methods -----------------------

}