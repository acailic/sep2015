package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.RiskItemInsuranceDao;
import org.sep.merchant.form.model.RiskItemInsurance;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("riskItemInsuranceService")
@Transactional
public class RiskItemInsuranceServiceImpl extends GenericServiceImpl<RiskItemInsurance> implements RiskItemInsuranceService{

	@Inject
	private RiskItemInsuranceDao riskItemInsuranceDao;

	@Override
	public GenericDao<RiskItemInsurance> getGenericDao() {
		return riskItemInsuranceDao;
	}
}
