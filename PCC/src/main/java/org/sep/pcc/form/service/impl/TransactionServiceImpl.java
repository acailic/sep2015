package org.sep.pcc.form.service.impl;

import javax.inject.Inject;

import org.sep.pcc.form.dao.TransactionDao;
import org.sep.pcc.form.model.Transaction;
import org.sep.pcc.form.service.TransactionService;
import org.sep.pcc.jpa.GenericDao;
import org.sep.pcc.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TransactionServiceImpl extends GenericServiceImpl<Transaction> implements TransactionService{
	
	@Inject
	private TransactionDao transactionDao;

	@Override
	public GenericDao<Transaction> getGenericDao() {
		return transactionDao;
	}

}
