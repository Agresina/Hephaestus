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
public class Message FolderService {

	// Managed Repository ------------------------
	@Autowired
	private Message FolderRepository message FolderRepository;

	// Constructor -------------------------------
	public Message FolderService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Message Folder create() {
		return new Message Folder();
	}

	public Message Folder findOne(int message FolderId) {
		Message Folder result;

		result = message FolderRepository.findOne(message FolderId);

		return result;
	}

	public Collection<Message Folder> findAll() {
		Collection<Message Folder> result;

		result = message FolderRepository.findAll();

		return result;
	}

	public Message Folder save(Message Folder message Folder) {
		Assert.notNull(message Folder);
		return message FolderRepository.save(message Folder);
	}

	// Other business methods -----------------------

}