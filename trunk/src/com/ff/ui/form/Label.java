package com.ff.ui.form;

import com.ff.Component;
import com.ff.annotation.Property;

public class Label extends Component{

	@Property
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
