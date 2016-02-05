package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.RiskTypeInfo;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("riskTypeInfoDao")
public class RiskTypeInfoDaoImpl extends GenericDaoImpl<RiskTypeInfo> implements RiskTypeInfoDao{

}
