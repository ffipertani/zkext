package com.ff.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ff.dao.DaoRequest;
import com.ff.dao.FilterCondition;
import com.ff.dao.FilterOperation;
import com.ff.dao.IDao;
import com.ff.entity.EntityDescriptor;
import com.ff.entity.EntityFactory;
import com.ff.entity.EntityRequest;
import com.ff.entity.FieldDescriptor;
import com.ff.entity.FieldRequest;
import com.ff.model.Model;

@Transactional(
	    propagation = Propagation.REQUIRED,
	    isolation = Isolation.DEFAULT)
public class CrudService extends Service implements ICrudService {
	IDao crudDao;

	@Transactional
	@Override
	public ServiceResponse create(ServiceRequest request)
			throws RuntimeException {
		ServiceResponse response = new ServiceResponse();
		try {
			Model model = request.getModel();
			EntityDescriptor descriptor = EntityFactory.createEntity(request
					.getEntityDescriptor());
			DaoRequest daoRequest = new DaoRequest();
			daoRequest.setEntityDescriptor(descriptor);

			String pk = descriptor.getPrimaryKey();
			if (pk != null) {
				Object pkValue = model.get(pk);
				if (pkValue == null) {
					Object id = crudDao.nextVal(daoRequest);
					model.put(pk, id);
					daoRequest.setModel(model);
				}
			}

			response.setModel(crudDao.insert(daoRequest));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return response;
	}

	@Transactional
	@Override
	public ServiceResponse delete(ServiceRequest request) {
		ServiceResponse response = new ServiceResponse();
		try {
			DaoRequest daoRequest = new DaoRequest();
			daoRequest.setEntityDescriptor(EntityFactory.createEntity(request
					.getEntityDescriptor()));
			daoRequest.setModel(request.getModel());

			response.setModel(crudDao.delete(daoRequest));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return response;
	}

	@Transactional
	@Override
	public ServiceResponse update(ServiceRequest request) {
		ServiceResponse response = new ServiceResponse();
		try {
			DaoRequest daoRequest = new DaoRequest();
			daoRequest.setEntityDescriptor(EntityFactory.createEntity(request
					.getEntityDescriptor()));
			daoRequest.setModel(request.getModel());

			response.setModel(crudDao.update(daoRequest));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return response;
	}

	@Transactional(readOnly=true)
	@Override
	public ServiceResponse read(ServiceRequest request) {
		ServiceResponse response = new ServiceResponse();
		try {
			DaoRequest daoRequest = new DaoRequest();
			EntityDescriptor entityDescriptor = EntityFactory
					.createEntity(request.getEntityDescriptor());
			EntityRequest entityRequest = request.getEntityRequest();
			daoRequest.setEntityDescriptor(entityDescriptor);
			daoRequest.setModel(request.getModel());

			List<Map> fetched = crudDao.search(daoRequest);
			for (Map map : fetched) {
				mergeResult(entityDescriptor,entityRequest,map);
			}

			response.setModel(fetched);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return response;
	}

	private void mergeResult(EntityDescriptor entityDescriptor, EntityRequest entityRequest, Map map) {
		try {
			List<FieldDescriptor> descriptors = (List<FieldDescriptor>) entityDescriptor
					.getEntityFields();
			for (FieldDescriptor fd : descriptors) {
				if (map.get(fd.getName()) == null
						|| "".equals(map.get(fd.getName()))) {
					map.put(fd.getName(), null);
					continue;
				}
				Boolean finded = false;
				List<FieldRequest> fieldRequests = entityRequest.getFields();
				for (FieldRequest fr : fieldRequests) {
					if (fr.getName().equals(fd.getName())) {
						finded = true;
						break;
					}
				}
				if (finded) {
					EntityDescriptor childDescriptor = EntityFactory.createEntity(fd.getEntity());
					DaoRequest childRequest = new DaoRequest();
					childRequest.setEntityDescriptor(childDescriptor);

					String field = null;
					for (FieldDescriptor childField : childDescriptor
							.getFieldDescriptors()) {
						if (childField.getName().equals(fd.getEntityField())) {
							field = childField.getColumn();
							break;
						}
					}

					FilterCondition filter = new FilterCondition();
					filter.setField(field);
					filter.setOperation(FilterOperation.EQUAL);
					filter.setValue((String) map.get(fd.getName()));

					childRequest.setFilterCondition(filter);

					List<Map> childFetched = crudDao.search(childRequest);
					if (fd.getMany()) {
						map.put(fd.getName(), childFetched);
					} else {
						if (childFetched.size() > 0) {
							map.put(fd.getName(), childFetched.get(0));
						} else {
							map.put(fd.getName(), null);
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IDao getCrudDao() {
		return crudDao;
	}

	public void setCrudDao(IDao crudDao) {
		this.crudDao = crudDao;
	}

}
