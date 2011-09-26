package com.ff.ui.page;

import com.ff.ui.container.Container;

public class Page extends Container{

	 
	private String title;
	
	public Page(){
		
	}
	 

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
		this.getPage().setTitle(title);
	}
 
}
