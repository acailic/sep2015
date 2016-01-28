package org.sep.issuer.form.service.impl;

import javax.inject.Inject;

import org.sep.issuer.form.dao.IssuerOrderDao;
import org.sep.issuer.form.model.IssuerOrder;
import org.sep.issuer.form.service.IssuerOrderService;
import org.sep.issuer.jpa.GenericDao;
import org.sep.issuer.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class IssuerOrderServiceImpl extends GenericServiceImpl<IssuerOrder> implements IssuerOrderService{
	
	@Inject
	private IssuerOrderDao issuerOrderDao;

	@Override
	public GenericDao<IssuerOrder> getGenericDao() {
		return issuerOrderDao;
	}

}
