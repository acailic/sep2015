package org.sep.issuer.form.service.impl;

import javax.inject.Inject;

import org.sep.issuer.form.dao.CardDao;
import org.sep.issuer.form.model.Card;
import org.sep.issuer.form.service.CardService;
import org.sep.issuer.jpa.GenericDao;
import org.sep.issuer.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CardServiceImpl extends GenericServiceImpl<Card> implements CardService{
	
	@Inject
	private CardDao cardDao;

	@Override
	public GenericDao<Card> getGenericDao() {
		return cardDao;
	}

	@Override
	public Card findByPan(String pan) {
		return cardDao.findByPan(pan);
	}
  
}
