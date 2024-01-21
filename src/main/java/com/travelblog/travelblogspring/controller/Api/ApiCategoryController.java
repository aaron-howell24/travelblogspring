package com.travelblog.travelblogspring.controller.Api;

import java.net.URI;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.travelblog.travelblogspring.Exceptions.ObjectDoesNotExistException;
import com.travelblog.travelblogspring.Exceptions.ObjectExistsException;
import com.travelblog.travelblogspring.model.Category;
import com.travelblog.travelblogspring.model.dto.CategoryDto;
import com.travelblog.travelblogspring.repository.CategoryRepository;

@Controller
@RequestMapping("/api/category")
public class ApiCategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@PostMapping("/create")
	public ResponseEntity<Object> create(
			@RequestBody CategoryDto categoryDto
			) {
		
		if(categoryRepository.findByName(categoryDto.getName()).isPresent()) {
			throw new ObjectExistsException("Category already exists with name: " + categoryDto.getName()); 
		}
		
		Category category = new Category();
		category.setName(categoryDto.getName());
		
		Category savedCategory = categoryRepository.save(category);
		
		URI location = ServletUriComponentsBuilder.fromPath("/api/category/{id}")
				.buildAndExpand(savedCategory.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(
		@RequestBody CategoryDto categoryDto,
		@PathVariable long id
		){
		Optional<Category> categoryOptional = categoryRepository.findById(id);

		if(categoryOptional.isEmpty()) {
			throw new ObjectDoesNotExistException("No category found with id: " + id);
		}
		
		Boolean updated = false;
		
		Category managedCategory = categoryOptional.get();
		
		if(!StringUtils.equals(categoryDto.getName(), managedCategory.getName())) {
			managedCategory.setName(categoryDto.getName());
			updated = true;
		}
		
		if(updated) {
			categoryRepository.save(managedCategory);
		}
		
		URI location = ServletUriComponentsBuilder.fromPath("/api/category/{id}")
				.buildAndExpand(managedCategory.getId()).toUri();

		return ResponseEntity.created(location).build();
		
	}
}
