package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.RiskItemDao;
import org.sep.merchant.form.model.RiskItem;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("riskItemService")
@Transactional
public class RiskItemServiceImpl extends GenericServiceImpl<RiskItem> implements RiskItemService{
	
	@Inject
	private RiskItemDao riskItemDao;

	@Override
	public GenericDao<RiskItem> getGenericDao() {
		return riskItemDao;
	}

}
