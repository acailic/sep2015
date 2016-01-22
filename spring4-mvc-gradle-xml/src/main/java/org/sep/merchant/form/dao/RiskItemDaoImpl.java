package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.RiskItem;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("riskItemDao")
public class RiskItemDaoImpl extends GenericDaoImpl<RiskItem> implements RiskItemDao{

}
