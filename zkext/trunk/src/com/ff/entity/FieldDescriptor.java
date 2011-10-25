package com.ff.entity;

import java.util.List;

public class FieldDescriptor {
	private Boolean primaryKey = false;
	private String name;
	private String column;
	private String entity;
	private String entityField;
	private String type;
	private Boolean many = false;
	private RelationDescriptor relationDescriptor;
	private List validators;
	
	
	public Boolean getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(Boolean primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColumn() {		 
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public String getEntityField() {
		return entityField;
	}
	public void setEntityField(String entityField) {
		this.entityField = entityField;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getMany() {
		return many;
	}
	public void setMany(Boolean many) {
		this.many = many;
	}
	public RelationDescriptor getRelationDescriptor() {
		return relationDescriptor;
	}
	public void setRelationDescriptor(RelationDescriptor relationDescriptor) {
		this.relationDescriptor = relationDescriptor;
	}
	public List getValidators() {
		return validators;
	}
	public void setValidators(List validators) {
		this.validators = validators;
	}
	
	
	
}
