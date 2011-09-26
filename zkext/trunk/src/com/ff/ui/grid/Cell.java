package com.ff.ui.grid;

import com.ff.annotation.Property;
import com.ff.ui.container.Container;

public class Cell extends Container {

	@Property
	private String value;
	@Property
	private String name;

	 

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}