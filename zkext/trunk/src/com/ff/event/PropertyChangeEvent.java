package com.ff.event;

import com.ff.Component;


public class PropertyChangeEvent  extends Event{
	public static String NAME = "onClick";
	private String property;
	private Object value;
	
	public PropertyChangeEvent(Component target, String property, Object value) {
		super(NAME, target);
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
