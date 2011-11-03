package com.ff.ui.grid;

import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.ff.annotation.Property;
import com.ff.model.Model;
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
	
 
	private int rowCount = 0;
	
	private Store store;
	
	@Property
	private int selectedIndex;
	@Property
	private String storeId;
	@Property
	private Boolean rowEditing;  
	@Property
	private Boolean commit;
	
	@Override
	public void init() {		
		super.init();
		addEventListener(SelectionChangeEvent.NAME, new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				Map data = (Map)event.getData();		
				if(data!=null && data.get("id")!=null){
					Integer id = (Integer)data.get("id");
					selectedIndex = id;					
				}else{
					selectedIndex = -1;
				}
								
			}
		});
	}
	 
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
		rowCount = 0;
		/*
		List<Component> children = rows.getChildren(); 
		for(Component c: children){
			rows.removeChild(c);
		}
		*/
	}
	
	public void replaceAt(int index, Row row){
		removeAt(index);
		insertAt(index,row);
		 
	}
	
	public void insertAt(int index, Model model){
		insertAt(index,createRow(model));
	}
	
	public void insertAt(int index, Row row){
		Rows rows = getChildOfClass(Rows.class);
		Row before = (Row)rows.getChildren().get(index);		
		
		List<Row> allRows = getRows();
		
		for(int i=index;i<allRows.size();i++){
			Row current = allRows.get(i);
			current.setIndex(i+1);
		}
		rows.insertBefore(row, before);
		row.setIndex(index);
		rowCount++;
	}
	
	
	public void removeAt(int index){
		getRows().get(index).setParent(null);
		if(index == getSelectedIndex()){
			setSelectedIndex(-1);
		}
		List<Row> rows = getRows();
		for(int i=index;i<rows.size();i++){
			Row row = rows.get(i);
			row.setIndex(i);
			rowCount--;
		}
	}
	
	public void addRow(Row row){
		Rows rows = getChildOfClass(Rows.class);		
		row.setIndex(rowCount++);
		row.setParent(rows);				 		 
	}
	
	public void addRow(Model model){
		Row row = createRow(model);
		addRow(row);
	}
	
	private Row createRow(Model model){
		Row row = newInstance(Row.class);
		List<Column> columns = getColumns();
		for(Column column:columns){
			String name = column.getName();
			Object val = MapUtils.getValue(model,name);//map.get(key);
			
			Cell cell = newInstance(Cell.class);
			cell.setName(name);
			if(val==null){
				val = "";
			}
			cell.setValue(val.toString());
			row.addCell(cell);
		}
		return row;
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

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	
	 
	 
}
