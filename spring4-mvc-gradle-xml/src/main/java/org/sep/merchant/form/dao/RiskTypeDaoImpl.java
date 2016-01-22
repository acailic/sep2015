package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.RiskType;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("riskTypeDao")
public class RiskTypeDaoImpl extends GenericDaoImpl<RiskType> implements RiskTypeDao{

}
