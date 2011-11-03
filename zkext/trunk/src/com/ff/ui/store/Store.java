package com.ff.ui.store;

import static com.ff.model.Model.MODEL_ID;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.ff.AbstractComponent;
import com.ff.annotation.Property;
import com.ff.entity.EntityDescriptor;
import com.ff.entity.EntityFactory;
import com.ff.entity.EntityRequest;
import com.ff.entity.FieldDescriptor;
import com.ff.model.Model;
import com.ff.service.ICrudService;
import com.ff.service.ServiceRequest;
import com.ff.service.ServiceResponse;
import com.ff.ui.button.event.DeleteEvent;
import com.ff.ui.button.event.EditEvent;
import com.ff.ui.button.event.SaveEvent;
import com.ff.ui.button.event.SearchEvent;
import com.ff.ui.form.Field;
import com.ff.ui.form.Form;
import com.ff.ui.form.Hidden;
import com.ff.ui.grid.Column;
import com.ff.ui.grid.Grid;
import com.ff.ui.grid.event.SelectionChangeEvent;

public class Store extends AbstractComponent{
	
	static {
		registerEvent(SearchEvent.class, Store.class,  SearchEvent.NAME, CE_IMPORTANT);		
		registerEvent(SaveEvent.class, Store.class,  SaveEvent.NAME, CE_IMPORTANT);
		registerEvent(EditEvent.class, Store.class,  EditEvent.NAME, CE_IMPORTANT);
		registerEvent(DeleteEvent.class, Store.class,  DeleteEvent.NAME, CE_IMPORTANT);
		registerEvent(SelectionChangeEvent.class, Store.class,  SelectionChangeEvent.NAME, CE_IMPORTANT);
	}
	
	private List<Grid> grids = new ArrayList<Grid>();
	private List<Form> forms = new ArrayList<Form>();	
	private static final String ATTACHED_FIELD = "__attached";
	
	private EntityDescriptor entityDescriptor;
	private EntityRequest entityRequest ;
	private String name;
	private ICrudService crudService;
	private String entity;
	private List<Model> models;
	private Model selectedModel;
	private Integer nextId=0;
	private List<Store> stores = new ArrayList<Store>();
	 
	@Property
	private List<Model> modelUsed = new ArrayList<Model>();

	@Override
	public void init() {		 
		super.init();
		entityDescriptor = EntityFactory.createEntity(entity);
		createStores();				
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
				serviceRequest.setModel((Model)event.getData());
				save(serviceRequest);
				
			}
			
		});		
	}
	
	
	private void createStores(){
		EntityDescriptor ed = entityDescriptor;
		EntityRequest er = createEntityRequest();
		List<FieldDescriptor> fields = ed.getEntityFields();
		List<Store> stores = getStores();
		for(FieldDescriptor fd:fields){
			Boolean toCreate = true;
			for(Store store:stores){
				if(store.getEntity().equals(fd.getEntity())){
					toCreate = false;
					break;
				}
			}
			if(toCreate){
				Store newStore = newInstance(Store.class);
				newStore.setEntityRequest(er.getFieldByName(name));
				newStore.setName(fd.getName());
				newStore.setEntity(fd.getEntity());
				newStore.setCrudService(crudService);
				newStore.setParent(this);
				newStore.init();				
			}
		}
	}
	
	 
	
	private EntityRequest createEntityRequest(){
		EntityRequest er = new EntityRequest();
		for(Grid grid:grids){
			er.mergeWith(createEntityRequest(grid));	
		}
		
		for(Form form:forms){
			er.mergeWith(createEntityRequest(form));
		}		
		er.mergeWith(entityRequest);
		return er;
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
		Hidden hidden = newInstance(Hidden.class);
		hidden.setName(MODEL_ID);		
		form.appendChild(hidden);
		form.addEventListener("onSave", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {		 
				Map submittedData = (Map)event.getData();
				Form form = (Form)event.getTarget();
				form.setSubmittedValues(submittedData);
				Model model = form.getModel();
				
				ServiceRequest serviceRequest = new ServiceRequest();
				serviceRequest.setEntityDescriptor(entity);							
				serviceRequest.setModel(model);
				save(serviceRequest);								
			}
			
		});
		
		form.addEventListener("onEdit", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {		 
				Map submittedData = (Map)event.getData();
				Form form = (Form)event.getTarget();
				form.setSubmittedValues(submittedData);
				Model model = form.getModel();										
				editModel(model);									
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
				search();						
			}		
				
		});
		
		grid.addEventListener("onSave", new EventListener(){

			@Override
			public void onEvent(Event event) throws Exception {		 				
				ServiceRequest serviceRequest = new ServiceRequest();
				serviceRequest.setEntityDescriptor(entity);							
				serviceRequest.setModel((Model)event.getData());
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
				serviceRequest.setModel((Model)event.getData());
				ServiceResponse serviceResponse = crudService.delete(serviceRequest);	

			}
			
		});
		
		grid.addEventListener("onSelectionChange", new EventListener(){
			
			@Override
			public void onEvent(Event event) throws Exception {		 				
				Model data = new Model((Map)event.getData());				
				Integer id = (Integer)data.get("id");
				selectModel(id);

			}
		});
	}
	
	public void selectModel(Integer index){
		List<Form> forms = getForms();
		selectedModel = getModelById(index);
				
		for(Form form:forms){					
			form.setModel(selectedModel);
		}
	}
	
	public void editModel(Model model){		 
		Model memModel = null;
		Integer id = model.getId();
		if(id!=null){					
			memModel = getModelById(id);
			memModel.merge(model);		
			
		}			
		if(memModel==null){
			return;
		}
			
		for(Form form:forms){
			form.setModel(memModel);
		}
		for(Grid grid:grids){
			int index = grid.getSelectedIndex();
			 grid.removeAt(grid.getSelectedIndex());
			 grid.insertAt(index,memModel);					
			 grid.setSelectedIndex(index);
			 selectModel(index);
		}
	}
	
	protected ServiceRequest buildRequest(){
		ServiceRequest serviceRequest = new ServiceRequest();
		serviceRequest.setEntityDescriptor(entity);		
		EntityRequest er = createEntityRequest();
		serviceRequest.setEntityRequest(er);
		return serviceRequest;
	}
	
	public Model load(Object id){
		ServiceRequest serviceRequest = buildRequest();
		Model model = new Model();	 
		model.put(entityDescriptor.getPrimaryKey(), id);		
		serviceRequest.setModel(model);
		
		ServiceResponse response = doSearch(serviceRequest);
		
		List<Model> models = (List<Model>)response.getModel();
		if(model.size()>0){
			return models.get(0);
		}
		return null;
	}
	
	
	/* ********** SEARCH ************* */
	
	
	protected ServiceResponse doSearch(ServiceRequest serviceRequest){			
		ServiceResponse serviceResponse = crudService.read(serviceRequest);		
		models = (List<Model>)serviceResponse.getModel();
		nextId = 0;
		
		for(Model res:models){
			res.put(ATTACHED_FIELD, true);
			res.put(MODEL_ID, nextId++);					
		}
		for(Grid grid:grids){
			grid.clearRows();		
			for(Model res:models){
				grid.addRow(res);
			}			
		}			
		return serviceResponse;
	}
	
	protected ServiceResponse doSearch(){
		ServiceRequest serviceRequest = buildRequest();
		return doSearch(serviceRequest);
	}
		 	
	
	public List<Model> search(){
		ServiceResponse response = doSearch();
		List<Model> models = (List<Model>)response.getModel();		 
		return models;
	}
	/* ******************************* */
	
	
	public void save(ServiceRequest serviceRequest){
		Boolean toCreate = true;
		serviceRequest.setModel(merge(serviceRequest.getModel()));
		ServiceResponse serviceResponse = null;
		Model data = serviceRequest.getModel();
		if(data.keySet().contains(ATTACHED_FIELD)){
			String val = (String) (data.get(ATTACHED_FIELD)+"");
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
	
	private Model merge(Model object){
		Integer id = object.getId();
		if(id!=null){						
			Model model = getModelById(id);
			
			Set<String> keys = (Set) model.keySet();
			for(String key:keys){
				if(!object.containsKey(key)){
					object.put(key, model.get(key));
				}
			}				
		}
		return object;
	}
	
	public boolean areEquals(Model model1, Model model2){
		String pk = entityDescriptor.getPrimaryKey();
		Object val1 = model1.get(pk);
		Object val2 = model2.get(pk);
		if(val1==null || val2 == null){
			return false;
		}
		if(val1.equals(val2)){
			return true;
		}
		return false;
	}
	
	@Deprecated
	private void createEntities(){		
		List<String> created = new ArrayList<String>();
		List<String> toCreate = new ArrayList<String>();
		toCreate.add(entity);
		while(toCreate.size()>0){
			String name = toCreate.get(0);
			if(!created.contains(name)){
				try{
					EntityDescriptor ed = EntityFactory.createEntity(entity);
					Model obj = getEntityModel(ed);
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
	
	@Deprecated
	private Model getEntityModel(EntityDescriptor ed){
		
		Model obj = new Model();
		obj.put("name", ed.getFullName());
		List fields = new ArrayList();
		List<FieldDescriptor> entities = (List) ed.getFieldDescriptors();
		for(FieldDescriptor fd:entities){
			Model map = new Model();
			map.put("name", fd.getName());
			map.put("type",fd.getType());			
			map.put("entity", fd.getEntity());
			map.put("many",fd.getMany());
			
			fields.add(map);
		}	
		obj.put("fields", fields);
		return obj;
	}

	public Model getModelById(Integer id){
		
		for(Model map:models){
			try{				
				Integer currentId=map.getId();				 
				if(id.equals(currentId)){
					return map;
				}
			}catch(Exception e){
				
			}
		}
		
		return null;
	}
	
	public Store getStore(String name){
		List<Store> stores = getStores();
		for(Store store:stores){
			if(store.getName().equals(name)){
				return store;
			}
		}
		return null;
	}
	
	 

	public void addGrid(Grid grid){		
		grids.add(grid);		
	}
	
	public void addForm(Form form){
		forms.add(form);
	}
		
	public List<Grid> getGrids(){
		return grids;
	}
	
	public List<Form> getForms(){
		return forms;
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

	public List<Model> getModelUsed() {
		return modelUsed;
	}

	public void setModelUsed(List<Model> modelUsed) {
		this.modelUsed = modelUsed;
	}

	public List<Store> getStores() {
		return getChildrenOfClass(Store.class);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EntityRequest getEntityRequest() {
		return entityRequest;
	}

	public void setEntityRequest(EntityRequest entityRequest) {
		this.entityRequest = entityRequest;
	}

	 
	
}
