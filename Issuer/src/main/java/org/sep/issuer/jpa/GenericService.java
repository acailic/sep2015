package org.sep.issuer.jpa;

import java.util.List;

public interface GenericService<E> {

	public E save(E entity);

	public E update(E entity);

	public void delete(E entity);

	public E find(Long id);

	public void deleteById(final Long entityId);

	public List<E> findAll();

	public GenericDao<E> getGenericDao();

}
