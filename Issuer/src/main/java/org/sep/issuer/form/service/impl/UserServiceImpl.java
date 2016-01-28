package org.sep.issuer.form.service.impl;

import javax.inject.Inject;

import org.sep.issuer.form.dao.UserDao;
import org.sep.issuer.form.model.User;
import org.sep.issuer.form.service.UserService;
import org.sep.issuer.jpa.GenericDao;
import org.sep.issuer.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService{
	
	@Inject
	private UserDao userDao;

	@Override
	public GenericDao<User> getGenericDao() {
		return userDao;
	}

}
