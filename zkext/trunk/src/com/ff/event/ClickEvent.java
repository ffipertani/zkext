package com.ff.event;

import java.util.Map;

import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;

public class ClickEvent extends Event{
	public static String NAME = "onClick";
	
	public ClickEvent(String name, Component target) {
		super(name,target);		
	}

	
	public static final ClickEvent getEvent(AuRequest request) {
		final Component comp = request.getComponent();
		
		if(comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, request);
		
		final Map data=request.getData();
		
		if(data == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] {data, request});
		
		return new ClickEvent(request.getCommand(), comp);
	}
	 
}
