package com.ff.ui.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ff.Component;
import com.ff.annotation.Property;
import com.ff.ui.button.event.SaveEvent;
import com.ff.ui.panel.Panel;
import com.ff.ui.store.Store;
import com.ff.utils.MapUtils;

public class Form extends Panel{
	static{
		registerEvent(SaveEvent.class, Form.class,  SaveEvent.NAME, CE_IMPORTANT);
	}
	
	@Property
	private Store store;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
		store.addForm(this);
	}
	
	public void loadModel(Map model){
		loadModelRecursive(this,model);
		
	//	updateClient("model", model);
	}
	
	
	private void loadModelRecursive(Component component, Map model){
		List<Component> childs = component.getChildren();
		for(Component comp:childs){
			if(comp instanceof Field){
				Field field = (Field)comp;
				String name = field.getName();
				Object val = MapUtils.getValue(name,model);//map.get(key);
				if(val==null){
					val = "";
				}
				if(field instanceof CheckBox){
					if(val.equals(field.getValue())){
						((CheckBox)field).setChecked(true);
					}else{
						((CheckBox)field).setChecked(false);
					}
				}else{
					field.setValue(val.toString());
				}
				 
				
				/*
				if(name!=null && !"".equals(name)){
					field.setValue("'"+model.get(name)+"'");
				}
				*/
			}else{
				loadModelRecursive(comp,model);
			}
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
