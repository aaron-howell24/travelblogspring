package com.travelblog.travelblogspring.repository;

import org.springframework.data.repository.CrudRepository;

import com.travelblog.travelblogspring.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
