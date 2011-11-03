package com.ff.entity;

import java.util.ArrayList;
import java.util.List;

public class FieldRequest extends EntityRequest{
	private String name;
	 
	
	public FieldRequest(){
		
	}
		
	public FieldRequest(String name){
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	 
}
