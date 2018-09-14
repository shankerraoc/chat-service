package com.ua.poc.chatservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ua.poc.chatservice.domain.ActiveChat;
import com.ua.poc.chatservice.domain.Chat;
import com.ua.poc.chatservice.domain.ExpiredChat;
import com.ua.poc.chatservice.repository.ActiveChatsRepository;
import com.ua.poc.chatservice.repository.ExpiredChatsRepository;
import com.ua.poc.chatservice.service.ChatService;
import com.ua.poc.chatservice.util.RandomUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ActiveChatsRepository activeChatsRepository;
	
	@Autowired
	private ExpiredChatsRepository expiredChatsRepository;
	
	@Override
	public Chat findChatById(Integer id) {

		log.info("Retriving chat for the id {}" + id);
		Optional<ActiveChat> chat = activeChatsRepository.findById(id);
		log.info("Done retrieving  chat for the id {}" + id);
		return chat.isPresent()?chat.get():getExpiredChat(id);
	
	}

	@Override
	public List<ActiveChat> findChatsByusername(String username) {

		log.info("Retriving chat for the username {}" ,username);
		List<ActiveChat> activeChats = activeChatsRepository.findByUsername(username);
		log.info("Retriving chat for the username {}" , username);
		expireChats(activeChats,username);
		return activeChats;
	}
	

	@Override
	public ActiveChat saveChat(ActiveChat chat) {
	
		chat.setId(RandomUtil.randomId());
		chat.setExpiration_date(LocalDateTime.now().plusMinutes(chat.getTimeout() > 0 ? chat.getTimeout():60));
		return (ActiveChat)activeChatsRepository.save(chat);
	}
	
	private Chat getExpiredChat(Integer id) {
		Optional<ExpiredChat> chat = expiredChatsRepository.findById(id);
		return chat.isPresent()?chat.get():null;
	}

	@Async
	private void expireChats(List<ActiveChat> activeChats,String username) {

		log.info("Expiring chats retrieved for the user {} ", username);
		List<ExpiredChat> allChats = activeChats.stream().map(c -> new ExpiredChat(c.getId(),c.getUsername(),c.getText(),c.getTimeout(),c.getExpiration_date())).collect(Collectors.toList());
		allChats.forEach(c-> expiredChatsRepository.save(c));
		activeChats.forEach(c-> activeChatsRepository.delete(c));
		log.info("Done chats retrieved for the user {} ",username);
	}
	
}
