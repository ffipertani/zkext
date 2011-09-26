package com.ff.ui.form;

import com.ff.annotation.Property;

public class Text extends Field{

	@Property
	private Boolean allowBlank;

	public Boolean getAllowBlank() {
		return allowBlank;
	}

	public void setAllowBlank(Boolean allowBlank) {
		this.allowBlank = allowBlank;
	}
	
	
}
