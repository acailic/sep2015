package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.Owner;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("ownerDao")
public class OwnerDaoImpl  extends GenericDaoImpl<Owner> implements OwnerDao{

}
