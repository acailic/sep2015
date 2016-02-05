package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.OrderDao;
import org.sep.merchant.form.model.Order;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orderService")
@Transactional(readOnly = true)
public class OrderServiceImpl extends GenericServiceImpl<Order> implements OrderService{
	
	@Inject
	private OrderDao orderDao;

	@Override
	public GenericDao<Order> getGenericDao() {
		return orderDao;
	}

}
