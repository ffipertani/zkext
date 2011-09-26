package com.ff.ui.grid;

import java.util.List;

import com.ff.ui.panel.Panel;
import com.ff.ui.store.Store;

public class Grid extends Panel{

	private String store;
	private Store activeStore;
	
	@Override
	public void init() {	
		super.init();
		
		List<Store> stores = getStores();
		for(Store s:stores){
			if(s.getId()!=null && s.getId().equals(store)){
				activeStore = s;
				break;
			}
		}
	}

	
	
	public List<Column> getColumns(){
		Columns columns = getChildOfClass(Columns.class);		 
		return columns.getChildrenOfClass(Column.class);		
	}
	
	public void addRow(Row row){
		Rows rows = getChildOfClass(Rows.class);
		row.setParent(rows);				 		 
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	 

	 
}
