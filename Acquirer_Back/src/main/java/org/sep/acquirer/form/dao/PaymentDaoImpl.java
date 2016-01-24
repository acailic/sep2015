package org.sep.acquirer.form.dao;

import org.sep.acquirer.form.model.Payment;
import org.sep.acquirer.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("paymentDao")
public class PaymentDaoImpl extends GenericDaoImpl<Payment> implements PaymentDao{

}
