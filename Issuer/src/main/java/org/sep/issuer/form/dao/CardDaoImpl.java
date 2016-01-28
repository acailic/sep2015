package org.sep.issuer.form.dao;

import org.apache.commons.logging.Log;
import org.sep.issuer.form.model.Card;
import org.sep.issuer.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("cardDao")
public class CardDaoImpl extends GenericDaoImpl<Card> implements CardDao{

	private Log log;
	
	@Override
	public Card findByPan(String pan) {
		log.info("find Card by PAN = " + pan);
		return (Card) entityManager.createQuery("select card from card where card.pan=" + pan).getSingleResult();
	}

}
