package org.sep.issuer.form.dao;

import org.sep.issuer.form.model.User;
import org.sep.issuer.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{

}
