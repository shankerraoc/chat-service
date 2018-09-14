package com.ua.poc.chatservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ua.poc.chatservice.domain.ActiveChat;
import com.ua.poc.chatservice.domain.ExpiredChat;

public interface ExpiredChatsRepository extends MongoRepository<ExpiredChat, Integer>{

	@Query("{ id : ?0 }")
	public Optional<ExpiredChat> findById(Integer id);
	
	@Query("{ username : '?0' }")
	public List<ExpiredChat> findByUsername(String username);

}
