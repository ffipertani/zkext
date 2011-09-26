package com.ff.ui.grid;

import java.util.List;

import com.ff.ui.panel.Panel;
import com.ff.ui.store.Store;

public class Grid extends Panel{

	private Store store;
 
	
	 
	public List<Column> getColumns(){		
		Columns columns = getChildOfClass(Columns.class);		 
		return columns.getChildrenOfClass(Column.class);		
	}
	
	public void addRow(Row row){
		Rows rows = getChildOfClass(Rows.class);
		row.setParent(rows);				 		 
	}
	
	public List<Row> getRows(){
		return getChildOfClass(Rows.class).getChildrenOfClass(Row.class);
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
		store.addGrid(this);
	}

	 
}
