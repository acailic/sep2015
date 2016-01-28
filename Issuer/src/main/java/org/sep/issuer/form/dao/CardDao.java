package org.sep.issuer.form.dao;

import org.sep.issuer.form.model.Card;
import org.sep.issuer.jpa.GenericDao;

public interface CardDao extends GenericDao<Card>{

	public Card findByPan(String pan);
}
