package com.ff.dao;

import java.util.List;

public interface IDao {
	
	public List search(DaoRequest request)throws Exception;
	public Object load(DaoRequest request)throws Exception;
	public Object insert(DaoRequest request)throws Exception;
	public Object delete(DaoRequest request)throws Exception;
	public Object update(DaoRequest request)throws Exception;
	public Object nextVal(DaoRequest request)throws Exception;
}
