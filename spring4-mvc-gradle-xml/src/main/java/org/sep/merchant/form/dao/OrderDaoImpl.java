package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.Order;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("orderDao")
public class OrderDaoImpl extends GenericDaoImpl<Order> implements OrderDao{
	
	

}
