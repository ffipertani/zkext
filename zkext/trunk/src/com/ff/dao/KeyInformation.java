package com.ff.dao;

public class KeyInformation {	
	private String pkTable;
	private String pkColumnName;
	private String pkName;
	
	private String fkTable;
	private String fkColumnName;
	private String fkName;
	
	private Boolean deleteCascade;

	public String getPkTable() {
		return pkTable;
	}

	public void setPkTable(String pkTable) {
		this.pkTable = pkTable;
	}

	public String getPkColumnName() {
		return pkColumnName;
	}

	public void setPkColumnName(String pkColumnName) {
		this.pkColumnName = pkColumnName;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

	public String getFkTable() {
		return fkTable;
	}

	public void setFkTable(String fkTable) {
		this.fkTable = fkTable;
	}

	public String getFkColumnName() {
		return fkColumnName;
	}

	public void setFkColumnName(String fkColumnName) {
		this.fkColumnName = fkColumnName;
	}

	public String getFkName() {
		return fkName;
	}

	public void setFkName(String fkName) {
		this.fkName = fkName;
	}

	public Boolean getDeleteCascade() {
		return deleteCascade;
	}

	public void setDeleteCascade(Boolean deleteCascade) {
		this.deleteCascade = deleteCascade;
	}
	
	
	
}
