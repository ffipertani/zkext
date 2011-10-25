package com.ff.ui.grid.event;

import org.zkoss.zk.ui.Component;

import com.ff.event.Event;

public class SelectionChangeEvent extends Event{
	public static String NAME = "onSelectionChange";
	
	public SelectionChangeEvent(Component target) {
		super(NAME,target);		
	}

	 
}
