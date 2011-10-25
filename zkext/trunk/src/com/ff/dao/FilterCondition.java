package com.ff.dao;

public class FilterCondition {		
	private String field;
	private FilterOperation operation;
	private String value;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public FilterOperation getOperation() {
		return operation;
	}
	public void setOperation(FilterOperation operation) {
		this.operation = operation;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
