package org.sep.acquirer.form.service.impl;

import javax.inject.Inject;

import org.sep.acquirer.form.dao.PaymentDao;
import org.sep.acquirer.form.model.Payment;
import org.sep.acquirer.form.service.PaymentService;
import org.sep.acquirer.jpa.GenericDao;
import org.sep.acquirer.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PaymentServiceImpl extends GenericServiceImpl<Payment> implements PaymentService{
	
	@Inject
	private PaymentDao paymentDao;

	@Override
	public GenericDao<Payment> getGenericDao() {
		return paymentDao;
	}

}
