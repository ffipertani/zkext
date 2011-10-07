package com.ff.event;

import org.zkoss.zk.ui.Component;

 


public class ClickEvent extends Event{
	public static String NAME = "onClick";
	
	public ClickEvent(Component target) {
		super(NAME,target);		
	}

	/*
	
	public static final ClickEvent getEvent(AuRequest request) {
		final Component comp = request.getComponent();
		
		if(comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, request);
		
		final Map data=request.getData();
		
		if(data == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] {data, request});
		
		return new ClickEvent(request.getCommand(), comp);
	}
	*/
	 
}
