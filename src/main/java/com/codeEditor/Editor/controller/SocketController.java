package com.codeEditor.Editor.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class SocketController {
	@Autowired
	TaskSender ts;
	
	@Autowired
	SendingAsyncDataToQueue sendingAsyncDataToQueue;
	
	@MessageMapping("/scorecard")
	@SendToUser("/topic/livescores")	
	public String getData(@RequestBody String paramMap  ) throws InterruptedException, JsonMappingException, JsonProcessingException
	{
		
		System.out.println(paramMap);
	
		ObjectMapper mapper = new ObjectMapper();
		Map<String,String> map=new HashMap<>();
		map=mapper.readValue(paramMap,Map.class);		 
		
		String code = map.get("code");
		String codeId=map.get("sessionId");
		

		if(code==null || code=="")return "{\"status\":200, \"result\" : \"input is empty\"}";
		
		Code c=new Code();
		c.setId(codeId);
		c.setCode(code);
		
		//when request came then put compiling.. for that id
		SaveRequests.requests.put(c.getId(), "compiling...");
				//
		
		
		ts.produce(c);
//		Thread.sleep(2000);
//		
//		Response response=new Response(200, SaveRequests.requests.get(c.getId())  );
//
//		String jsonString=null;
//		try {
//		jsonString = mapper.writeValueAsString(response);
//		}catch( JsonProcessingException e)
//		{System.out.println("exception");}
//		
//		System.out.println(jsonString+"final result");
//		map.clear();
//		SaveRequests.requests.clear();	
		
		sendingAsyncDataToQueue.sendData(c);
		
		return "";
		

	}
	

	
	
	
	
}
