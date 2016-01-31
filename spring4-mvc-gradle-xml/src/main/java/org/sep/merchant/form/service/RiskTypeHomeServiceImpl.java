package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.RiskTypeHomeDao;
import org.sep.merchant.form.model.RiskTypeHome;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("riskTypeHomeService")
@Transactional
public class RiskTypeHomeServiceImpl extends GenericServiceImpl<RiskTypeHome> implements RiskTypeHomeService{

	@Inject
	private RiskTypeHomeDao riskTypeHomeDao;

	@Override
	public GenericDao<RiskTypeHome> getGenericDao() {
		return riskTypeHomeDao;
	}
}
