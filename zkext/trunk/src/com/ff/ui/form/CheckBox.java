package com.ff.ui.form;

import java.util.Map;

import com.ff.annotation.Property;
import com.ff.model.Model;
import com.ff.ui.store.Store;
import com.ff.utils.MapUtils;

public class CheckBox extends Field{
	
	@Property
	private Boolean checked;
	private Model model;

	@Override
	public void init() {
		Form form = getForm();
		Store store = form.getStore(getName());
		if(store!=null){					
			model = store.load(getValue());				
		}
	}
	
	public void createModelValue(Model model){
		if(getChecked()){
			MapUtils.setValue(model, getName(), this.model);
		}
	}
	
	private void doCheck(){
		setChecked(null);
		setChecked(true);		
	}
	
	private void doUncheck(){
		setChecked(null);
		setChecked(false);
	}
	
	public void setModel(Model model){
		try{
			Model currentModel = (Model) MapUtils.getValue(model,getName());
			Store store = getForm().getStore(getName());
			
			if(currentModel == null){
				doUncheck();
				return;
			}			
			if(store.areEquals(currentModel, this.model)){
				doCheck();
			}else{
				doUncheck();
			}
		}catch(Exception e){
			doUncheck();
		}
		/*
		if(val == null){
			val = "";
		}
		if(val.equals(getValue())){
			setChecked(null);
			setChecked(true);
		}else{
			setChecked(null);
			setChecked(false);
		}
		*/
	}
	
	public void setSubmittedValue(Map data){
		Boolean val = (Boolean)data.get(this.getUuid());
		if(val==null){
			val = false;
		}
		this.checked = val;
	}
	 
	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
