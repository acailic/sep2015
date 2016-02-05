package org.sep.merchant.jpa;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public abstract class GenericDaoImpl<E> implements GenericDao<E> {

	private Log log;

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<E> type;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<E>) pt.getActualTypeArguments()[0];
		// inicijalizacija sa implementirajucom klasom
		log = LogFactory.getLog(getClass().getSuperclass());
	}

	@Override
	public E save(E entity) {
		log.info("save entity " + entity);
		entityManager.persist(entity);
		log.info("saved entity " + entity);
		return entity;
	}

	@Override
	public E update(E entity) {
		log.info("update entity " + entity);
		return entityManager.merge(entity);
	}

	@Override
	public void delete(E entity) {
		log.info("delete entity " + entity);
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(final Integer entityId) {
		log.info("deleteById entityId " + entityId);
		E entity = find(entityId);
		delete(entity);
	}

	@Override
	public E find(Integer id) {
		log.info("findById " + id);
		return (E) entityManager.find(type, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		log.info("findAll ");
		return (List<E>) entityManager.createQuery("from " + type.getName())
				.getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
