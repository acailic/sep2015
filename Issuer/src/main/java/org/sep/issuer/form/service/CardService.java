package org.sep.issuer.form.service;


import org.sep.issuer.form.model.Card;
import org.sep.issuer.jpa.GenericService;

public interface CardService extends GenericService<Card>{

	public Card findByPan(String pan);
}
