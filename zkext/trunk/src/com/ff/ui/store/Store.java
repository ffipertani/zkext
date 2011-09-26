package com.ff.ui.store;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.ff.AbstractComponent;
import com.ff.ui.form.Form;
import com.ff.ui.grid.Cell;
import com.ff.ui.grid.Grid;
import com.ff.ui.grid.Row;

public class Store extends AbstractComponent{
	private DataSource dataSource;
	private String table;
	private List<Grid> grids = new ArrayList();
	private List<Form> forms = new ArrayList();
	
			
	public void init() {
		Grid grid = grids.get(0);
		try{
			CallableStatement st = dataSource.getConnection().prepareCall("select * from "+ table);			
			ResultSet res = st.executeQuery();
			List<String> columns = new ArrayList();
			int count = res.getMetaData().getColumnCount();

			for(int i=1;i<=count;i++){
				String name = res.getMetaData().getColumnLabel(i);
				columns.add(name);
			}
			
			while(res.next()) {
			
				Row row = newInstance(Row.class);
				for(int i=0;i<count;i++){
					Cell cell = newInstance(Cell.class);
					Object val = res.getObject(columns.get(i));
					cell.setValue(val.toString());
					cell.setName(columns.get(i).toLowerCase());
					row.addCell(cell);
				}
				grid.addRow(row);				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addGrid(Grid grid){		
		grids.add(grid);
	}
	
	public void addForm(Form form){
		forms.add(form);
	}
	
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
	
}
