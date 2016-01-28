package org.sep.issuer.form.service.impl;

import javax.inject.Inject;

import org.sep.issuer.form.dao.IssuerDao;
import org.sep.issuer.form.model.Issuer;
import org.sep.issuer.form.service.IssuerService;
import org.sep.issuer.jpa.GenericDao;
import org.sep.issuer.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class IssuerServiceImpl extends GenericServiceImpl<Issuer> implements IssuerService{
	
	@Inject
	private IssuerDao issuerDao;

	@Override
	public GenericDao<Issuer> getGenericDao() {
		return issuerDao;
	}

}
