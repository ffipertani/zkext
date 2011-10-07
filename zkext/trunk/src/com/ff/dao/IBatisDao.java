package com.ff.dao;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class IBatisDao extends SqlMapClientDaoSupport implements IDao{
	private static final String STMT_NEXT = "com.ff.dao.IBatisDao.next";
	private static final String STMT_LOAD = "com.ff.dao.IBatisDao.load";
	private static final String STMT_SEARCH= "com.ff.dao.IBatisDao.search";
	private static final String STMT_INSERT = "com.ff.dao.IBatisDao.insert";
	private static final String STMT_UPDATE = "com.ff.dao.IBatisDao.update";
	private static final String STMT_DELETE = "com.ff.dao.IBatisDao.delete";
	 
	 
	public Object load(DaoRequest request)throws Exception{			 
		return getSqlMapClientTemplate().queryForList(STMT_LOAD,request);
	}

	@Override
	public List search(DaoRequest request) throws Exception {
		return getSqlMapClientTemplate().queryForList(STMT_SEARCH,request);
	}

	@Override
	public Object insert(DaoRequest request) throws Exception {
			
		return getSqlMapClientTemplate().insert(STMT_INSERT, request);
	}

	@Override
	public Object delete(DaoRequest request) throws Exception {
		return getSqlMapClientTemplate().delete(STMT_DELETE, request);
	}

	@Override
	public Object update(DaoRequest request) throws Exception {
		return getSqlMapClientTemplate().update(STMT_UPDATE, request);
	}
	
	@Override
	public Object nextVal(DaoRequest request)throws Exception{
		return getSqlMapClientTemplate().queryForObject(STMT_NEXT,request);
	}
}
