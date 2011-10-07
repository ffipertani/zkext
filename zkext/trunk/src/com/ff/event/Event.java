package com.ff.event;

import org.zkoss.zk.ui.Component;

public abstract class Event extends  org.zkoss.zk.ui.event.Event{
	public static String NAME;
	private Object data;
	
			
	public Event(String name, Component target) {
		super(name,target);
	}
	
	public void setData(Object data){
		this.data = data;
	}
	
	public Object getData(){
		return data;
	}

	public String getEventName(){
		return NAME;
	}
	 
}
