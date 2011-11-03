package com.ff.ui.panel;

import com.ff.Component;
import com.ff.annotation.Property;
import com.ff.ui.panel.event.CloseEvent;

public class Panel extends AbstractPanel{
	
	static{
		registerEvent(CloseEvent.class, Panel.class,  CloseEvent.NAME, CE_IMPORTANT);
		//BEFORECLOSE
		//BEFORECOLLAPSE
		//BEFOREEXPAND
		//COLLAPSE
		//EXPAND
		//ICONCHANGE
		//TITLECHANGE
	}
	
	private Boolean animCollapse;
	//bbar
	private String buttonAlign;
	//buttons
	@Property
	private Boolean closable;
	private String closeAction;
	private Boolean collapseDirection;
	private Boolean collapseFirst;
	private String collapseMode;
	private Boolean collapsed;
	private String collapsedCls;
	@Property
	private Boolean collapsible;
	//dockedItems
	//fbar
	private Boolean floatable;
	private Boolean frame;
	private Boolean frameHeader;
	private Boolean headerPosition;
	private Boolean hideCollapseTool;
	private String iconCls;
	//lbar
	private Integer minButtonWidth;
	private Boolean overlapHeader;
	private Component placeholder;
	private Boolean preventHeader;
	//rbar
	//tbar
	@Property
	private String title;
	private Boolean titleCollapse;
	//tools
	
	
	public void close(){
		callMethod("close", null);
	}
	
	public void collapse(String direction){
		
	}
	
	public void collapse(String direction, Boolean animate){
		
	}
	
	public void expand(Boolean animate){
		
	}
	
	public void toggleCollapse(){
		
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}

	public Boolean getClosable() {
		return closable;
	}

	public void setClosable(Boolean closable) {
		this.closable = closable;
	}

	public Boolean getCollapsible() {
		return collapsible;
	}

	public void setCollapsible(Boolean collapsible) {
		this.collapsible = collapsible;
	}
	
	
	
}
