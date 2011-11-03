package com.ff.controller;

import java.util.List;

import org.zkoss.spring.util.GenericSpringComposer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Components;

import com.ff.AbstractComponent;
import com.ff.factory.UiFactory;
import com.ff.ui.window.MessageBox;

public class Controller extends GenericSpringComposer {
	protected final UiFactory FACTORY = new UiFactory();
	public final MessageBox MessageBox =  newInstance(com.ff.ui.window.MessageBox.class);
	
	

	@Override
	public boolean doCatch(Throwable ex) throws Exception {		
		return super.doCatch(ex);
	}
	
	
	@Override
	public void doFinally() throws Exception {	
		super.doFinally();
	}
	
	
	@Override
	public void doAfterCompose(Component comp) {
		try{
			Components.wireVariables(comp, this);
			super.doAfterCompose(comp);
			initAll(comp);
			if(comp instanceof AbstractComponent){			
				MessageBox.setParent(((AbstractComponent)comp).getApplication());	
			}		
			onLoad();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
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
