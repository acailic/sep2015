package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.HomeDao;
import org.sep.merchant.form.model.Home;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("homeService")
@Transactional(readOnly = true)
public class HomeServiceImpl extends GenericServiceImpl<Home> implements HomeService{
	
	@Inject
	private HomeDao homeDao;

	@Override
	public GenericDao<Home> getGenericDao() {
		return homeDao;
	}

}
