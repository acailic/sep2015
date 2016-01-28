package org.sep.pcc.form.dao;

import org.sep.pcc.form.model.Bank;
import org.sep.pcc.jpa.GenericDao;

public interface BankDao extends GenericDao<Bank>{
	
	public Bank findByIin(String iin);
}
