package com.ff.ui.layout;

import com.ff.annotation.Property;


public class TableLayout extends AutoLayout{
 
	@Property
	private Integer columns;
	
	public TableLayout(){
		type="table";
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}
	
	
}
