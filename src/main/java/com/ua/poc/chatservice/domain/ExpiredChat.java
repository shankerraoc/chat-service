package com.ua.poc.chatservice.domain;

import java.time.LocalDateTime;

import lombok.ToString;


@ToString
public class ExpiredChat extends Chat{
	
public ExpiredChat(){
	
}
	
public ExpiredChat(Integer id , String username, String text, Integer timeout, LocalDateTime expiration_date){
		
		super(id, username, text, timeout, expiration_date);
	}

}
