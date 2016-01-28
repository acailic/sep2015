package org.sep.issuer.form.dao;

import org.sep.issuer.form.model.Account;
import org.sep.issuer.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl extends GenericDaoImpl<Account> implements AccountDao{

}
