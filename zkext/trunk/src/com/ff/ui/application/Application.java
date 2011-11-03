package com.ff.ui.application;

import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.ff.controller.Controller;
import com.ff.interfaces.IControllerable;
import com.ff.ui.container.Container;

public class Application extends Container implements IControllerable, AfterCompose, IdSpace{

	private Controller controller;
	private String title;
	
	public Application(){
		
	}
	 
	

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.getPage().setTitle(title);
	}



	@Override
	public void afterCompose() {
		controller.doAfterCompose(this);
		
	}
 
}
