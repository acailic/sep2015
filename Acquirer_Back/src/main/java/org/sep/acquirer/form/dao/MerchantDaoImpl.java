package org.sep.acquirer.form.dao;

import org.sep.acquirer.form.model.Merchant;
import org.sep.acquirer.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("merchantDao")
public class MerchantDaoImpl extends GenericDaoImpl<Merchant> implements MerchantDao{

}
