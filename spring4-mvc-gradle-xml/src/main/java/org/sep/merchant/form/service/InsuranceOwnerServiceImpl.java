package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.InsuranceOwnerDao;
import org.sep.merchant.form.model.InsuranceOwner;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("insuranceOwnerService")
@Transactional(readOnly = true)
public class InsuranceOwnerServiceImpl extends GenericServiceImpl<InsuranceOwner> implements InsuranceOwnerService{

	@Inject
	private InsuranceOwnerDao insuranceOwnerDao;

	@Override
	public GenericDao<InsuranceOwner> getGenericDao() {
		return insuranceOwnerDao;
	}
}
