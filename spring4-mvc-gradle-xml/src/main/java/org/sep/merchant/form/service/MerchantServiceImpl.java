package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.MerchantDao;
import org.sep.merchant.form.model.Merchant;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("merchantService")
@Transactional(readOnly = true)
public class MerchantServiceImpl extends GenericServiceImpl<Merchant> implements MerchantService{

	@Inject
	private MerchantDao merchantDao;

	@Override
	public GenericDao<Merchant> getGenericDao() {
		return merchantDao;
	}
}
