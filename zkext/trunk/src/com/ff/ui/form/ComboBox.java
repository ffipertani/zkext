package com.ff.ui.form;

import java.util.List;
import java.util.Map;

import com.ff.annotation.Property;
import com.ff.model.Model;
import com.ff.ui.store.Store;
import com.ff.utils.MapUtils;

public class ComboBox extends Picker{
	@Property
	private String itemLabel;
	@Property
	private String itemValue;
	@Property
	private List<Model> data;
	
	@Override
	public void init() {
		Form form = getForm();
		Store store = form.getStore(getName());
		if(store!=null){							
			List<Model> items = store.search();	
			setData(items);
		}
	}
	
	 
	public void setModel(Model model){
		Store store = getForm().getStore(getName());
		Model currentModel = (Model) MapUtils.getValue(model,getName());
		Object currentVal = null;
		
		for(Model mod:getData()){								
			if(store.areEquals(currentModel, mod)){
				currentVal = MapUtils.getValue(mod,itemValue);
			}
		}
		 
		if(currentVal == null){
			currentVal = "";
		}
		setValue(null);
		setValue(currentVal.toString()); 
	}
	
	public Model getSelectedModel(){
		for(Model model:getData()){
			Object val = MapUtils.getValue(model,itemValue);
			if(val.equals(getValue())){
				return model;
			}
		}
		return null;
	}
 
	public void createModelValue(Model model){	
		Model selectedModel = getSelectedModel();
		 
		MapUtils.setValue(model, getName(), selectedModel);
		 	 		
	}
 
	 
			
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
	public void setData(List<Model> data) {
		this.data = data;
	}
	public List<Model> getData() {
		return data;
	}
	
}
