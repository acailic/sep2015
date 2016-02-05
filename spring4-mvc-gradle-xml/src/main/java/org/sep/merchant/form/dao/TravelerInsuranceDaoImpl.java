package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.TravelerInsurance;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("travelerInsuranceDao")
public class TravelerInsuranceDaoImpl extends GenericDaoImpl<TravelerInsurance> implements TravelerInsuranceDao{

}
