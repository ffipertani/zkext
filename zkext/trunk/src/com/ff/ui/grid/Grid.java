package com.ff.ui.grid;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ff.annotation.Property;
import com.ff.ui.grid.event.DeleteEvent;
import com.ff.ui.grid.event.SaveEvent;
import com.ff.ui.grid.event.SearchEvent;
import com.ff.ui.panel.Panel;
import com.ff.ui.store.Store;

public class Grid extends Panel{
	static {
		registerEvent(SearchEvent.class, Grid.class,  SearchEvent.NAME, CE_IMPORTANT);		
		registerEvent(SaveEvent.class, Grid.class,  SaveEvent.NAME, CE_IMPORTANT);
		registerEvent(DeleteEvent.class, Grid.class,  DeleteEvent.NAME, CE_IMPORTANT);
	}
	
	private Store store;
	@Property
	private Boolean rowEditing;  
	 
	public List<Column> getColumns(){		
		Columns columns = getChildOfClass(Columns.class);		 
		return columns.getChildrenOfClass(Column.class);		
	}
	
	public void addColumn(Column column){
		Columns columns = getChildOfClass(Columns.class);		 				
		columns.appendChild(column);
	}
	
	public void clearRows(){
		Rows rows = getChildOfClass(Rows.class);
		removeChild(rows);
		rows = newInstance(Rows.class);
		appendChild(rows);
		
		/*
		List<Component> children = rows.getChildren(); 
		for(Component c: children){
			rows.removeChild(c);
		}
		*/
	}
	
	public void addRow(Row row){
		Rows rows = getChildOfClass(Rows.class);
		row.setParent(rows);				 		 
	}
	
	public void addRow(Map map){
		Row row = newInstance(Row.class);
		Set<String> keys = (Set<String>)map.keySet();
		for(String key:keys){
			Object val = map.get(key);
			Cell cell = newInstance(Cell.class);
			cell.setName(key);
			cell.setValue(val.toString());
			row.addCell(cell);
		}
		addRow(row);
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

	public Boolean getRowEditing() {
		return rowEditing;
	}

	public void setRowEditing(Boolean rowEditing) {
		this.rowEditing = rowEditing;
	}

	
	 
	 
}
