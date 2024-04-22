package com.fit.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fit.model.Message;
import com.fit.model.User;
import com.fit.repo.MessageRepository;
import com.fit.repo.UserRepository;

@RestController
@RequestMapping(path = "/api/messages")
public class MessageController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MessageRepository messageRepo;
	
	@PostMapping
	public MappingJacksonValue sendMessage(@RequestBody Message message) {
		FilterProvider filterProvider = new SimpleFilterProvider()
				.addFilter("messageFilter", 
						SimpleBeanPropertyFilter.serializeAllExcept("receiver"));
		
		User sender = userRepo.findByUsername(message.getSender().getUsername());
		User receiver = userRepo.findByUsername(message.getReceiver().getUsername());
		
		Message newMessage = new Message();
		newMessage.setSender(sender);
		newMessage.setReceiver(receiver);
		newMessage.setContent(message.getContent());
		newMessage.setTimeSent(LocalDateTime.now());
		
		MappingJacksonValue json = new MappingJacksonValue(messageRepo.save(newMessage));
		json.setFilters(filterProvider);
		
		return json;
	}
	
	@PatchMapping
	public void openMessage(@RequestBody Message other) {
		Message message = messageRepo
				.findBySenderAndReceiverAndTimeSent(other.getSender(), other.getReceiver(), other.getTimeSent());
		
		message.setOpened(true);
		messageRepo.save(message);
	}
}
