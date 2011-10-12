package com.ff.ui.button.event;

import org.zkoss.zk.ui.Component;

import com.ff.event.Event;
 

public class SearchEvent extends Event{
	public static String NAME = "onSearch";
	
	public SearchEvent(Component target) {
		super(NAME,target);		
	}

	 
}
