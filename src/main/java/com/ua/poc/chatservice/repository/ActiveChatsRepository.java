package com.ua.poc.chatservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.ua.poc.chatservice.domain.ActiveChat;

public interface ActiveChatsRepository extends MongoRepository<ActiveChat, Integer>{
	
	@Query("{id:?0 }")
	public Optional<ActiveChat> findById(Integer id);
	
	@Query("{ username : '?0' }")
	public List<ActiveChat> findByUsername(String username);

}
