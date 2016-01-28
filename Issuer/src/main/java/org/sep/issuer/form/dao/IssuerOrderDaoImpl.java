package org.sep.issuer.form.dao;

import org.sep.issuer.form.model.IssuerOrder;
import org.sep.issuer.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("issuerOrderDao")
public class IssuerOrderDaoImpl extends GenericDaoImpl<IssuerOrder> implements IssuerOrderDao{

}
