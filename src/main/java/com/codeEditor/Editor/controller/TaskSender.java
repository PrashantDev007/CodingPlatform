package com.codeEditor.Editor.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TaskSender {

	public TaskSender() {
	}

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	public static final String routing_key = "1234";
	public static final String exchange = "amq.direct";
	
	public void produce(Code code) {
		
		String 	s=null;
		try{ObjectMapper op=new ObjectMapper();
		s=op.writeValueAsString(code);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		rabbitTemplate.convertAndSend(exchange, routing_key, s);
		
	
	}

}