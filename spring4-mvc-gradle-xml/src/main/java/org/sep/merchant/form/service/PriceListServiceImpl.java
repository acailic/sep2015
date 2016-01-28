package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.PriceListDao;
import org.sep.merchant.form.model.PriceList;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("priceListService")
@Transactional(readOnly = true)
public class PriceListServiceImpl extends GenericServiceImpl<PriceList> implements PriceListService{
	
	@Inject
	private PriceListDao priceListDao;

	@Override
	public GenericDao<PriceList> getGenericDao() {
		return priceListDao;
	}

}
