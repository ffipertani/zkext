package com.ff.ui.form;

import com.ff.annotation.Property;

public class ComboBox extends Picker{
	@Property
	private String itemLabel;
	@Property
	private String itemValue;
	
	public String getItemLabel() {
		return itemLabel;
	}
	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}
	public String getItemValue() {
		return itemValue;
	}
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}
		 
}
