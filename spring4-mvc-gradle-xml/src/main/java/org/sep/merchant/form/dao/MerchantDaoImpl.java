package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.Merchant;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;
;

@Repository("merchantDao")
public class MerchantDaoImpl extends GenericDaoImpl<Merchant> implements MerchantDao{

}
