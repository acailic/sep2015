package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.VehicleDao;
import org.sep.merchant.form.model.Vehicle;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("vehicleService")
@Transactional(readOnly = true)
public class VehicleServiceImpl extends GenericServiceImpl<Vehicle> implements VehicleService{
	
	@Inject
	private VehicleDao vehicleDao;

	@Override
	public GenericDao<Vehicle> getGenericDao() {
		return vehicleDao;
	}

}
