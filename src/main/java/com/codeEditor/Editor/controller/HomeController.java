//package com.codeEditor.Editor.controller;
//
//import java.applet.AppletContext;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//	
//@RestController
//public class HomeController {
//
//	@Autowired
//	TaskSender ts;
//
//	@PostMapping("/run")
//	@CrossOrigin(origins = "*")
//	public String homePage(@RequestBody MultiValueMap<String, String> paramMap) throws InterruptedException {
//
//		String code = paramMap.get("code").get(0);
//		Code c=new Code();
//		c.setCode(code);
//		
//		ts.produce(c);
//		Thread.sleep(2000);
//		
//		Response response=new Response(200, SaveRequests.requests.get(c.getId())  );
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonString=null;
//		try {System.out.println(response.getResult());
//		jsonString = mapper.writeValueAsString(response);
//		}catch( JsonProcessingException e)
//		{}
////		String result="{\"status\":200, \"result\" : \""+SaveRequests.requests.get(c.getId())+"\"}";
//		System.out.println(jsonString);
//		return jsonString;
//	}
//	
//	
//	
//	
//	
//}
