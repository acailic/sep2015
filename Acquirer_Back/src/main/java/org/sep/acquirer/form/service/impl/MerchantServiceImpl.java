package org.sep.acquirer.form.service.impl;

import javax.inject.Inject;

import org.sep.acquirer.form.dao.MerchantDao;
import org.sep.acquirer.form.model.Merchant;
import org.sep.acquirer.form.service.MerchantService;
import org.sep.acquirer.jpa.GenericDao;
import org.sep.acquirer.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MerchantServiceImpl extends GenericServiceImpl<Merchant> implements MerchantService{
	
	@Inject
	private MerchantDao merchantDao;

	@Override
	public GenericDao<Merchant> getGenericDao() {
		return merchantDao;
	}

}
