package com.ff.dao;

import java.util.List;

public class TableInformation {
	private String name;
	private List<KeyInformation> primaryKeys;
	private List<KeyInformation> importedKeys;
	private List<KeyInformation> exportedKeys;
	private List<ColumnInformation> columns;
	
	
	public ColumnInformation getColumnByName(String name){
		for(ColumnInformation col:columns){
			if(col.getName().equalsIgnoreCase(name)){
				return col;
			}
		}
		return null;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<KeyInformation> getPrimaryKeys() {
		return primaryKeys;
	}
	public void setPrimaryKeys(List<KeyInformation> primaryKeys) {
		this.primaryKeys = primaryKeys;
	}	
	public List<ColumnInformation> getColumns() {
		return columns;
	}
	public void setColumns(List<ColumnInformation> columns) {
		this.columns = columns;
	}
	public List<KeyInformation> getImportedKeys() {
		return importedKeys;
	}
	public void setImportedKeys(List<KeyInformation> importedKeys) {
		this.importedKeys = importedKeys;
	}
	public List<KeyInformation> getExportedKeys() {
		return exportedKeys;
	}
	public void setExportedKeys(List<KeyInformation> exportedKeys) {
		this.exportedKeys = exportedKeys;
	}
	
	
}
