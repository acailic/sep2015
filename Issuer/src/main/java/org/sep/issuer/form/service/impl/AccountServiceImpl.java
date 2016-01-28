package org.sep.issuer.form.service.impl;

import javax.inject.Inject;

import org.sep.issuer.form.dao.AccountDao;
import org.sep.issuer.form.model.Account;
import org.sep.issuer.form.service.AccountService;
import org.sep.issuer.jpa.GenericDao;
import org.sep.issuer.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AccountServiceImpl extends GenericServiceImpl<Account> implements AccountService{
	
	@Inject
	private AccountDao accountDao;

	@Override
	public GenericDao<Account> getGenericDao() {
		return accountDao;
	}

}
