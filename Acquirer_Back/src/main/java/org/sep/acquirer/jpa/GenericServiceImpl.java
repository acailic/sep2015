package org.sep.acquirer.jpa;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public abstract class GenericServiceImpl<E> implements GenericService<E> {
	
	@Override
	@Transactional(readOnly = false)
	public E save(E entity) {
		return getGenericDao().save(entity);
	}
	
	@Override
	@Transactional(readOnly = false)
	public E update(E entity) {
		return getGenericDao().update(entity);
	}  

	@Override
	@Transactional(readOnly = false)
	public void delete(E entity) {
		getGenericDao().delete(entity);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void deleteById(final Long entityId) {
		getGenericDao().deleteById(entityId);
    }
	
	@Override
	@Transactional(readOnly = true)
	public E find(Long id) {
		return getGenericDao().find(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<E> findAll() {
		return getGenericDao().findAll();
	}



}
