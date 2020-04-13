package com.codeEditor.Editor.controller;

import org.json.simple.JSONValue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TaskReceiver {
	@Autowired
	CompileCode compileCode;

	@RabbitListener(queues = "Q1")
	public void listen(String code) throws InterruptedException {

		Code c = null;

		try {
			ObjectMapper op = new ObjectMapper();
			c = op.readValue(code, Code.class);
		} catch (Exception e) {
			System.out.println("task receiver exception");
		}

		compileCode.execute(c);

	}
}