package com.ua.poc.chatservice.service;

import java.util.List;

import com.ua.poc.chatservice.domain.ActiveChat;
import com.ua.poc.chatservice.domain.Chat;

public interface ChatService {
	
	public Chat findChatById(Integer id);
	public List<ActiveChat> findChatsByusername(String usActiveChatme);
	public Chat saveChat(ActiveChat chat);
	

}
