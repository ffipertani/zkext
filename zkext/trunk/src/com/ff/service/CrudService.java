package com.ff.service;

import java.util.Map;

import com.ff.dao.DaoRequest;
import com.ff.dao.IDao;
import com.ff.entity.EntityDescriptor;
import com.ff.entity.EntityFactory;

public class CrudService extends Service implements ICrudService {
	IDao crudDao; 
	
 
	@Override
	public ServiceResponse create(ServiceRequest request)throws RuntimeException {
		ServiceResponse response = new ServiceResponse();
		try{						
			Map model = request.getModel();
			EntityDescriptor descriptor = EntityFactory.createEntity(request.getEntityDescriptor());
			DaoRequest daoRequest = new DaoRequest();
			daoRequest.setEntityDescriptor(descriptor);	
			
			String pk = descriptor.getPrimaryKey();
			if(pk!=null){
				Object pkValue = model.get(pk);
				if(pkValue==null){
					Object id = crudDao.nextVal(daoRequest); 
					model.put(pk,id);				
					daoRequest.setModel(model);
				}
			}
													
			response.setModel(crudDao.insert(daoRequest));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
		return response;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		ServiceResponse response = new ServiceResponse();
		try {		 
			DaoRequest daoRequest = new DaoRequest();
			daoRequest.setEntityDescriptor(EntityFactory.createEntity(request.getEntityDescriptor()));			 
			daoRequest.setModel(request.getModel());
			
			response.setModel(crudDao.delete(daoRequest));						
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);			
		}
		return response;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		ServiceResponse response = new ServiceResponse();
		try {		 
			DaoRequest daoRequest = new DaoRequest();
			daoRequest.setEntityDescriptor(EntityFactory.createEntity(request.getEntityDescriptor()));			 
			daoRequest.setModel(request.getModel());
			
			response.setModel(crudDao.update(daoRequest));						
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);			
		}
		return response;
	}

	@Override
	public ServiceResponse read(ServiceRequest request) {
		ServiceResponse response = new ServiceResponse();
		try {		 
			DaoRequest daoRequest = new DaoRequest();
			daoRequest.setEntityDescriptor(EntityFactory.createEntity(request.getEntityDescriptor()));			 
			daoRequest.setModel(request.getModel());
			
			response.setModel(crudDao.search(daoRequest));						
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);			
		}
		return response;
	}

	public IDao getCrudDao() {
		return crudDao;
	}

	public void setCrudDao(IDao crudDao) {
		this.crudDao = crudDao;
	}
	
	

}
