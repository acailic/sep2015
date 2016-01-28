package org.sep.pcc.form.service.impl;

import javax.inject.Inject;

import org.sep.pcc.form.dao.BankDao;
import org.sep.pcc.form.model.Bank;
import org.sep.pcc.form.service.BankService;
import org.sep.pcc.jpa.GenericDao;
import org.sep.pcc.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BankServiceImpl extends GenericServiceImpl<Bank> implements BankService{
	
	@Inject
	private BankDao bankDao;

	@Override
	public GenericDao<Bank> getGenericDao() {
		return bankDao;
	}

	@Override
	public Bank findByIin(String iin) {
		return bankDao.findByIin(iin);
	}

}
