package org.sep.pcc.form.dao;

import org.sep.pcc.form.model.Transaction;
import org.sep.pcc.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("transactionDao")
public class TransactionDaoImpl extends GenericDaoImpl<Transaction> implements TransactionDao{

}
