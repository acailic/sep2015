package org.sep.merchant.form.dao;

import org.sep.merchant.form.model.Traveler;
import org.sep.merchant.jpa.GenericDaoImpl;
import org.springframework.stereotype.Repository;

@Repository("travelerDao")
public class TravelerDaoImpl extends GenericDaoImpl<Traveler> implements TravelerDao{

}
