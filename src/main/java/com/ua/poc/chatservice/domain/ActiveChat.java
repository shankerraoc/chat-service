package com.ua.poc.chatservice.domain;

import java.time.LocalDateTime;

import lombok.ToString;


@ToString
public class ActiveChat extends Chat{
	
public ActiveChat(){
	
}
	
public ActiveChat(Integer id , String username, String text, Integer timeout, LocalDateTime expiration_date){
		
		super(id, username, text, timeout, expiration_date);
	}

	

}
