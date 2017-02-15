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
public class CommentService {

	// Managed Repository ------------------------
	@Autowired
	private CommentRepository commentRepository;

	// Constructor -------------------------------
	public CommentService() {
		super();
	}

	// Supporting services -----------------------

	// Simple CRUD methods -----------------------
	
	public Comment create() {
		return new Comment();
	}

	public Comment findOne(int commentId) {
		Comment result;

		result = commentRepository.findOne(commentId);

		return result;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = commentRepository.findAll();

		return result;
	}

	public Comment save(Comment comment) {
		Assert.notNull(comment);
		return commentRepository.save(comment);
	}

	// Other business methods -----------------------

}