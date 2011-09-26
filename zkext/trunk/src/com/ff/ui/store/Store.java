package com.ff.ui.store;

import javax.sql.DataSource;

import com.ff.AbstractComponent;

public class Store extends AbstractComponent{
	private DataSource dataSource;
	private String table;
	
	
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
