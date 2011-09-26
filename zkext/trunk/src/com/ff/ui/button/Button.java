package com.ff.ui.button;

import com.ff.Component;
import com.ff.annotation.Property;
import com.ff.event.ClickEvent;

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
