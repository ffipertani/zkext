package com.ff.service;

import com.ff.entity.EntityRequest;
import com.ff.model.Model;

public class ServiceRequest {
	private String entityDescriptor;
	private EntityRequest entityRequest;
	private Model model;
	 
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
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
