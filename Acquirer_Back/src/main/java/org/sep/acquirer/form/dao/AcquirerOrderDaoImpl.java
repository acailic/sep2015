package org.sep.acquirer.form.dao;

import org.sep.acquirer.form.model.AcquirerOrder;
import org.sep.acquirer.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("acquirerOrderDao")
public class AcquirerOrderDaoImpl extends GenericDaoImpl<AcquirerOrder> implements AcquirerOrderDao{

}
