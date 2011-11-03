package com.ff.ui.form;

import java.util.Map;

import com.ff.Component;
import com.ff.annotation.Property;
import com.ff.model.Model;
import com.ff.utils.MapUtils;

public class Field extends Component{

	@Property
	private String name;
	@Property
	private String value;
	@Property
	private String label;
	@Property
	private String boxLabel;
	
	public void setModel(Model model){
		Object val = MapUtils.getValue(model,name);
		if(val == null){
			val = "";
		}
		
		setValue(val.toString());
	}
	
	public void setSubmittedValue(Map data){
		Object val = data.get(this.getUuid());
		if(val==null){
			val = "";
		}
		this.value = val.toString();
	}
	
	public void createModelValue(Model model){
		 MapUtils.setValue(model, name, value);
	}
		
	public Form getForm(){
		return getParentOfClass(Form.class);
	}

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
