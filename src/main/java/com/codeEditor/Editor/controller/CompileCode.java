package com.codeEditor.Editor.controller;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.tomcat.jni.File;
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

		String rootPathForTheProject = System.getProperty("user.dir");

		java.io.File files = new java.io.File(rootPathForTheProject + "\\CompiledAndRunnapleCode\\" + code.getId());
		files.mkdir();
		String anyCommand = null;

		FileWriter fw = null;

		try {

			fw = new FileWriter(rootPathForTheProject + "\\CompiledAndRunnapleCode\\" + code.getId() + "\\"
					+ code.getId() + ".java");

//				 anyCommand = "\"C:\\MinGW\\bin\\g++\" -o"+" \""+rootPathForTheProject+"\\CompiledAndRunnapleCode\\"+code.getId()+"\\a.exe\" \""+rootPathForTheProject+"\\CompiledAndRunnapleCode\\"+code.getId()+"\\"+code.getId()+".cpp\"";

			anyCommand = "javac \"" + rootPathForTheProject + "/CompiledAndRunnapleCode/" + code.getId() + "/"
					+ code.getId() + ".java\"";

			fw.write(code.getCode());
			fw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		Process process = null;
		try {

			process = Runtime.getRuntime().exec(anyCommand, null);

		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println(e1 + "exceptions");
		}

		BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

		boolean error = false;
		String output = null;
		StringBuffer ans = new StringBuffer("");

		try {
			while ((output = stdError.readLine()) != null) {

				ans = ans.append(output + "\n");
				error = true;
			}
		} catch (Exception e) {
			System.out.println("compilation error");
		}

		if (error == true) {
			SaveRequests.requests.put(code.getId(), ans.toString());

			sendingAsyncDataToQueue.sendData(code);

		} else {

			run.execute(code);
		}

	}

}
