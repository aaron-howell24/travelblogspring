package com.travelblog.travelblogspring.controller.Api;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.travelblog.travelblogspring.Exceptions.UserDoesNotExistException;
import com.travelblog.travelblogspring.model.Author;
import com.travelblog.travelblogspring.model.User;
import com.travelblog.travelblogspring.model.dto.AuthorDto;
import com.travelblog.travelblogspring.repository.AuthorRepository;
import com.travelblog.travelblogspring.repository.UserRepository;

@RestController
@RequestMapping("/api/author")
public class ApiAuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(
			@RequestBody AuthorDto authorDto
			) {
		
		Author author = new Author();
		Optional<User> user = userRepository.findById(authorDto.getUserId());
		if(user.isEmpty()) {
			throw new UserDoesNotExistException("User not found");
		}
		author.setUser(user.get());
		
		Author savedAuthor = authorRepository.save(author);
		
		URI location = ServletUriComponentsBuilder.fromPath("/api/author/{id}")
				.buildAndExpand(savedAuthor.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	public Author get(@PathVariable long id) throws NotFoundException {
		Optional<Author> author = authorRepository.findById(id);

		if (author.isEmpty()) {
			throw new NotFoundException();
		}
		return author.get();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(
			@RequestBody Author author,
			@PathVariable long id){
		Optional<Author> authorOptional = authorRepository.findById(id);

		if (authorOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		authorRepository.save(author);
		
		return ResponseEntity.noContent().build();

	}
		

}
