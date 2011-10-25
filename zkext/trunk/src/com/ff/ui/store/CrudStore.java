package com.ff.ui.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.ff.annotation.Property;
import com.ff.entity.EntityDescriptor;
import com.ff.entity.EntityFactory;
import com.ff.entity.EntityRequest;
import com.ff.entity.FieldDescriptor;
import com.ff.service.ICrudService;
import com.ff.service.ServiceRequest;
import com.ff.service.ServiceResponse;
import com.ff.ui.form.Field;
import com.ff.ui.form.Form;
import com.ff.ui.grid.Column;
import com.ff.ui.grid.Grid;

public class CrudStore extends Store{
	
	
	
	private static final String ATTACHED_FIELD = "__attached";
	private static final String MODEL_ID = "__model_id";
	
	private ICrudService crudService;
	private String entity;
	private List<Map> models;
	private Map selectedModel;
	private Integer nextId=0;
	 
	@Property
	private List<Map> modelUsed = new ArrayList();

	@Override
	public void init() {		 
		super.init();
		try{
			 createEntities();
			
		}catch(Exception e){}
		
		for(Grid grid:getGrids()){
			initGrid(grid);
		}
		for(Form form:getForms()){
			initForm(form);
		}
		
		addEventListener("onSave", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {
				ServiceRequest serviceRequest = new ServiceRequest();
				serviceRequest.setEntityDescriptor(entity);							
				serviceRequest.setModel((Map)event.getData());
				save(serviceRequest);
				
			}
			
		});
		
	}
	
	private EntityRequest createEntityRequest(Grid grid){
		EntityRequest request = new EntityRequest();		
		List<Column> columns = grid.getColumns();
		for(Column column:columns){
			String name = column.getName();
			request.addField(name);
		}					
		return request;
	}
	
	private EntityRequest createEntityRequest(Form form){
		EntityRequest request = new EntityRequest();		
		List<Field> fields = form.getFields();
		for(Field field:fields){
			String name = field.getName();
			request.addField(name);
		}					
		return request;
	}
	
	private void initForm(Form form){
		form.addEventListener("onSave", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {		 
				ServiceRequest serviceRequest = new ServiceRequest();
				serviceRequest.setEntityDescriptor(entity);							
				serviceRequest.setModel((Map)event.getData());
				save(serviceRequest);								
			}
			
		});
	}
	
 	
	private void initGrid(Grid grid){
		Column column = newInstance(Column.class);
		column.setName(ATTACHED_FIELD);		
		column.setVisible(false);
		grid.addColumn(column);
		
		column = newInstance(Column.class);
		column.setName(MODEL_ID);		
		column.setVisible(false);
		grid.addColumn(column);
		
		grid.addEventListener("onSearch", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {		 
				ServiceRequest serviceRequest = new ServiceRequest();
				serviceRequest.setEntityDescriptor(entity);
				serviceRequest.setEntityRequest(createEntityRequest((Grid)event.getTarget()));
																
				ServiceResponse serviceResponse = crudService.read(serviceRequest);
				List<Map> results = (List<Map>) serviceResponse.getModel();				
				Grid grid = (Grid)event.getTarget();
				
				grid.clearRows();
						
				models = results;
				
				for(Map res:results){
					res.put(ATTACHED_FIELD, true);
					res.put(MODEL_ID, nextId++);
					//res.put("sesso.nome", "M");
					grid.addRow(res);
				}
				/*
			//	updateClient("setAddRow",results);
				updateClient("addModel",results);
								*/
			}		
				
		});
		
		grid.addEventListener("onSave", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {		 				
				ServiceRequest serviceRequest = new ServiceRequest();
				serviceRequest.setEntityDescriptor(entity);							
				serviceRequest.setModel((Map)event.getData());
				save(serviceRequest);
				Grid grid = (Grid)event.getTarget();
				grid.setCommit(true);
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
		
		grid.addEventListener("onSelectionChange", new EventListener(){
			
			@Override
			public void onEvent(Event event) throws Exception {		 
				List<Form> forms = getForms();
				Map data = (Map)event.getData();
				Integer id = (Integer)data.get("id");
				selectedModel = getModelById(id);
				for(Form form:forms){
					form.loadModel(selectedModel);
				}

			}
		});
	}
	
	public void save(ServiceRequest serviceRequest){
		Boolean toCreate = true;
		serviceRequest.setModel(merge(serviceRequest.getModel()));
		ServiceResponse serviceResponse = null;
		Map data = serviceRequest.getModel();
		if(data.keySet().contains(ATTACHED_FIELD)){
			String val = (String)data.get(ATTACHED_FIELD);
			data.remove(ATTACHED_FIELD);
			if(val==null || !"true".equals(val)){
				toCreate = true;
			}else{
				toCreate = false;
			}
		} 	
		if(toCreate){
			serviceResponse = crudService.create(serviceRequest);						
		}else{
			serviceResponse = crudService.update(serviceRequest);
		}
	}
	
	private Map merge(Map object){
		if(object.keySet().contains(MODEL_ID)){
			String strId = (String)object.get(MODEL_ID);
			Integer id = Integer.parseInt(strId);
			Map model = getModelById(id);
			
			Set<String> keys = (Set) model.keySet();
			for(String key:keys){
				if(!object.containsKey(key)){
					object.put(key, model.get(key));
				}
			}				
		}
		return object;
	}
	
	private void createEntities(){		
		List<String> created = new ArrayList();
		List<String> toCreate = new ArrayList();
		toCreate.add(entity);
		while(toCreate.size()>0){
			String name = toCreate.get(0);
			if(!created.contains(name)){
				try{
					EntityDescriptor ed = EntityFactory.createEntity(entity);
					Map obj = getEntityObject(ed);
					modelUsed.add(obj);
					created.add(name);
					List<FieldDescriptor> fields = ed.getEntityFields();
					for(FieldDescriptor field:fields){
						toCreate.add(field.getEntity());
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}			
			toCreate.remove(0);
		}								
	}
	
	private Map getEntityObject(EntityDescriptor ed){
		
		Map obj = new HashMap();
		obj.put("name", ed.getFullName());
		List fields = new ArrayList();
		List<FieldDescriptor> entities = (List) ed.getFieldDescriptors();
		for(FieldDescriptor fd:entities){
			Map map = new HashMap();
			map.put("name", fd.getName());
			map.put("type",fd.getType());			
			map.put("entity", fd.getEntity());
			map.put("many",fd.getMany());
			
			fields.add(map);
		}	
		obj.put("fields", fields);
		return obj;
	}

	public Map getModelById(Integer id){
		
		for(Map map:models){
			try{
				if(id.equals(map.get(MODEL_ID))){
					return map;
				}
			}catch(Exception e){
				
			}
		}
		
		return null;
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

	public List<Map> getModelUsed() {
		return modelUsed;
	}

	public void setModelUsed(List<Map> modelUsed) {
		this.modelUsed = modelUsed;
	}

	 
	
}
