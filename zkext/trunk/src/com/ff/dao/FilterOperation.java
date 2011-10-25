package com.ff.dao;

public enum FilterOperation {
	EQUAL("="),
	NOT_EQUAL("<>"),
	GREAT(">"),
	LESS("<"),
	GREAT_EQUAL(">="),
	LESS_EQUAL("<="),
	LIKE("like"),
	BETWEEN(""),
	IN("in");
	
	FilterOperation(String operation){
		this.operation = operation;
	}
	
	private final String operation;
	public String operation(){return operation;}	
	
	@Override
	public String toString(){
		return operation;
	}
}
