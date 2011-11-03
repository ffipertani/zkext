package com.ff.ui.panel.event;

import org.zkoss.zk.ui.Component;

import com.ff.event.Event;

public class CloseEvent extends Event{
	public static String NAME = "onClose";
	
	public CloseEvent(Component target) {
		super(NAME,target);		
	}

	 
}
