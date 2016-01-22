package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.PriceListItem;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("priceListItemDao")
public class PriceListItemDaoImpl extends GenericDaoImpl<PriceListItem> implements PriceListItemDao{

}
