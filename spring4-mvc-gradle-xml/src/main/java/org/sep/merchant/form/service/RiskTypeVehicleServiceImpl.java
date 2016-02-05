package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.RiskTypeVehicleDao;
import org.sep.merchant.form.model.RiskTypeVehicle;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("riskTypeVehicleService")
@Transactional
public class RiskTypeVehicleServiceImpl extends GenericServiceImpl<RiskTypeVehicle> implements RiskTypeVehicleService{

	@Inject
	private RiskTypeVehicleDao riskTypeVehicleDao;

	@Override
	public GenericDao<RiskTypeVehicle> getGenericDao() {
		return riskTypeVehicleDao;
	}
}
