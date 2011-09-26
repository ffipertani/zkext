package com.ff.factory;

import net.sf.cglib.proxy.Enhancer;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.http.SimpleUiFactory;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zk.ui.metainfo.ComponentInfo;

import com.ff.interceptor.SmartUpdateInterceptor;

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
		if(com.ff.Component.class.isAssignableFrom(wClass)){		
			return createComponent(page, parent, compdef, wClass);
		}else{
			return super.newComponent(page, parent, compdef, clsnm);
		}
		
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
		Class wClass =(Class)compInfo.getComponentDefinition().getImplementationClass();
		if(com.ff.Component.class.isAssignableFrom(wClass)){		
			return createComponent(page,parent,compInfo,wClass);
		}else{
			return super.newComponent(page, parent, compInfo);
		}
		
	}
	
	private Component createComponent(Page page, Component parent,
			Object info, Class wClass){
		//com.ff.Component newComp = (com.ff.Component)component;
		com.ff.Component newComp = newIstance(wClass);		
							
		applyProperties(newComp,info);		
		newComp.setPage(page);
		newComp.setParent(parent);
		newComp.init();
		return newComp;
		 
		
	}
	
	private void applyProperties(Component comp, Object info){
		if(info instanceof ComponentInfo){
			((ComponentInfo)info).applyProperties(comp);
		}else{
			((ComponentDefinition)info).applyProperties(comp);
		}
	}
	
	public <T extends com.ff.Component> T newIstance(Class<T> c){	
		T newComp = (T)Enhancer.create(c, new SmartUpdateInterceptor());
		newComp._init();
		return newComp;
	}
}
