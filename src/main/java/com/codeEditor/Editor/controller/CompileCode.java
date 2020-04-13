package com.codeEditor.Editor.controller;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jolokia.util.LogHandler.StdoutLogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CompileCode implements CompilerHelper {

	@Autowired
	SendingAsyncDataToQueue sendingAsyncDataToQueue;
	@Autowired
	Run run;
	
	@Override
	public void execute(Code code) {
			
		java.io.File files = new java.io.File(
				"C:\\Users\\devpr\\Desktop\\java projects\\Editor\\CompiledAndRunnapleCode\\" + code.getId());
		files.mkdir();

		try {
			FileWriter fw = new FileWriter("C:\\Users\\devpr\\Desktop\\java projects\\Editor\\CompiledAndRunnapleCode\\"
					+ code.getId() + "\\" + code.getId() + ".java");
			fw.write(code.getCode());
			fw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		Process process = null;
		String anyCommand = "javac \"C:/Users/devpr/Desktop/java projects/Editor/CompiledAndRunnapleCode/" +code.getId()+ "/" + code.getId() + ".java\"";
		try {
			process = Runtime.getRuntime().exec(anyCommand);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

			BufferedReader stdError = new BufferedReader(new 
			     InputStreamReader(process.getErrorStream()));

			boolean error=false;
			String output = null;
			StringBuffer ans=new StringBuffer("");  
			try {					
				while ((output = stdError.readLine()) != null) {
				    ans=ans.append( output+"\n");
				    error=true;
				}
			}catch(Exception e) {
				System.out.println("compilation error");
			}
			
			if(error==true)
			{
				SaveRequests.requests.put(code.getId(), ans.toString());
				
				sendingAsyncDataToQueue.sendData(code);
			
			}
			else
			{
				run.execute(code);		
			}
		
		
		

	}

}
