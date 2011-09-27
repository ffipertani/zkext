package com.ff.ui.store;

import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.ff.service.ICrudService;
import com.ff.service.ServiceRequest;
import com.ff.service.ServiceResponse;
import com.ff.ui.grid.Grid;

public class CrudStore extends Store{
	private ICrudService crudService;
	private String table;
 
	public void addGrid(Grid grid){
		super.addGrid(grid);
		grid.addEventListener("onSearch", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {		 
				ServiceRequest serviceRequest = new ServiceRequest();
				serviceRequest.setTable(table);
				ServiceResponse serviceResponse = crudService.read(serviceRequest);
				List<Map> results = (List<Map>) serviceResponse.getModel();				
				Grid grid = (Grid)event.getTarget();
				
				grid.clearRows();
				
			 
				for(Map res:results){
					grid.addRow(res);
				}
				 
			}
			
		});
	}
	
	
	public ICrudService getCrudService() {
		return crudService;
	}

	public void setCrudService(ICrudService crudService) {
		this.crudService = crudService;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
}
