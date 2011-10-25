package com.ff.dao;

import java.util.Map;

import com.ff.entity.EntityDescriptor;

public class DaoRequest {
	private EntityDescriptor entityDescriptor; 	
	private FilterCondition filterCondition;
	private Map model;
		 
	
	public Map getModel() {
		return model;
	}
	public void setModel(Map model) {
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
