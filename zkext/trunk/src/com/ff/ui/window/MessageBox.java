package com.ff.ui.window;

import java.util.HashMap;
import java.util.Map;

import com.ff.annotation.Property;

public class MessageBox extends Window{
	@Property
	private String title;
	@Property
	private String message;
	@Property
	private MessageBoxButton buttons;
	@Property
	private MessageBoxIcon icon;	
	@Property
	private Object showAs;
	
	public void show(){
		Map map = new HashMap();
		if(title!=null){
			map.put("title", title);	
		}		
		if(message!=null){
			map.put("msg",message);
		}
		if(buttons!=null){
			map.put("buttons","Ext.Msg."+buttons.toString());
		}
		if(icon!=null){
			map.put("icon","Ext.Msg."+icon.toString());
		}
		
		setShowAs(map);
		
	}
	
	public void show(String title, String message, MessageBoxButton buttons, MessageBoxIcon icon){
		Map map = new HashMap();
		if(title!=null){
			map.put("title", title);	
		}		
		if(message!=null){
			map.put("msg",message);
		}
		if(buttons!=null){
			map.put("buttons","Ext.Msg."+buttons.toString());
		}
		if(icon!=null){
			map.put("icon","Ext.Msg."+icon.toString());
		}
		
		setShowAs(map);
		
	}
	 
	
	
	public Object getShowAs() {
		return showAs;
	}



	public void setShowAs(Object showAs) {
		this.showAs = showAs;
	}



	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageBoxButton getButtons() {
		return buttons;
	}
	public void setButtons(MessageBoxButton buttons) {
		this.buttons = buttons;
	}
	public MessageBoxIcon getIcon() {
		return icon;
	}
	public void setIcon(MessageBoxIcon icon) {
		this.icon = icon;
	}
	
	
	
}
