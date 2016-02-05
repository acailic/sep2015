package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.InsuranceOwner;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("insuranceOwnerDao")
public class InsuranceOwnerDaoImpl extends GenericDaoImpl<InsuranceOwner> implements InsuranceOwnerDao{

}
