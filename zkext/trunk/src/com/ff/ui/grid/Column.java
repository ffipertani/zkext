package com.ff.ui.grid;

import com.ff.annotation.Property;
import com.ff.ui.container.Container;

public class Column extends Container{
	
	@Property
	private String text;
	@Property
	private String name;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 

}