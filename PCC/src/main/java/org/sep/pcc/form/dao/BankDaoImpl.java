package org.sep.pcc.form.dao;

import org.apache.commons.logging.Log;
import org.sep.pcc.form.model.Bank;
import org.sep.pcc.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("bankDao")
public class BankDaoImpl extends GenericDaoImpl<Bank> implements BankDao{

	private Log log;
	
	
	@Override
	public Bank findByIin(String iin) {
		log.info("find bank by IIN = " + iin);
		return (Bank) entityManager.createQuery("select bank from bank where bank.iin=" + iin).getSingleResult();
	}
}
