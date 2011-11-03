package com.ff.ui.menu;

import java.util.List;

import com.ff.ui.tree.TreePanel;
import com.ff.ui.view.ViewStack;

public class Menu extends TreePanel{

	private ViewStack viewStack;
	
	@Override
	public void init() {	
		super.init();
		setShowRoot(false);		
		setCollapsible(true);
		List<MenuItem> items = getChildrenOfClass(MenuItem.class);
		
		MenuItem root = newInstance(MenuItem.class);
		root.setRoot(true);
		root.setParent(this);
		
		for(MenuItem child:items){
			child.setParent(root);			
		}
		
		
	}
		
	public MenuItem getSelectedItem(){
		return (MenuItem)super.getSelectedItem();
	}
	
	@Override
	protected void doSelectItem(String id) {
		super.doSelectItem(id);
		 
		MenuItem item = getSelectedItem();
		String view = item.getView();
		viewStack.createView(view);
		callMethod("collapse", null);
	};

	public ViewStack getViewStack() {
		return viewStack;
	}

	public void setViewStack(ViewStack viewStack) {
		this.viewStack = viewStack;
	}
	
	
}
