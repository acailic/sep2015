package org.sep.acquirer.jpa;
import java.util.List;


public interface GenericDao<E> {

	public E save(E entity) throws Exception;

	public E update(E entity) throws Exception;

	public void delete(E entity);

	public E find(Integer id);

	public void deleteById(final Integer entityId);

	public List<E> findAll();

}
