package com.ff.factory;

import java.util.List;

import net.sf.cglib.proxy.Enhancer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Richlet;
import org.zkoss.zk.ui.http.SimpleUiFactory;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zk.ui.metainfo.Property;
import org.zkoss.zk.ui.sys.RequestInfo;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.ff.AbstractComponent;
import com.ff.controller.Controller;
import com.ff.interceptor.SmartUpdateInterceptor;
import com.ff.interfaces.IControllerable;
import com.ff.ui.store.Store;
import com.ff.utils.StringUtils;

public class UiFactory extends SimpleUiFactory{
	
	public AbstractComponent newComponent(String uri, AbstractComponent parent){
		String path = uri.replaceAll("\\.","/");
		path+=".xml";
		String compDefinition = StringUtils.getResourceAsString(path);
		
		Component comp = Executions.createComponentsDirectly(compDefinition, "zul", parent, null);
		return (AbstractComponent)comp;
	}
	
	@Override	
	public Component newComponent(Page page, Component parent,
			ComponentDefinition compdef, String clsnm) {	
		Class wClass;	
		try{
			wClass = Class.forName(clsnm);		 
		}catch(Exception e){
			return null;
		}
		if(com.ff.AbstractComponent.class.isAssignableFrom(wClass)){		
			return createComponent(page, parent, compdef, wClass,null);
		}else{
			return super.newComponent(page, parent, compdef, clsnm);
		}
		
	}
	
	@Override
	public Page newPage(RequestInfo ri, PageDefinition pagedef, String path) {
		Page page = super.newPage(ri, pagedef, path);
		return createPage(page);
	}
	
	@Override
	public Page newPage(RequestInfo ri, Richlet richlet, String path) {	
		Page page = super.newPage(ri, richlet, path);		
		return createPage(page);
	}
	
	@Override	
	public Component newComponent(Page page, Component parent,
			ComponentInfo compInfo) { 	
		 
		Controller controller = null;
		
		Class wClass =(Class)compInfo.getComponentDefinition().getImplementationClass();
		if(com.ff.ui.store.Store.class.isAssignableFrom(wClass)){
			return createStore(page,parent,compInfo,wClass);
		}
		
		if(IControllerable.class.isAssignableFrom(wClass)){
				
			List prop = compInfo.getProperties();
			for(int i=0;i<prop.size();i++){
				Property p = (Property) compInfo.getProperties().get(i);
				if(p.getName().equals("controller")){													
					String controllerString = p.getRawValue();
					controller = createControllerInstance(controllerString);
					if(controller==null){
						try{
							controller = (Controller)Executions.evaluate(page, controllerString, Controller.class);
						}catch(Exception e){
							
						}
					}
					
					compInfo.getProperties().remove(i);
					break;					
				}
			}			
		}
		
	
		if(com.ff.AbstractComponent.class.isAssignableFrom(wClass)){		
			return createComponent(page,parent,compInfo,wClass,controller);
		}else{
			return super.newComponent(page, parent, compInfo);
		}
		
	}
	
	private Controller createControllerInstance(String cls){
		try{
			Class aClass = Class.forName(cls);
			return (Controller)aClass.newInstance();
		}catch(Exception e){
			return null;
		}
	}
	
	private Page createPage(Page page){
		
		page.addVariableResolver(new DelegatingVariableResolver());
		return page;
	}
	
	private Component createComponent(Page page, Component parent,
			Object info, Class wClass, Controller controller){
		//com.ff.Component newComp = (com.ff.Component)component;
		
		try{							
			com.ff.AbstractComponent newComp = newInstance(wClass);		
			newComp.setPage(page);
			newComp.setParent(parent);
			applyProperties(newComp,info);		
			
			if(IControllerable.class.isAssignableFrom(wClass)){
				if(controller == null){
					controller = new Controller();
				}	
				IControllerable controllerable = (IControllerable)newComp;
				controllerable.setController(controller);				
			}
			
			//newComp.init();
			return newComp;
		}catch(Exception  e){
			throw new RuntimeException(e);
		}
	}
	
	private void applyProperties(Component comp, Object info){
		if(info instanceof ComponentInfo){
			((ComponentInfo)info).applyProperties(comp);
		}else{
			((ComponentDefinition)info).applyProperties(comp);
		}
	}
	
	public <T extends com.ff.AbstractComponent> T newInstance(Class<T> c){		
		Enhancer e = new Enhancer();		
		
		/*
		e.setStrategy(new DefaultGeneratorStrategy() {
			protected ClassGenerator transform(ClassGenerator cg) throws Exception {
			ClassTransformer t = new InterceptFieldTransformer();
			return new TransformingClassGenerator(cg, t);
			}
			});
			*/
		T newComp = (T)e.create(c, new SmartUpdateInterceptor());
		newComp._init();
		return newComp;
	}
	 
	
	public Store createStore(Page page, Component parent,
			Object info, Class<Store> wClass){
		Store store = newInstance(wClass);		
		
		applyProperties(store,info);		
		store.setPage(page);				
		store.setParent(parent);
		//store.init();
		return store;
	}
}
