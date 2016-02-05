package org.sep.merchant.jpa;
import java.util.List;


public interface GenericDao<E> {

	public E save(E entity);

	public E update(E entity);

	public void delete(E entity);

	public E find(Integer id);

	public void deleteById(final Integer entityId);

	public List<E> findAll();

}
