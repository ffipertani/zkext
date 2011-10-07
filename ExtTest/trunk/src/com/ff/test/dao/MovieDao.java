package com.ff.test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ff.test.domain.MovieSource;


public class MovieDao extends SqlMapClientDaoSupport {
	 
    
    public MovieDao() {
    }

   
    public List<MovieSource> loadAll() { 
    	SqlMapClientTemplate tmp;    
        return (List<MovieSource>) getSqlMapClientTemplate().queryForList(getNS("loadAll"));
    }     
    
    public List<MovieSource> search(List filters, int from, int to) {        
    	Map map = new HashMap();
    	map.put("filters", filters);
    	map.put("from", from);
    	map.put("to",to);
        return (List<MovieSource>) getSqlMapClientTemplate().queryForList(getNS("search"),map);
    }     
    
    public MovieSource load(Long id) {        
        return (MovieSource) getSqlMapClientTemplate().queryForObject(getNS("load"),id);
    } 
    
    public Integer count(List filters) {   
    	Map map = new HashMap();
    	map.put("filters", filters);
        return (Integer) getSqlMapClientTemplate().queryForObject(getNS("count"),map);
    } 
    
    public void insert(MovieSource movie){
    		getSqlMapClientTemplate().insert(getNS("insert"),movie);
    }
    
    private String getNS(String text){
    	return getClass().getName()+"."+text;
    }
     
}
