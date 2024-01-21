package com.travelblog.travelblogspring.controller.Api;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.travelblog.travelblogspring.Exceptions.ObjectDoesNotExistException;
import com.travelblog.travelblogspring.Exceptions.ObjectExistsException;
import com.travelblog.travelblogspring.model.Author;
import com.travelblog.travelblogspring.model.Post;
import com.travelblog.travelblogspring.model.dto.PostDto;
import com.travelblog.travelblogspring.repository.AuthorRepository;
import com.travelblog.travelblogspring.repository.PostRepository;

@Controller
@RequestMapping("/api/post")
public class ApiPostController {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private AuthorRepository authorRepository;

	@PostMapping("/create")
	public ResponseEntity<Object> create(
			@RequestBody PostDto postDto
			) {
		
		Author author = authorRepository.findById(postDto.getAuthorId()).get();
		
		Hibernate.initialize(author.getUser());
		
		if(postRepository.findByTitleAndAuthorId(postDto.getTitle(), postDto.getAuthorId()).isPresent()) {
			throw new ObjectExistsException("Post already exists with title : " + postDto.getTitle() + " for author: " + author.getUser().getFirstName() + " " + author.getUser().getLastName()); 
		}
		
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setBody(postDto.getBody());
		post.setAuthor(author);

		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromPath("/api/post/{id}")
				.buildAndExpand(savedPost.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Post get(
			@PathVariable long id
	) {
		Optional<Post> postOptional = postRepository.findById(id);
		if(postOptional.isEmpty()) {
			throw new ObjectDoesNotExistException("Post not found for that Id: " + id);
		}
		
		Post post = postOptional.get();
		
		Hibernate.initialize(post.getCategory());
		Hibernate.initialize(post.getAuthor());
		Hibernate.initialize(post.getAuthor().getUser());
		
		return post;
	}
	
	@GetMapping("/all")
	@ResponseBody
	public List<Post> getAll() {
		List<Post> posts = new ArrayList<Post>();
		postRepository.findAll().forEach(posts::add);
		if(posts.isEmpty()) {
			return posts;
		}
		
		posts.forEach(post -> {
			Hibernate.initialize(post.getCategory());
			Hibernate.initialize(post.getAuthor());
			Hibernate.initialize(post.getAuthor().getUser());
		});
		
		return posts;
	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<Object> update(
//		@RequestBody CategoryDto categoryDto,
//		@PathVariable long id
//		){
//		Optional<Category> categoryOptional = categoryRepository.findById(id);
//
//		if(categoryOptional.isEmpty()) {
//			throw new ObjectDoesNotExistException("No category found with id: " + id);
//		}
//		
//		Boolean updated = false;
//		
//		Category managedCategory = categoryOptional.get();
//		
//		if(!StringUtils.equals(categoryDto.getName(), managedCategory.getName())) {
//			managedCategory.setName(categoryDto.getName());
//			updated = true;
//		}
//		
//		if(updated) {
//			categoryRepository.save(managedCategory);
//		}
//		
//		URI location = ServletUriComponentsBuilder.fromPath("/api/category/{id}")
//				.buildAndExpand(managedCategory.getId()).toUri();
//
//		return ResponseEntity.created(location).build();
//		
//	}
}
