package com.ff.ui.grid;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ff.annotation.Property;
import com.ff.ui.button.event.DeleteEvent;
import com.ff.ui.button.event.SaveEvent;
import com.ff.ui.button.event.SearchEvent;
import com.ff.ui.grid.event.SelectionChangeEvent;
import com.ff.ui.panel.Panel;
import com.ff.ui.store.Store;
import com.ff.utils.MapUtils;

public class Grid extends Panel{
	static {
		registerEvent(SearchEvent.class, Grid.class,  SearchEvent.NAME, CE_IMPORTANT);		
		registerEvent(SaveEvent.class, Grid.class,  SaveEvent.NAME, CE_IMPORTANT);
		registerEvent(DeleteEvent.class, Grid.class,  DeleteEvent.NAME, CE_IMPORTANT);
		registerEvent(SelectionChangeEvent.class, Grid.class,  SelectionChangeEvent.NAME, CE_IMPORTANT);
	}
	
	private Store store;
	@Property
	private String storeId;
	@Property
	private Boolean rowEditing;  
	@Property
	private Boolean commit;
	 
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
		List<Column> columns = getColumns();
		for(Column column:columns){
			String name = column.getName();
			Object val = MapUtils.getValue(name,map);//map.get(key);
			
			Cell cell = newInstance(Cell.class);
			cell.setName(name);
			if(val==null){
				val = "";
			}
			cell.setValue(val.toString());
			row.addCell(cell);
		}
		/*
		Set<String> keys = (Set<String>)map.keySet();
		for(String key:keys){
			Object val = MapUtils.getValue(key,map);//map.get(key);
			Cell cell = newInstance(Cell.class);
			cell.setName(key);
			cell.setValue(val.toString());
			row.addCell(cell);
		}
		*/
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
		storeId = store.getUuid();
	}

	public Boolean getRowEditing() {
		return rowEditing;
	}

	public void setRowEditing(Boolean rowEditing) {
		this.rowEditing = rowEditing;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Boolean getCommit() {
		return commit;
	}

	public void setCommit(Boolean commit) {
		this.commit = commit;
	}

	
	 
	 
}
