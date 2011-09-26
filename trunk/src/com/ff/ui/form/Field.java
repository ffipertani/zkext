package com.ff.ui.form;

import com.ff.Component;
import com.ff.annotation.Property;

public class Field extends Component{

	@Property
	private String name;
	@Property
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
