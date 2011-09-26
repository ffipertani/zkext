package com.ff.factory;

import net.sf.cglib.proxy.Enhancer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Richlet;
import org.zkoss.zk.ui.http.SimpleUiFactory;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zk.ui.sys.RequestInfo;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.ff.interceptor.SmartUpdateInterceptor;
import com.ff.ui.store.Store;

public class UiFactory extends SimpleUiFactory{

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
			return createComponent(page, parent, compdef, wClass);
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
		 /*
			Class wClass = (Class) compInfo.getComponentDefinition().getImplementationClass();
			if(Layout.class.isAssignableFrom(wClass)){
				Layout layout = (Layout) super.newComponent(page, null, compInfo);
				if(parent instanceof Container){
					Container newParent = (Container)parent;
					newParent.setLayout(layout);
				}
				return layout;
			}
			*/
		/*
		return super.newComponent(page, parent, compInfo);
		*/
		Class wClass =(Class)compInfo.getComponentDefinition().getImplementationClass();
		if(com.ff.ui.store.Store.class.isAssignableFrom(wClass)){
			return createStore(page,parent,compInfo,wClass);
		}
		
		if(com.ff.AbstractComponent.class.isAssignableFrom(wClass)){		
			return createComponent(page,parent,compInfo,wClass);
		}else{
			return super.newComponent(page, parent, compInfo);
		}
		
	}
	
	private Page createPage(Page page){
		page.addVariableResolver(new DelegatingVariableResolver());
		return page;
	}
	
	private Component createComponent(Page page, Component parent,
			Object info, Class wClass){
		//com.ff.Component newComp = (com.ff.Component)component;
		com.ff.AbstractComponent newComp = newInstance(wClass);		
		newComp.setPage(page);
		newComp.setParent(parent);
		applyProperties(newComp,info);		
		
		//newComp.init();
		return newComp;
		 
		
	}
	
	private void applyProperties(Component comp, Object info){
		if(info instanceof ComponentInfo){
			((ComponentInfo)info).applyProperties(comp);
		}else{
			((ComponentDefinition)info).applyProperties(comp);
		}
	}
	
	public <T extends com.ff.AbstractComponent> T newInstance(Class<T> c){	
		T newComp = (T)Enhancer.create(c, new SmartUpdateInterceptor());
		newComp._init();
		return newComp;
	}
	
	public Store createStore(Page page, Component parent,
			Object info, Class wClass){
		Store store = newInstance(wClass);		
		
		applyProperties(store,info);		
		store.setPage(page);				
		store.setParent(parent);
		//store.init();
		return store;
	}
}
