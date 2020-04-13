package com.codeEditor.Editor.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.codecentric.boot.admin.server.web.PathUtils;

@Component
public class Run implements CompilerHelper {

	@Autowired
	SendingAsyncDataToQueue sendingAsyncDataToQueue;
	
	@Override
	public void execute(Code code) {
		
		

		File dir = new File( "C:/Users/devpr/Desktop/java projects/Editor/CompiledAndRunnapleCode/" +code.getId()+ "/");
		
		File[] matches = dir.listFiles(new FilenameFilter()
		{
		  public boolean accept(File dir, String name)
		  {
		     return name.endsWith(".class");
		  }
		});
		String fileName=matches[0].getName().substring(0,matches[0].getName().length() - 6);
		
		
		Process process = null;
		String anyCommand = "java -classpath \"C:/Users/devpr/Desktop/java projects/Editor/CompiledAndRunnapleCode/" +code.getId()+"\" "+fileName;
		
		try {
			process = Runtime.getRuntime().exec(anyCommand);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//-----------------------------
			
			BufferedReader stdOutput = new BufferedReader(new 
			InputStreamReader(process.getInputStream()));
			
		
			String output=null;
			
			StringBuffer ans=new StringBuffer("");  
			try {			
				
				while ((output = stdOutput.readLine()) != null) {
				    ans=ans.append( output+"\n");
				    
				}
			}catch(Exception e) {
				System.out.println("runtime error");
			}
				
			SaveRequests.requests.put(code.getId(), ans.toString());
			

			sendingAsyncDataToQueue.sendData(code);
		
		
		
		
		
		
		
		
		
		
}

	}


