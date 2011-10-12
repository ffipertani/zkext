package com.ff.ui.button.event;

import org.zkoss.zk.ui.Component;

import com.ff.event.Event;


public class SaveEvent extends Event{
	public static String NAME = "onSave";
	
	public SaveEvent(Component target) {
		super(NAME,target);		
	}

	 
}
