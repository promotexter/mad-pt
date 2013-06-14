package com.pt.mobile.madpt.enums;

public enum SocketEvent {

	NEW_LINE("new_line"),
	ERROR("error"),
	CONNECT("connect");
	
	final String value;

	private SocketEvent(String value) {
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
}
