package com.ff.ui.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ff.Component;
import com.ff.annotation.Property;
import com.ff.model.Model;
import com.ff.ui.button.event.EditEvent;
import com.ff.ui.panel.Panel;
import com.ff.ui.store.Store;

public class Form extends Panel{
	static{
		registerEvent(EditEvent.class, Form.class,  EditEvent.NAME, CE_IMPORTANT);
	}
	
	@Property
	private Store store;

	public Store getStore(String field){
		return store.getStore(field);
	}
	
	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
		store.addForm(this);
	}
	
	public void setSubmittedValues(Map map){
		List<Field> fields = getFields();
		for(Field field:fields){
			field.setSubmittedValue(map);
		}		 
	}
		
	public Model getModel(){
		Model model = new Model();
		List<Field> fields = getFields();
		for(Field field:fields){
			field.createModelValue(model);
		}
		
		return model;
	}
	
	 
	public void setModel(Model model){
		List<Field> fields = getFields();
		for(Field field:fields){
			field.setModel(model);
		}
	}
	 
	 
	
	public List<Field> getFields(){
		List<Field> fields = new ArrayList();
		return getFieldsRecursive(this, fields);
		
	}
	
	private List<Field> getFieldsRecursive(Component component, List<Field> list){
		List<Component> childs = component.getChildren();
		
		for(Component comp:childs){
			if(comp instanceof Field){
				list.add((Field)comp);
			}else{
				list = getFieldsRecursive(comp,list);
			}
		}
		return list;
		
	}
	
}
