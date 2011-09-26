package com.ff.test.controller;

import org.zkoss.spring.util.GenericSpringComposer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;

import com.ff.factory.UiFactory;

public class JJController extends GenericSpringComposer {
	protected UiFactory FACTORY = new UiFactory();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		Components.wireVariables(comp, this);
		super.doAfterCompose(comp);
		onLoad();
	}
	
	protected <T extends com.ff.AbstractComponent> T newInstance(Class<T> cls){
		return FACTORY.newInstance(cls);
	}
	
	protected void onLoad(){
		
	}
}
