package com.ff.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudService extends Service implements ICrudService {

	@Override
	public ServiceResponse create(ServiceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse delete(ServiceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse update(ServiceRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse read(ServiceRequest request) {
		ServiceResponse response = new ServiceResponse();
		try {
			List toreturn = new ArrayList();
			CallableStatement st = dataSource.getConnection().prepareCall(
					"select * from " + request.getTable());

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
					map.put(columns.get(i).toLowerCase(), val.toString());
				}
				toreturn.add(map);
			}
			
			response.setModel(toreturn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
