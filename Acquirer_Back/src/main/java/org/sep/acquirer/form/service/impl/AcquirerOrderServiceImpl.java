package org.sep.acquirer.form.service.impl;

import javax.inject.Inject;

import org.sep.acquirer.form.dao.AcquirerOrderDao;
import org.sep.acquirer.form.model.AcquirerOrder;
import org.sep.acquirer.form.service.AcquirerOrderService;
import org.sep.acquirer.jpa.GenericDao;
import org.sep.acquirer.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AcquirerOrderServiceImpl extends GenericServiceImpl<AcquirerOrder> implements AcquirerOrderService{
	
	@Inject
	private AcquirerOrderDao acquirerOrderDao;

	@Override
	public GenericDao<AcquirerOrder> getGenericDao() {
		return acquirerOrderDao;
	}

}
