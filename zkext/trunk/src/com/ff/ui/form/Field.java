package com.ff.ui.form;

import com.ff.Component;
import com.ff.annotation.Property;

public class Field extends Component{

	@Property
	private String name;
	@Property
	private String value;
	@Property
	private String label;
	@Property
	private String boxLabel;

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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBoxLabel() {
		return boxLabel;
	}

	public void setBoxLabel(String boxLabel) {
		this.boxLabel = boxLabel;
	}
	
	
	
}
