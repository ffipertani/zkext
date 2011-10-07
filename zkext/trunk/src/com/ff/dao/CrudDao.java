package com.ff.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.ff.entity.FieldDescriptor;

public class CrudDao implements IDao{	 
	private DataSource dataSource;
	 
	
	private String getPrimaryKey(DaoRequest request){
		List<FieldDescriptor> descriptors = (List)request.getEntityDescriptor().getFieldDescriptors();
		for(FieldDescriptor fd: descriptors){
			if(fd.getPrimaryKey()){
				return fd.getColumn();
			}
		}
		return null;
	}
	
	public List search(DaoRequest request)throws Exception{
		return (List) load(request);
	}
	
	public Object insert(DaoRequest request)throws Exception{	
		String pk = getPrimaryKey(request);
		String sequence = request.getEntityDescriptor().getSequence();
		if(pk!=null){
			Long id = nextVal(sequence);
			request.getModel().put(pk, id);			
		}
		return insert(request.getModel(),request.getEntityDescriptor().getTable());		
	}
	 
	public Object insert(Map model,String table)throws Exception{
		Iterator it = model.keySet().iterator();
		String names = "(";
		String values = "VALUES (";
		Boolean isFirst = true;
		while(it.hasNext()){
			String key = (String)it.next();//(String)keys.i.get(i);
			if(!isFirst){
				names = names + ",";
				values = values + ",";
			}else{
				isFirst = false;
			}
			names = names+key+" ";
			Object value = model.get(key);
			if(value instanceof String){
				values = values+"'"+value+"' ";
			}else{
				values = values+value+" ";	
			}
						
		}
		names = names+")";		 
		values = values+")";
		
		try{
			String query = "insert into " + table + " " + names + " " + values;
			CallableStatement st = dataSource.getConnection().prepareCall(query);			
			st.executeUpdate();
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;	
	}
	
	public Object load(DaoRequest request)throws Exception{
		List toreturn = new ArrayList();
		CallableStatement st = dataSource.getConnection().prepareCall(
				"select * from " + request.getEntityDescriptor().getTable());

		ResultSet res = st.executeQuery();
		List<String> columns = new ArrayList();
		int count = res.getMetaData().getColumnCount();
		for (int i = 1; i <= count; i++) {
			String name = res.getMetaData().getColumnLabel(i);
			columns.add(name);
		}
		
		while (res.next()) {
			Map map = new HashMap();
			for (int i = 0; i < count; i++) {
				Object val = res.getObject(columns.get(i));
				map.put(columns.get(i).toLowerCase(), val!=null?val.toString():"");
			}
			toreturn.add(map);
		}
		st.close();
		return toreturn;
	}
	/*
	
	public Object load(Map model)throws Exception{
		return load(model,table);
	}
	*/
	
	public Object update(DaoRequest request)throws Exception{
 		List toreturn = new ArrayList();
		String sql = "update " + request.getEntityDescriptor().getTable() + " ";
		
		Iterator it = request.getModel().keySet().iterator();
		String set = " SET ";
		String where = " WHERE ";
		Boolean isFirst = true;
		String pk = getPrimaryKey(request);
		while(it.hasNext()){
			String key = (String)it.next();//(String)keys.i.get(i);
			if(key.equals(pk)){								
				where = where + writeValue(request.getEntityDescriptor().getTable(), key, request.getModel());				
				continue;
			}
			if(!isFirst){
				set = set + ",";				 
			}else{
				isFirst = false;
			}
			set = set + writeValue(request.getEntityDescriptor().getTable(), key, request.getModel());			 						
		}
		  
		sql = sql + set + where;
		
		CallableStatement st = dataSource.getConnection().prepareCall(sql);
		st.execute();
		st.close();
		return toreturn;
	}
	
	public Object delete(DaoRequest request)throws Exception{	 
		String pk = getPrimaryKey(request);
		String sql = "delete from " + request.getEntityDescriptor().getTable();					 
		String where = " where "+writeValue(request.getEntityDescriptor().getTable(), pk, request.getModel());				 
		sql = sql+where;
		CallableStatement st = dataSource.getConnection().prepareCall(sql);
		Boolean res = st.execute();
		st.close();
		return res;
	}
	
	public Long nextVal(String sequence)throws Exception{
		String sql = "select "+sequence+".NEXTVAL from DUAL";
		CallableStatement st = dataSource.getConnection().prepareCall(sql);			
		ResultSet rs = st.executeQuery();		
		rs.next();
		Long res = rs.getLong(1);
		st.close();
		return res;
	}
	
	public Object nextVal(DaoRequest request)throws Exception{
		return nextVal(request.getEntityDescriptor().getSequence());
	}
	
	private String writeValue(String table, String field, Map model)throws Exception{
		  
		 CallableStatement st = dataSource.getConnection().prepareCall(
					" select * from "+table+" where rownum < 1 ");

			ResultSet res = st.executeQuery();
			List<String> columns = new ArrayList();
			int count = res.getMetaData().getColumnCount();
			/*
			for (int i = 1; i <= count; i++) {
			
				System.out.println(res.getMetaData().getColumnClassName(i));
				System.out.println(res.getMetaData().getColumnLabel(i));
				System.out.println(res.getMetaData().getColumnType(i));
				System.out.println(res.getMetaData().getColumnTypeName(i));
			  
				
			}
		  */			
		 String className = res.getMetaData().getColumnClassName(getColumnIndex(table,field));
		 st.close();
		 if(className.equals("java.lang.String")){
			 return field+"="+"'"+model.get(field)+"' ";
		 }else{
			 return field+"="+model.get(field)+"";	 
		 }
		 
		 
	}
	
	private int getColumnIndex(String table, String column)throws Exception{
		List toreturn = new ArrayList();
		CallableStatement st = dataSource.getConnection().prepareCall(
				"select * from " + table);

		ResultSet res = st.executeQuery();
		List<String> columns = new ArrayList();
		int count = res.getMetaData().getColumnCount();
		for (int i = 1; i <= count; i++) {
			String name = res.getMetaData().getColumnLabel(i);
			if(name!=null){
				if(name.toLowerCase().equals(column.toLowerCase())){
					st.close();
					return i;
				}
			}			
		}
		st.close();
		return -1;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	
}
