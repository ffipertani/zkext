package com.ff.dao;

import java.io.StringWriter;
import java.sql.CallableStatement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ff.cache.Cache;
import com.ff.utils.DbUtils;


public class JDBCTemplateDao implements IDao{
	private JdbcTemplate jdbcTemplate;
	private static Cache<TableInformation> tableCache = new Cache<TableInformation>();
	private static Template insert,delete,update,select;
	
	public JDBCTemplateDao(){
		buildTemplates();
	}
	
	
	private String call(Template template, DaoRequest request){
		buildTableMetaData(request);
		
		StringWriter sw = new StringWriter();
		VelocityContext context = new VelocityContext();
		context.put( "request", request);
		context.put( "pk", request.getEntityDescriptor().getPrimaryKey());
		context.put( "dao", this);
		template.merge( context, sw );
		return sw.toString();
	}
	
	private synchronized void buildTableMetaData(DaoRequest request){
		try{					
			String table = request.getEntityDescriptor().getTable();
			TableInformation ti = tableCache.get(table);
			if(ti==null){
				DatabaseMetaData databaseMetaData  = jdbcTemplate.getDataSource().getConnection().getMetaData();
				ti = DbUtils.buildTable(databaseMetaData,table.toUpperCase());
				
				tableCache.put(table, ti);
				databaseMetaData.getConnection().close();
			}							
		}catch(Exception e){
			e.printStackTrace();		  
		}
	}
	
	public String writeValue(DaoRequest request, String field)throws Exception{	
		 String table = request.getEntityDescriptor().getTable();
		 TableInformation ti = tableCache.get(table);
		
		 String className = ti.getColumnByName(field).getClassName();
		 
		 if(className.equals("java.lang.String")){
			 return "'"+request.getModel().get(field)+"' ";
		 }else{
			 return request.getModel().get(field)+"";	 
		 }		 		 
	}
	
	
	public String writeValuePair(DaoRequest request, String field)throws Exception{
		return field.toUpperCase()+"="+writeValue(request,field);
	}
	
	
	private synchronized void buildTemplates(){
		if(insert==null){		
			VelocityEngine ve = new VelocityEngine();
			
			Properties p = new Properties();						
			ve.setProperty("resource.loader", "class");
			ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
							
			try
			{
				insert = ve.getTemplate("com/ff/dao/velocity/insert.vm");
				delete = ve.getTemplate("com/ff/dao/velocity/delete.vm");
				select = ve.getTemplate("com/ff/dao/velocity/select.vm");
				update = ve.getTemplate("com/ff/dao/velocity/update.vm");
			 
			}catch( Exception e ){
				throw new RuntimeException(e);
			}			
		} 					
	}
	
	
	@Override
	public List search(DaoRequest request) throws Exception {
		String sql = call(select,request);
		//String sql = "select * from " + request.getEntityDescriptor().getTable();			
		return jdbcTemplate.query(sql,new ResultSetExtractor<List>(){

			@Override
			public List extractData(ResultSet res) throws SQLException,
					DataAccessException {
				List toreturn = new ArrayList();
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
				return toreturn;				 
			}			
		});
		 
	}
	
	

	@Override
	public Object load(DaoRequest request) throws Exception {
		String sql = call(select,request);		 
		return jdbcTemplate.query(sql,new ResultSetExtractor<DaoObject>(){

			@Override
			public DaoObject extractData(ResultSet res) throws SQLException,
					DataAccessException {			
				if(res.getFetchSize()>1){
					throw new SQLException("Trovato piu di un oggetto");
				}
				List<String> columns = new ArrayList();
				int count = res.getMetaData().getColumnCount();
				for (int i = 1; i <= count; i++) {
					String name = res.getMetaData().getColumnLabel(i);
					columns.add(name);
				}
				
				DaoObject map = null;
				while (res.next()) {		
					map = new DaoObject();
					for (int i = 0; i < count; i++) {
						Object val = res.getObject(columns.get(i));
						map.put(columns.get(i).toLowerCase(), val!=null?val.toString():"");
					}
					break;
				}
				return map;
			}			
		});		 		 
	}

	 
	@Override
	public Object insert(DaoRequest request) throws Exception {
		String sql = call(insert,request);
		jdbcTemplate.execute(sql);
		return request.getModel();
	}

	@Override
	public Object delete(DaoRequest request) throws Exception {
		String sql = call(delete,request);
		jdbcTemplate.execute(sql);
		return request.getModel();
	}

	@Override
	public Object update(DaoRequest request) throws Exception {
		String sql = call(update,request);
		jdbcTemplate.update(sql);
		return request.getModel();
	}

	@Override
	public Object nextVal(DaoRequest request) throws Exception {
		String sql = "select " + request.getEntityDescriptor().getSequence()+".NEXTVAL from dual";
		return jdbcTemplate.queryForLong(sql);
	}

	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
}
