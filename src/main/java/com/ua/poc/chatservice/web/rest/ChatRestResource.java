package com.ua.poc.chatservice.web.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ua.poc.chatservice.domain.ActiveChat;
import com.ua.poc.chatservice.domain.Chat;
import com.ua.poc.chatservice.service.ChatService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class ChatRestResource {
	
	@Autowired
	@Qualifier("chatServiceImpl")
	private ChatService chatService;
	
	@PostMapping("/chat")
	public ResponseEntity<String> saveChat(@Valid @RequestBody ActiveChat chat){
		
		return new ResponseEntity<String>("id="+chatService.saveChat(chat).getId(),HttpStatus.CREATED);
	}

	
	@GetMapping("/chat/{id}")
	public ResponseEntity<Chat> getChat(@PathVariable Integer id){

		log.info("Retrive Chat for the Id {}",id);
		Chat chat = chatService.findChatById(id);

		if (null !=  chat){
			
			Chat mCh = new Chat();	
			mCh.setUsername(chat.getUsername());
			mCh.setText(chat.getText());			
			mCh.setExpiration_date(chat.getExpiration_date());
			log.info("Chat found for the Id {}, chat info {}", id, mCh);			
			return new ResponseEntity<Chat>(mCh,HttpStatus.OK);
		}else{
			return new ResponseEntity<Chat>(HttpStatus.NOT_FOUND);
		}
		
	}


	
	@GetMapping("/chats/{username}")
	public ResponseEntity<List<Chat>> getChats(@PathVariable String username){
		
		List<Chat> activeChats = chatService.findChatsByusername(username).stream().
				map(c -> new Chat(c.getId(),c.getText())).collect(Collectors.toList());
		log.info("Total Active Chats for the user {} and count {}",username, activeChats.size());
		return new ResponseEntity<List<Chat>>(activeChats,HttpStatus.OK);
	}

	
}
