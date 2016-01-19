package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.PriceList;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("priceListDao")
public class PriceListDaoImpl extends GenericDaoImpl<PriceList> implements PriceListDao{

}
