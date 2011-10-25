package com.ff.service;

import java.util.Map;

import com.ff.entity.EntityRequest;

public class ServiceRequest {
	private String entityDescriptor;
	private EntityRequest entityRequest;
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
	public EntityRequest getEntityRequest() {
		return entityRequest;
	}
	public void setEntityRequest(EntityRequest entityRequest) {
		this.entityRequest = entityRequest;
	}
	 
	
	
	
}
