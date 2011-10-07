package com.ff.dao;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class Dao {

	public void loadMap()throws Exception{
		Reader reader = Resources.getResourceAsReader("com/ibatis/example/sqlMapconfig.xml");
		SqlMapClientBuilder.buildSqlMapClient(reader);
	}
}
