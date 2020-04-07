package com.codeEditor.Editor.controller;

import java.util.UUID;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


public class Code {

	private String code;
	private UUID id;

	public Code() {
		super();
		
		this.id = UUID.randomUUID();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public UUID getId() {
		return id;
	}

	
	
	



	
	
	
}
