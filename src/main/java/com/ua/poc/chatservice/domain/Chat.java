package com.ua.poc.chatservice.domain;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Chat {

	public Chat() {

	}

	public Chat(Integer id, String text) {

		this.id = id;
		this.text = text;
		
	}

	public Chat(String username, String text, Integer timeout) {

		this.username = username;
		this.text = text;
		this.timeout = timeout;
		this.expiration_date = LocalDateTime.now().plusMinutes(timeout);
	}

	public Chat(Integer id, String username, String text, Integer timeout, LocalDateTime expiration_date) {

		this.id = id;
		this.username = username;
		this.text = text;
		this.timeout = timeout;
		this.expiration_date = expiration_date;
	}

	@JsonInclude(Include.NON_NULL)
	@Id
	private ObjectId _id;

	@JsonInclude(Include.NON_NULL)
	@Indexed(unique = true)
	private Integer id;
	
	@JsonInclude(Include.NON_NULL)
	@Indexed
	private String username;
	@JsonInclude(Include.NON_NULL)
	private String text;
	@JsonInclude(Include.NON_NULL)
	private Integer timeout;
	@JsonInclude(Include.NON_NULL)
	private LocalDateTime expiration_date;

}
