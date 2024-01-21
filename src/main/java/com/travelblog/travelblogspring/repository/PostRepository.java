package com.travelblog.travelblogspring.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.travelblog.travelblogspring.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
	
	Optional<Post> findByTitleAndAuthorId(String title, long authorId);

}
