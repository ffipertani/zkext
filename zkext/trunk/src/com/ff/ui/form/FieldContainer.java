package com.ff.ui.form;

import com.ff.annotation.Property;
import com.ff.ui.container.Container;


public class FieldContainer extends Container{
	@Property
	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
