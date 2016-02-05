package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.Home;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("homeDao")
public class HomeDaoImpl extends GenericDaoImpl<Home> implements HomeDao{

}
