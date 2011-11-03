package com.ff.ui.button.event;

import org.zkoss.zk.ui.Component;

import com.ff.event.Event;


public class EditEvent extends Event{
	public static String NAME = "onEdit";
	
	public EditEvent(Component target) {
		super(NAME,target);		
	}

	 
}
