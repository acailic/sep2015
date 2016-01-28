package org.sep.issuer.form.dao;

import org.sep.issuer.form.model.Issuer;
import org.sep.issuer.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("issuerDao")
public class IssuerDaoImpl extends GenericDaoImpl<Issuer> implements IssuerDao{

}
