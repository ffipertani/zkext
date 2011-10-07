package com.ff.ui.store;

import java.util.ArrayList;
import java.util.List;

import com.ff.AbstractComponent;
import com.ff.ui.form.Form;
import com.ff.ui.grid.Grid;

public class Store extends AbstractComponent{
	
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
	
}
