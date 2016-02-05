package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.TravelerInsuranceDao;
import org.sep.merchant.form.model.TravelerInsurance;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("travelerInsuranceService")
@Transactional
public class TravelerInsuranceServiceImpl extends GenericServiceImpl<TravelerInsurance> implements TravelerInsuranceService{
	
	@Inject
	private TravelerInsuranceDao travelerInsuranceDao;

	@Override
	public GenericDao<TravelerInsurance> getGenericDao() {
		return travelerInsuranceDao;
	}

}
