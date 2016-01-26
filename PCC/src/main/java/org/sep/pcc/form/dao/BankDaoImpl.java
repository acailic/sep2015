package org.sep.pcc.form.dao;

import org.sep.pcc.form.model.Bank;
import org.sep.pcc.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("bankDao")
public class BankDaoImpl extends GenericDaoImpl<Bank> implements BankDao{

}
