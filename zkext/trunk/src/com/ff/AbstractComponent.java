package com.ff;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.sys.ContentRenderer;

import com.ff.annotation.Property;
import com.ff.enums.UpdateType;
import com.ff.event.EventConfig;
import com.ff.ui.layout.Layout;
import com.ff.ui.page.Page;
import com.ff.ui.store.Store;

public abstract class AbstractComponent extends org.zkoss.zk.ui.AbstractComponent{

	protected Map<String,PropertyField> properties = new HashMap<String,PropertyField>();
	protected static Map<Class,List<EventConfig>> events = new HashMap<Class,List<EventConfig>>();
	
	public AbstractComponent(){
 
	}
	
	
	public static void registerEvent(Class<? extends Event> eventClass,Class targetClass, String eventName, int type){				
		addClientEvent(targetClass,eventName,type);
		EventConfig config = new EventConfig();
		config.setType(type);
		config.setName(eventName);
		config.setEventClass(eventClass);
		synchronized (events) {
			List<EventConfig> configs = events.get(targetClass);	
			if(configs==null){
				configs = new ArrayList<EventConfig>();
			}
			configs.add(config);
			events.put(targetClass, configs);
		}
	}
	
	private Class searchEvent(Class cls, String name){
		List<EventConfig> configs = events.get(cls);
		for(EventConfig config:configs){
			if(config.getName().equals(name)){
				return config.getEventClass();
			}
		}
		return null;
	}
	
	protected Event createEvent(String command, Component component, Class eventClass,Map data){
		try{
			Object event = eventClass.getConstructor(String.class,Component.class).newInstance(command,component);
			if(!data.isEmpty()){			
				List keys = (List)data.keySet();
				for(int i=0;i<keys.size();i++){
					String key = (String)keys.get(i);
					Method[] methods = eventClass.getMethods();
					for(Method method:methods){
						if(method.getName().equalsIgnoreCase("set"+key)){
							/*
							Gson gson = new Gson();
							gson.fromJson(, classOfT)
							*/
							
							method.invoke(event, data.get(key));
						}
					}
				}
			}
			return (Event)event;
		}catch(Exception e){
			return null;
		}
	}
	
	
	public void _init(){
		initEvents();
		initProperties();
	}
	
	public void init(){
		
	}
	
	protected synchronized void initEvents(){
		Annotation[] annotations = this.getRealClass().getAnnotations();
	}
	
	protected synchronized void initProperties(){		
		Class currentClass = this.getRealClass();
	
		while(currentClass!=org.zkoss.zk.ui.AbstractComponent.class){
			initProperties(currentClass);
			currentClass=currentClass.getSuperclass();
		}
	}
	
	protected synchronized void initProperties(Class aclass){
		Field[] fields = aclass.getDeclaredFields();
		for(Field field:fields){
			Property property = field.getAnnotation(Property.class);
			if(property!=null){
				PropertyField propertyField = new PropertyField();
				propertyField.setUpdateClient(property.updateClient());
				propertyField.setUpdateServer(property.updateServer());
				propertyField.setOriginClass(aclass);
				propertyField.setName(field.getName());
				if(properties.get(field.getName())==null){
					properties.put(field.getName(), propertyField);
				}
			}
		}		 
	}
	
	public void addProperty(String name, PropertyField property)throws Exception{
		Field field = this.getRealClass().getField(name);
		if(field!=null){
			properties.put(name, property);
		}
	}
	
	public void removeProperty(String name){		 
		properties.remove(name);
	}
	
	 
	
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
	throws java.io.IOException {
		super.renderProperties(renderer);
		
		for(String property:properties.keySet()){
			renderNotNull(renderer, property);
		}	 
	}
	
	public void setProperty(String property, Object value){
		try{
			Field field = this.getRealClass().getDeclaredField(property);
			Property ann = field.getAnnotation(Property.class);
			if(ann!=null){
				if(ann.updateClient().equals(UpdateType.ALWAYS)
						||ann.updateClient().equals(UpdateType.CHANGE)){
					field.set(this, value);
					smartUpdate(property, value);
				}
			}
			
		}catch(Exception e){}	
	}
	
	@Override
	public void service(org.zkoss.zk.au.AuRequest request, boolean everError) {
		final String command = request.getCommand();
		final com.ff.Component component = (com.ff.Component) request.getComponent();
		final Map data=request.getData();
		Boolean handled = false;
		Class eventClass = searchEvent(component.getRealClass(),command);
		if(eventClass!=null){
			Event event = createEvent(command, component, eventClass, data);
			if(event!=null){
				handled = true;
				Events.postEvent(event);
			}
		}
		if(!handled){
			super.service(request, everError);
		}
	}	

	 
	
	protected void renderNotNull(ContentRenderer renderer, String property){		 
		try{
			PropertyField pf = properties.get(property);			
			 
			Object value = getPropertyValue(pf);
			if(value!=null){
				render(renderer, property, value);
			}	
		}catch(Exception e){}
	}
	
	protected Object getPropertyValue(PropertyField propertyField){
		try{
			Field field = propertyField.getOriginClass().getDeclaredField(propertyField.getName());
			if(field!=null){				
				field.setAccessible(true);
				Object value = field.get(this);
				return value;
			}
		}catch(Exception e){
			
		}
		return null;
	}
	
	protected void renderAlways(ContentRenderer renderer, String property){
		try{
			Field field = this.getRealClass().getDeclaredField(property);
			if(field!=null){
				Object value = field.get(this);			  
				render(renderer, property, value);				 
			}
		}catch(Exception e){}				 
	}
	
	public void updateClient(String property, Object value){
		smartUpdate(property, value);
	}
	
	public Map<String,PropertyField> getProperties(){
		return properties;
	}
	
	@Override
	public void setParent(Component parent) {
		if(parent instanceof Layout){
			Layout layout = (Layout)parent;
			Component grandParent = layout.getRealParent();
			super.setParent(grandParent);
		}else{
			super.setParent(parent);
		}
	}
	 
	public Class getRealClass(){
		Class cls = this.getClass();
		return cls.getSuperclass();
	}
	
	public <T> T getChildOfClass(Class<T> cls){
		List<AbstractComponent> children = getChildren();	 
		for(AbstractComponent child:children){			
			if(child.getRealClass().isAssignableFrom(cls) ){
				return (T)child;
			}
		}
		return null;
	}
	
	public <T> List<T> getChildrenOfClass(Class<T> cls){
		List<AbstractComponent> children = getChildren();	 
		List<T> toReturn = new ArrayList<T>();
		for(AbstractComponent child:children){			
			if(child.getRealClass().isAssignableFrom(cls) ){
				toReturn.add((T)child);
			}
		}
		return toReturn;
	}
	
	public List<Store> getStores(){
		Page page = getParentPage();
		return page.getChildrenOfClass(Store.class);
	}
	
	public Page getParentPage(){
		AbstractComponent parent = this;
		while(parent!=null || !(parent instanceof AbstractComponent) ){
			if(parent instanceof Page){
				return (Page)parent;
			}
			parent = (AbstractComponent)parent.getParent();			
		}
		return null;
	}
	 
}
