package org.sep.acquirer.form.dao;

import org.sep.acquirer.form.model.Acquirer;
import org.sep.acquirer.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("acquirerDao")
public class AcquirerDaoImpl extends GenericDaoImpl<Acquirer> implements AcquirerDao{

}
