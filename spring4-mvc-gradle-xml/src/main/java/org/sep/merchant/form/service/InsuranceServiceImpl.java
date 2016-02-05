package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.InsuranceDao;
import org.sep.merchant.form.model.Insurance;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("insuranceService")
@Transactional(readOnly = true)
public class InsuranceServiceImpl extends GenericServiceImpl<Insurance> implements InsuranceService {
	
	@Inject
	private InsuranceDao insuranceDao;

	@Override
	public GenericDao<Insurance> getGenericDao() {
		return insuranceDao;
	}


}
