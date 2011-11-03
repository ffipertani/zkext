package com.ff.entity;

import java.util.ArrayList;
import java.util.List;

public class EntityRequest {
	protected List<FieldRequest> fields = new ArrayList();

	public static void main(String[] args){
		String field = "regione.nome";
		EntityRequest r = new EntityRequest();
		r.addField(field);
	}
	
	
	/**
	 * 
	 * @param field può essere una propietà semplice tipo nome, 
	 * oppure una complessa tipo regione.descrizione 
	 */
	public void addField(String field){				 
		if(field == null){
			return;
		}
		String[] names = field.split("\\.");
		
		String name = names[0];
		FieldRequest fr = getFieldByName(name);
		if(fr==null){
			fr = new FieldRequest(name);					
		}
		if(names.length>1){
			String children = field.substring(field.indexOf(".")+1);	
			fr.addField(children);	
		}
				
		fields.add(fr);					 		 
	}
	
	public void mergeWith(EntityRequest entityRequest){
		if(entityRequest == null){
			return;
		}
		for(FieldRequest fr:getFields()){			
			for(FieldRequest fr2:entityRequest.getFields()){
				if(fr.getName().equals(fr2.getName())){
					fr.mergeWith(fr2);					
					break;
				}
			}		
		}
		for(FieldRequest fr2:entityRequest.getFields()){
			Boolean finded = false;
			for(FieldRequest fr:getFields()){			
				if(fr.getName().equals(fr2.getName())){
					finded = true;
					break;
				}
			}
			fields.add(fr2);
		}		
	}
	
	public FieldRequest getFieldByName(String name){
		for(FieldRequest request:fields){
			if(request.getName().equals(name)){
				return request;
			}
		}
		return null;
	}
	
	
	public List<FieldRequest> getFields() {
		return fields;
	}

	public void setFields(List<FieldRequest> fields) {
		this.fields = fields;
	}
	
	
}
