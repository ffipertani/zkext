package com.ff.ui.store;

import java.util.ArrayList;
import java.util.List;

import com.ff.AbstractComponent;
import com.ff.ui.button.event.DeleteEvent;
import com.ff.ui.button.event.SaveEvent;
import com.ff.ui.button.event.SearchEvent;
import com.ff.ui.form.Form;
import com.ff.ui.grid.Grid;
import com.ff.ui.grid.event.SelectionChangeEvent;

public class Store extends AbstractComponent{
	
	static {
		registerEvent(SearchEvent.class, Store.class,  SearchEvent.NAME, CE_IMPORTANT);		
		registerEvent(SaveEvent.class, Store.class,  SaveEvent.NAME, CE_IMPORTANT);
		registerEvent(DeleteEvent.class, Store.class,  DeleteEvent.NAME, CE_IMPORTANT);
		registerEvent(SelectionChangeEvent.class, Store.class,  SelectionChangeEvent.NAME, CE_IMPORTANT);
	}
	
	private List<Grid> grids = new ArrayList();
	private List<Form> forms = new ArrayList();
	
			
	
	public void addGrid(Grid grid){		
		grids.add(grid);		
	}
	
	public void addForm(Form form){
		forms.add(form);
	}
		
	public List<Grid> getGrids(){
		return grids;
	}
	
	public List<Form> getForms(){
		return forms;
	}
	
}
