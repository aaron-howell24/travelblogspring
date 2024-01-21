package com.travelblog.travelblogspring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.travelblog.travelblogspring.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	Optional<Category> findByName(String name);

}
