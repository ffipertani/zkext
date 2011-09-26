package com.ff.ui.grid;

import java.util.List;

import com.ff.ui.panel.Panel;

public class Grid extends Panel{


	public List<Column> getColumns(){
		Columns columns = getChildOfClass(Columns.class);		 
		return columns.getChildrenOfClass(Column.class);		
	}
	
	public void addRow(Row row){
		Rows rows = getChildOfClass(Rows.class);
		row.setParent(rows);				 		 
	}

	 
	
	
}
