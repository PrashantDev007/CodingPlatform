package com.codeEditor.Editor.controller;

public class Response {

	private Integer status;
	private String result;
	public Response(Integer status, String result) {
		super();
		this.status = status;
		this.result = result;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	
	
	
	
	
	
	
	
}
