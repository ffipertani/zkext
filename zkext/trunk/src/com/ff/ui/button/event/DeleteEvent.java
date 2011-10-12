package com.ff.ui.button.event;

import org.zkoss.zk.ui.Component;

import com.ff.event.Event;


public class DeleteEvent extends Event{
	public static String NAME = "onDelete";
	
	public DeleteEvent(Component target) {
		super(NAME,target);		
	}

	 
}
