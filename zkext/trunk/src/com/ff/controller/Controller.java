package com.ff.controller;

import java.util.List;

import org.zkoss.spring.util.GenericSpringComposer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;

import com.ff.AbstractComponent;
import com.ff.factory.UiFactory;

public class Controller extends GenericSpringComposer {
	protected UiFactory FACTORY = new UiFactory();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		Components.wireVariables(comp, this);
		super.doAfterCompose(comp);
		initAll(comp);
		onLoad();
	}
	
	private void initAll(Component comp){
		List children = comp.getChildren();
		for(Object child:children){
			if(child instanceof AbstractComponent){
				((AbstractComponent) child).init();
				initAll((AbstractComponent)child);
			}
		}
	}
	
	protected <T extends com.ff.AbstractComponent> T newInstance(Class<T> cls){
		return FACTORY.newInstance(cls);
	}
	
	protected void onLoad(){
		
	}
}
