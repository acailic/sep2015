package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.TravelerDao;
import org.sep.merchant.form.model.Traveler;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("travelerService")
@Transactional
public class TravelerServiceImpl extends GenericServiceImpl<Traveler> implements TravelerService{

	@Inject
	private TravelerDao travelerDao;

	@Override
	public GenericDao<Traveler> getGenericDao() {
		return travelerDao;
	}
}
