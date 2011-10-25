package com.ff.service;


public interface ICrudService {	
	public ServiceResponse create(ServiceRequest serviceRequest);
	public ServiceResponse delete(ServiceRequest serviceRequest);
	public ServiceResponse update(ServiceRequest serviceRequest);
	public ServiceResponse read(ServiceRequest serviceRequest);
}
