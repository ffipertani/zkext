package com.ff.event;

import org.zkoss.zk.ui.event.Event;


public class PropertyChangeEvent  extends Event{
	public static String NAME = "onClick";
	private String property;
	private Object value;
	
	public PropertyChangeEvent(String property, Object value) {
		super(NAME);
		this.property = property;
		this.value = value;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	
}
