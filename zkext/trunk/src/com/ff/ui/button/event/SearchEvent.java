package com.ff.ui.grid.event;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

public class SearchEvent extends Event{
	public static String NAME = "onSearch";
	
	public SearchEvent(String name, Component target) {
		super(name,target);		
	}

	 
}
