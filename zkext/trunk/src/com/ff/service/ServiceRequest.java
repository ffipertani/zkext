package com.ff.service;

import java.util.Map;

public class ServiceRequest {
	private String entityDescriptor;
	private Map model;
	 
	public Map getModel() {
		return model;
	}
	public void setModel(Map model) {
		this.model = model;
	}
	public String getEntityDescriptor() {
		return entityDescriptor;
	}
	public void setEntityDescriptor(String entityDescriptor) {
		this.entityDescriptor = entityDescriptor;
	}
	 
	
	
}
