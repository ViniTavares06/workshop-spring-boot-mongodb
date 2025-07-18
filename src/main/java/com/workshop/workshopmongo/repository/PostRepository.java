package com.workshop.workshopmongo.repository;

import com.workshop.workshopmongo.domain.Post;
import com.workshop.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
}
