package com.ff.ui.button;

import org.zkoss.zkext.ui.button.events.ClickEvent;

import com.ff.Component;
import com.ff.annotation.Property;

public class Button extends Component{
	static {
		registerEvent(ClickEvent.class, Button.class,  ClickEvent.NAME, CE_IMPORTANT);		
	}
	 
	@Property
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
