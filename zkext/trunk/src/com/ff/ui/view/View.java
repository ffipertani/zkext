package com.ff.ui.view;

import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.ff.controller.Controller;
import com.ff.interfaces.IControllerable;
import com.ff.ui.layout.FitLayout;
import com.ff.ui.panel.Panel;

public class View extends Panel implements IControllerable, AfterCompose, IdSpace{

	private Controller controller;
	
	public View(){
		this.setLayout(newInstance(FitLayout.class));
		this.setShowBorder(false);
		this.setClosable(true);
		this.setAutoScroll(true);
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void afterCompose() {
		controller.doAfterCompose(this);
		
	}
	
	
}
