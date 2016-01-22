package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.RiskTypeDao;
import org.sep.merchant.form.model.RiskType;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("riskTypeService")
@Transactional(readOnly = true)
public class RiskTypeServiceImpl extends GenericServiceImpl<RiskType> implements RiskTypeService{
	
	@Inject
	private RiskTypeDao riskTypeDao;

	@Override
	public GenericDao<RiskType> getGenericDao() {
		return riskTypeDao;
	}

}
