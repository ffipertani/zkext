package com.ff;

import com.ff.annotation.Property;
import com.ff.enums.UpdateType;
import com.ff.event.PropertyChangeEvent;


public class Component extends AbstractComponent{

	static {
		registerEvent(PropertyChangeEvent.class, Component.class, PropertyChangeEvent.NAME, CE_IMPORTANT);
		//ACTIVATE
		//ADDED
		//AFTERRENDER
		//BEFOREACTIVATE
		//BEFOREDEACTIVATE
		//BEFOREDESTROY
		//BEFOREHIDE
		//BEFORERENDER
		//BEFORESHOW
		//DEACTIVATE
		//DESTROY
		//DISABLE
		//ENABLE
		//HIDE
		//MOVE
		//REMOVED
		//RENDER
		//RESIZE
		//SHOW
	}	
		
	@Property
	String border;
	@Property
	Boolean showBorder;
	@Property 
	String cls;	
	@Property 
	Boolean disabled;
	@Property 
	String disabledCls;
	@Property 
	Boolean draggable;	
	@Property 
	Integer height;
	@Property 
	Boolean hidden;
	@Property 
	String hideMode;		
	@Property 
	String margin;
	@Property 
	Integer maxHeight;
	@Property 
	Integer maxWidth;
	@Property 
	Integer minHeight;
	@Property 
	Integer minWidth;
	@Property 
	String overCls;
	@Property 
	String padding;				
	@Property 
	String style;		
	@Property(updateServer=UpdateType.ALWAYS)
	Integer width;
	@Property
	String region;
	@Property
	String styleWidth;
	@Property
	String styleHeight;
	@Property
	Integer flex;
	@Property 
	String anchor;
	@Property 
	Float columnWidth;
	
	
	public void show(){
		callMethod("show", null);
	}
	
	
	
	public String getBorder() {
		return border;
	}
	public void setBorder(String border) {
		this.border = border;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public String getDisabledCls() {
		return disabledCls;
	}
	public void setDisabledCls(String disabledCls) {
		this.disabledCls = disabledCls;
	}
	public Boolean getDraggable() {
		return draggable;
	}
	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Boolean getHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	public String getHideMode() {
		return hideMode;
	}
	public void setHideMode(String hideMode) {
		this.hideMode = hideMode;
	}
	public String getMargin() {
		return margin;
	}
	public void setMargin(String margin) {
		this.margin = margin;
	}
	public Integer getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
	}
	public Integer getMaxWidth() {
		return maxWidth;
	}
	public void setMaxWidth(Integer maxWidth) {
		this.maxWidth = maxWidth;
	}
	public Integer getMinHeight() {
		return minHeight;
	}
	public void setMinHeight(Integer minHeight) {
		this.minHeight = minHeight;
	}
	public Integer getMinWidth() {
		return minWidth;
	}
	public void setMinWidth(Integer minWidth) {
		this.minWidth = minWidth;
	}
	public String getOverCls() {
		return overCls;
	}
	public void setOverCls(String overCls) {
		this.overCls = overCls;
	}
	public String getPadding() {
		return padding;
	}
	public void setPadding(String padding) {
		this.padding = padding;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;		
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getStyleWidth() {
		return styleWidth;
	}
	public void setStyleWidth(String styleWidth) {
		this.styleWidth = styleWidth;
	}
	public String getStyleHeight() {
		return styleHeight;
	}
	public void setStyleHeight(String styleHeight) {
		this.styleHeight = styleHeight;
	}
	public Integer getFlex() {
		return flex;
	}
	public void setFlex(Integer flex) {
		this.flex = flex;
	}
	public String getAnchor() {
		return anchor;
	}
	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}
	public Float getColumnWidth() {
		return columnWidth;
	}
	public void setColumnWidth(Float columnWidth) {
		this.columnWidth = columnWidth;
	}



	public Boolean getShowBorder() {
		return showBorder;
	}



	public void setShowBorder(Boolean showBorder) {
		this.showBorder = showBorder;
	}
	   
	
}
