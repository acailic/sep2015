package org.sep.merchant.form.service;

import javax.inject.Inject;

import org.sep.merchant.form.dao.PriceListItemDao;
import org.sep.merchant.form.model.PriceListItem;
import org.sep.merchant.jpa.GenericDao;
import org.sep.merchant.jpa.GenericServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("priceListItemService")
@Transactional(readOnly = true)
public class PriceListItemServiceImpl extends GenericServiceImpl<PriceListItem> implements PriceListItemService{

	@Inject
	private PriceListItemDao priceListItemDao;

	@Override
	public GenericDao<PriceListItem> getGenericDao() {
		return priceListItemDao;
	}
}
