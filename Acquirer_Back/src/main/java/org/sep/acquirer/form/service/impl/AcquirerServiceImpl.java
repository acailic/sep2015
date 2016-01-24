package org.sep.acquirer.form.service.impl;

import javax.inject.Inject;

import org.sep.acquirer.form.dao.AcquirerDao;
import org.sep.acquirer.form.model.Acquirer;
import org.sep.acquirer.form.service.AcquirerService;
import org.sep.acquirer.jpa.GenericDao;
import org.sep.acquirer.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AcquirerServiceImpl extends GenericServiceImpl<Acquirer> implements AcquirerService{
	
	@Inject
	private AcquirerDao acquirerDao;

	@Override
	public GenericDao<Acquirer> getGenericDao() {
		return acquirerDao;
	}

}
