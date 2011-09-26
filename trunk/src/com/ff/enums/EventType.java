package com.ff.enums;

public enum EventType{
	IMPORTANT(1),
	NORMAL(0);
	
	EventType(int key){
		this.key = key;
	}
	
	private final int key;
	public int key(){return key;}		
}