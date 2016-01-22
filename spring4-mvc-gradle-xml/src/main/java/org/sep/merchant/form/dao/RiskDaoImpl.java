package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.Risk;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("riskDao")
public class RiskDaoImpl extends GenericDaoImpl<Risk> implements RiskDao{

}
