package com.ff.utils;

import java.sql.CallableStatement;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ff.dao.ColumnInformation;
import com.ff.dao.KeyInformation;
import com.ff.dao.TableInformation;

public class DbUtils {
	
	public static TableInformation buildTables(DatabaseMetaData dbm){
		try{
		 List<String> tables = getListOfAllTables(dbm);
		 for(int i=0;i<tables.size();i++){
			 getPrimaryKeys(dbm,tables.get(i));
			 getIndexInfo(dbm,tables.get(i));
			 getImportedKeys(dbm,tables.get(i));
			 getExportedKeys(dbm,tables.get(i));
			 getColumns(dbm,tables.get(i));
		 }
		}catch(Exception e){
			
		}
		return new TableInformation();
	}
	
	public static TableInformation buildTable(DatabaseMetaData dbm, String table){
		try{		 
			TableInformation ti = new TableInformation();
			ti.setName(table);
			ti.setPrimaryKeys(getPrimaryKeys(dbm,table));
			//getIndexInfo(dbm,table);
			ti.setImportedKeys(getImportedKeys(dbm,table));
			ti.setExportedKeys(getExportedKeys(dbm,table));
			ti.setColumns(getColumns(dbm,table));
			
			return ti;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private static void printResultSet(String title,ResultSet res){
		try{			 
			System.out.println(title);
			int count = res.getMetaData().getColumnCount();
			while(res.next()){			 
				for (int i = 1; i <= count; i++) {
					System.out.println(res.getMetaData().getColumnLabel(i));								
					System.out.println(res.getObject(i));
					System.out.println("--------");
				}
				System.out.println("");			
			}
			System.out.println("");
			 			
			res.first();
		}catch(Exception e){}		
	}
	
	private static List<KeyInformation> getPrimaryKeys(DatabaseMetaData dbm, String table)throws Exception{
		List<KeyInformation> keys = new ArrayList();
		
		ResultSet res = dbm.getPrimaryKeys(dbm.getConnection().getCatalog(), null, table);
								
		//printResultSet("PRIMARY KEYS", res);
		
		while(res.next()){			
			KeyInformation key = new KeyInformation();
			key.setPkTable(res.getString("TABLE_NAME"));
			key.setPkColumnName(res.getString("COLUMN_NAME"));
			key.setPkName(res.getString("PK_NAME"));
			
			keys.add(key);
		}		 	
		res.close();
		
		return keys;
	}
	
	private static void getIndexInfo(DatabaseMetaData dbm, String table)throws Exception{
		ResultSet res = dbm.getIndexInfo(dbm.getConnection().getCatalog(), null, table, true, true);

		//printResultSet("INDEX INFO", res);
		 		
		res.close();
	}
	
	private static List<KeyInformation> getImportedKeys(DatabaseMetaData dbm, String table)throws Exception{
		List<KeyInformation> keys = new ArrayList();
		
		ResultSet res = dbm.getImportedKeys(dbm.getConnection().getCatalog(), null, table);
		
		//printResultSet("IMPORTED KEYS",res);
		
		while(res.next()){			
			KeyInformation key = new KeyInformation();
			key.setPkTable(res.getString("PKTABLE_NAME"));
			key.setPkColumnName(res.getString("PKCOLUMN_NAME"));
			key.setPkName(res.getString("PK_NAME"));
			
			key.setFkTable(res.getString("FKTABLE_NAME"));
			key.setFkColumnName(res.getString("FKCOLUMN_NAME"));
			key.setFkName(res.getString("FKTABLE_NAME"));
			
			keys.add(key);
		}		 
		res.close();
		
		return keys;
	}
	
	private static List<KeyInformation> getExportedKeys(DatabaseMetaData dbm, String table)throws Exception{
		List<KeyInformation> keys = new ArrayList();		
		ResultSet res = dbm.getExportedKeys(dbm.getConnection().getCatalog(), null, table);
				
		//printResultSet("EXPORTED KEYS", res);	
		
		while(res.next()){					
			KeyInformation key = new KeyInformation();
			key.setPkTable(res.getString("PKTABLE_NAME"));
			key.setPkColumnName(res.getString("PKCOLUMN_NAME"));
			key.setPkName(res.getString("PK_NAME"));
			
			key.setFkTable(res.getString("FKTABLE_NAME"));
			key.setFkColumnName(res.getString("FKCOLUMN_NAME"));
			key.setFkName(res.getString("FKTABLE_NAME"));
			
			keys.add(key);
		}		
		res.close();
		
		return keys;
	}
	
	private static List<ColumnInformation> getColumns(DatabaseMetaData dbm, String table)throws Exception{
		List<ColumnInformation> columns = new ArrayList();	
		
		ResultSet res = dbm.getColumns(dbm.getConnection().getCatalog(), null, table, null);
		
		CallableStatement st = dbm.getConnection().prepareCall(
				" select * from "+table+" where rownum < 1 ");
	    ResultSet nres = st.executeQuery();		
	    	    
	  //  printResultSet("COLUMNS", res);	
	    		 
		int index = 1;
		while(res.next()){						
			ColumnInformation ci = new ColumnInformation();
			ci.setClassName(nres.getMetaData().getColumnClassName(index));			
			ci.setName(res.getString("COLUMN_NAME"));
			ci.setNullable(res.getBoolean("NULLABLE"));
			ci.setType(res.getInt("DATA_TYPE"));
			ci.setTypeName(res.getString("TYPE_NAME"));
						
			columns.add(ci);	
			index++;
		}
		 
		res.close();
		nres.close();
				 		   	
		return columns;
	}
	
	private static List getListOfAllTables(DatabaseMetaData dbm) 
	  throws SQLException {
		List listOfTables = new ArrayList();
	    /*
		String[] tableTypes = {
	    	"TABLE",
	        "VIEW",
	        "ALIAS",
	        "SYNONYM",
	        "GLOBAL TEMPORARY",
	        "LOCAL TEMPORARY",
	        "SYSTEM TABLE"};
	        */
		String[] tableTypes = {"TABLE"};
	    ResultSet rs = dbm.getTables(dbm.getConnection().getCatalog(), null, "%", tableTypes);

	    while (rs.next()) {
	      String tableName = rs.getString(3);
	      listOfTables.add(tableName);
	    }
	    rs.close();
	    return listOfTables;
	  }
}
