package com.codeEditor.Editor.controller;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketForAsyncTransfer {

	
	public void ceateSocket() {
	try {
	ServerSocket ss=new ServerSocket(4444);
	Socket s=ss.accept();
	DataInputStream dis=new DataInputStream(s.getInputStream());
	
	
	
	
	
	
	
	
	
	}catch(Exception e)
	{}
	
	
	
	
	
	}
	}
