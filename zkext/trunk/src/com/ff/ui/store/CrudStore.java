package com.ff.ui.store;

import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.ff.service.ICrudService;
import com.ff.service.ServiceRequest;
import com.ff.service.ServiceResponse;
import com.ff.ui.grid.Column;
import com.ff.ui.grid.Grid;

public class CrudStore extends Store{
	private static final String ATTACHED_FIELD = "__attached";
	
	private ICrudService crudService;
	private String entity;
	

	@Override
	public void init() {		 
		super.init();
		for(Grid grid:getGrids()){
			initGrid(grid);
		}
	}
	
	private void initGrid(Grid grid){
		Column column = newInstance(Column.class);
		column.setName(ATTACHED_FIELD);		
		column.setVisible(false);
		grid.addColumn(column);
		
		grid.addEventListener("onSearch", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {		 
				ServiceRequest serviceRequest = new ServiceRequest();
				serviceRequest.setEntityDescriptor(entity);
				ServiceResponse serviceResponse = crudService.read(serviceRequest);
				List<Map> results = (List<Map>) serviceResponse.getModel();				
				Grid grid = (Grid)event.getTarget();
				
				grid.clearRows();
							 
				for(Map res:results){
					res.put(ATTACHED_FIELD, true);
					grid.addRow(res);
				}				 
			}			
		});
		
		grid.addEventListener("onSave", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {		 
				Map data = (Map) event.getData();
				ServiceResponse serviceResponse = null;
				ServiceRequest serviceRequest = new ServiceRequest();
				serviceRequest.setEntityDescriptor(entity);							
				serviceRequest.setModel(data);
				if(data.keySet().contains(ATTACHED_FIELD)){
					String val = (String)data.get(ATTACHED_FIELD);
					data.remove(ATTACHED_FIELD);
					if(val==null || !"true".equals(val)){
						serviceResponse = crudService.create(serviceRequest);						
					}else{
						serviceResponse = crudService.update(serviceRequest);
					}
				}else{
					serviceResponse = crudService.create(serviceRequest);			
				}				 										
			}
			
		});
		
		grid.addEventListener("onDelete", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {		 
				ServiceRequest serviceRequest = new ServiceRequest();
				serviceRequest.setEntityDescriptor(entity);
				serviceRequest.setModel((Map)event.getData());
				ServiceResponse serviceResponse = crudService.delete(serviceRequest);	

			}
			
		});
	}

	public void addGrid(Grid grid){
		super.addGrid(grid);
		
	}
	
	
	public ICrudService getCrudService() {
		return crudService;
	}

	public void setCrudService(ICrudService crudService) {
		this.crudService = crudService;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	 
	
}
