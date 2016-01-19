package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.OwnerDao;
import org.sep.merchant.form.model.Owner;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ownerService")
@Transactional(readOnly = true)
public class OwnerServiceImpl extends GenericServiceImpl<Owner> implements OwnerService{
	
	@Inject
	private OwnerDao ownerDao;

	@Override
	public GenericDao<Owner> getGenericDao() {
		return ownerDao;
	}

}
