package com.ff.event;

import org.zkoss.zk.ui.Component;

public abstract class Event extends  org.zkoss.zk.ui.event.Event{
	public static String NAME;
	
	public Event(Component target) {
		super(NAME,target);
	}

	public String getEventName(){
		return NAME;
	}
	 
}
