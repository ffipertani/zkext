package com.ff.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

 

/*
 
MAX	"MAX( pk ) + 1" generic algorithm
HIGH-LOW	HIGH-LOW generic algorithm
UUID	UUID generic algorithm
IDENTITY	Supports autoincrement identity fields in Sybase ASE/ASA, MS SQL Server, MySQL and Hypersonic SQL
SEQUENCE	Supports SEQUENCEs in Oracle, PostgreSQL, Interbase and SAP DB
  
 
 <Entity package="it.ff.test.entity" extends="it.ff.test.entity.Entity">
 	<Name>User</Name> 	
 	<ClassName>it.ff.test.model.User</ClassName> //FAC
 	<Table>Users</Table>
 	<KeyGenerator>SEQUENCE</KeyGenerator> MAX|HIGH-LOW|UUID|IDENTITY|SEQUENCE
 	<Sequence>users_seq</Sequence> 		//SOLO SE KEYGEN = SEQUENCE
	<DataSource>${dataSource}</DataSource> 	   	
	<Service>${userService}</Service>	//FAC
	<Dao>it.ff.test.dao.UserDao</Dao>	//FAC
	 
	<select template="com/ff/entity/select.vm"/> //FAC
	<insert template="com/ff/entity/insert.vm"/> //FAC
	<update template="com/ff/entity/update.vm"/> //FAC
	<delete template="com/ff/entity/delete.vm"/> //FAC
		
		
	<Fields inherit="false">
		<Field name="id" primaryKey="true" type="number"/>
		<Field name="firstname" column="firstname" type="text"/>
		<Field name="dateOfBirth" column="birth" type="date"/>
		<Field type="datetime"/>
		<Field type="currency"/>
		<Field type="percentage"/>
		<Field type="binary"/>
		<Field type="longtext"/>
		<Field name="senority" type="number">
			<Validators>
				<NotNull/>
				<MaxValidator max="100"/>
			</Validators>
		</Field>		
		<Field name="roles" type="entity" ref="it.ff.test.entity.Role" entityField="iduser" column="id" many="true">
			//delete cascade della relazione
			<Relation table="USERS_ROLES" column="id_user" relationColumn="id_role"/>
		</Field>
		//delete cascade
		<Field name="address" type="entity" ref="it.ff.test.entity.Address" entityField="iduser" column="id" many="false"/>
	</Fields>
	
	
 
 </Entity>
 
 
 */

public class EntityDescriptor {
	private String extend;
	private String name;
	private String packageName;
	private String className;	
	private String dataSource;
	private String table;
	private String keyGenerator;
	private String sequence;
	private String service;
	private String dao;
	private Boolean inheritFields;
	private Collection<FieldDescriptor> fieldDescriptors;
					
	
	public String getPrimaryKey(){
		List<FieldDescriptor> descriptors = (List)getFieldDescriptors();
		for(FieldDescriptor fd: descriptors){
			if(fd.getPrimaryKey()){
				return fd.getColumn();
			}
		}
		return null;
	}
	
	public List<FieldDescriptor> getEntityFields(){
		List<FieldDescriptor> toReturn = new ArrayList<FieldDescriptor>();
		List<FieldDescriptor> descriptors = (List)getFieldDescriptors();
		for(FieldDescriptor fd: descriptors){
			if(fd.getType().equals("entity")){
				toReturn.add(fd);
			}
		}
		return toReturn;
	}
	
	public List<FieldDescriptor> getSimpleFields(){
		List<FieldDescriptor> toReturn = new ArrayList<FieldDescriptor>();
		List<FieldDescriptor> descriptors = (List)getFieldDescriptors();
		for(FieldDescriptor fd: descriptors){
			if(!fd.getPrimaryKey() && !fd.getType().equals("entity")){
				toReturn.add(fd);
			}
		}
		return toReturn;
	}
	
	public FieldDescriptor getFieldByName(String name){
		List<FieldDescriptor> descriptors = (List)getFieldDescriptors();
		for(FieldDescriptor fd: descriptors){
			if(fd.getName().equals(name)){
				return fd;
			}
		}
		return null;
	}
	
	public String getFullName(){
		return packageName+"."+name;
	}
	
	
	public String getExtend() {
		return extend;
	}


	public void setExtend(String extend) {
		this.extend = extend;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPackageName() {
		return packageName;
	}


	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getDataSource() {
		return dataSource;
	}


	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}


	public String getTable() {
		return table;
	}


	public void setTable(String table) {
		this.table = table;
	}


	public String getKeyGenerator() {
		return keyGenerator;
	}


	public void setKeyGenerator(String keyGenerator) {
		this.keyGenerator = keyGenerator;
	}


	public String getSequence() {
		return sequence;
	}


	public void setSequence(String sequence) {
		this.sequence = sequence;
	}


	public String getService() {
		return service;
	}


	public void setService(String service) {
		this.service = service;
	}


	public String getDao() {
		return dao;
	}


	public void setDao(String dao) {
		this.dao = dao;		
	}


	public Boolean getInheritFields() {
		return inheritFields;
	}


	public void setInheritFields(Boolean inheritFields) {
		this.inheritFields = inheritFields;		
	}


	public Collection<FieldDescriptor> getFieldDescriptors() {
		return fieldDescriptors;
	}


	public void setFieldDescriptors(Collection fieldDescriptors) {
		this.fieldDescriptors = fieldDescriptors;
	}



}
