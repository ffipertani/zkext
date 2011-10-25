package com.ff.ui.form;

import com.ff.annotation.Property;

public class CheckBox extends Field{
	
	@Property
	private Boolean checked;

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
