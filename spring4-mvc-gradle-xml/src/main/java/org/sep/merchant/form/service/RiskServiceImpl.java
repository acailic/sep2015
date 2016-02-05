package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.RiskDao;
import org.sep.merchant.form.model.Risk;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("riskService")
@Transactional(readOnly = true)
public class RiskServiceImpl extends GenericServiceImpl<Risk> implements RiskService{
	
	@Inject
	private RiskDao riskDao;

	@Override
	public GenericDao<Risk> getGenericDao() {
		return riskDao;
	}

}
