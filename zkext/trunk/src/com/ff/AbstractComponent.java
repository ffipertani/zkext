package com.ff;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.sys.ContentRenderer;

import com.ff.annotation.Property;
import com.ff.enums.UpdateType;
import com.ff.event.Event;
import com.ff.event.EventConfig;
import com.ff.factory.UiFactory;
import com.ff.ui.application.Application;
import com.ff.ui.layout.Layout;
import com.ff.ui.store.Store;

public abstract class AbstractComponent extends org.zkoss.zk.ui.AbstractComponent{

	protected Map<String,PropertyField> properties = new HashMap<String,PropertyField>();
	protected static Map<Class,List<EventConfig>> events = new HashMap<Class,List<EventConfig>>();
	protected static UiFactory FACTORY;
	
	static{
		if(FACTORY==null){
			FACTORY = new UiFactory();
		}
	}
	
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
		Class currentClass = this.getRealClass();
		
		while(currentClass!=org.zkoss.zk.ui.AbstractComponent.class){
			try{
				List<EventConfig> configs = events.get(currentClass);
				for(EventConfig config:configs){
					if(config.getName().equals(name)){
						return config.getEventClass();
					}
				}
			}catch(Exception e){
				
			}
			currentClass=currentClass.getSuperclass();
		}
		
		return null;
	}
	
	protected Event createEvent(String command, Component component, Class eventClass,Map data){
		try{
			Constructor con =  eventClass.getConstructor(Component.class);
			Event event = (Event)con.newInstance(component);
			event.setData(data);
			if(!data.isEmpty()){							
				Set keys = (Set)data.keySet();
				this.populateEvent(event, keys, data);
			}
			return (Event)event;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private Event populateEvent(Event event, Set keys, Map data)throws Exception{
		Iterator it = keys.iterator();
		while(it.hasNext()){
			String key = (String)it.next();//(String)keys.i.get(i);
			Method[] methods = event.getClass().getMethods();
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
		 
		return event;
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
				if(properties.get(field.getName().toLowerCase())==null){
					properties.put(field.getName().toLowerCase(), propertyField);
				}
			}
		}		 
	}
	
	public void addProperty(String name, PropertyField property)throws Exception{
		Field field = this.getRealClass().getField(name);
		if(field!=null){
			properties.put(name.toLowerCase(), property);
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
		final AbstractComponent component = (AbstractComponent) request.getComponent();
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
				render(renderer, pf.getName(), value);
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
	
	public void callMethod(String name, Object[] args){
		Map object = new HashMap();
		object.put("method", name);
		object.put("args",args);
		this.updateClient("callMethod", object);
	}
	
	public void updateClient(String property, Object value){
		smartUpdate(property, value, true);
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
			//if(child.getRealClass().isAssignableFrom(cls) ){
			if(cls.isAssignableFrom(child.getRealClass())){
				return (T)child;
			}
		}
		return null;
	}
	
	public <T> T getParentOfClass(Class<T> cls){
		try{
			AbstractComponent parent = 	(AbstractComponent)getParent(); 
			while(parent != null){
				if(parent.getRealClass().isAssignableFrom(cls) ){
					return (T)parent;
				}
				parent = (AbstractComponent)parent.getParent();
			}	
		}catch(Exception e){
			
		}
		return null;
	}
	
	public <T> List<T> getChildrenOfClass(Class<T> cls){
		List<AbstractComponent> children = getChildren();	 
		List<T> toReturn = new ArrayList<T>();
		for(int i=0;i<children.size();i++){
			Object o = children.get(i);
			if(o instanceof AbstractComponent){
				AbstractComponent child = (AbstractComponent)o;
				if(cls.isAssignableFrom(child.getRealClass()) ){
					toReturn.add((T)child);
				}
			}
		}		 
		return toReturn;
	}
	
	public List<Store> getStores(){
		Application app = getApplication();
		return app.getChildrenOfClass(Store.class);
	}
	
	public Application getApplication(){
		AbstractComponent parent = this;
		while(parent!=null || !(parent instanceof AbstractComponent) ){
			if(parent instanceof Application){
				return (Application)parent;
			}
			parent = (AbstractComponent)parent.getParent();			
		}
		return null;
	}
	
	public <T extends AbstractComponent> T newInstance(Class<T> c){
		return FACTORY.newInstance(c);
	}
	
	public <T extends AbstractComponent> T newInstance(String uri){
		return (T) FACTORY.newComponent(uri,this);
	}
	 
}
