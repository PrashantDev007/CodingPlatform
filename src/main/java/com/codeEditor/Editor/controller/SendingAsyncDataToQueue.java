package com.codeEditor.Editor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SendingAsyncDataToQueue {

	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	public void sendData(Code code)
	{
		
		Response response=new Response(200, SaveRequests.requests.get(code.getId())  );
		ObjectMapper mapper = new ObjectMapper();
		String jsonString=null;
		try {
		jsonString = mapper.writeValueAsString(response);
		}catch( JsonProcessingException e)
		{System.out.println("exception");}
		
		System.out.println("this is the final code");
		
		messagingTemplate.convertAndSend("/topic/livescore-"+code.getId(),jsonString);
		
		
		
	}
	
}
