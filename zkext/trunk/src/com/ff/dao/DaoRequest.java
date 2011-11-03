package com.ff.dao;

import com.ff.entity.EntityDescriptor;
import com.ff.model.Model;

public class DaoRequest {
	private EntityDescriptor entityDescriptor; 	
	private FilterCondition filterCondition;
	private Model model;
		 
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public EntityDescriptor getEntityDescriptor() {
		return entityDescriptor;
	}
	public void setEntityDescriptor(EntityDescriptor entityDescriptor) {
		this.entityDescriptor = entityDescriptor;
	}
	public FilterCondition getFilterCondition() {
		return filterCondition;
	}
	public void setFilterCondition(FilterCondition filterCondition) {
		this.filterCondition = filterCondition;
	}
	 
	 
	
	
}
