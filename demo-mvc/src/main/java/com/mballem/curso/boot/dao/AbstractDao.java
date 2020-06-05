package com.mballem.curso.boot.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractDao< T, K extends Serializable> {
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	private final Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];	
	
	public AbstractDao() {
		// TODO Auto-generated constructor stub
	}
	
	protected EntityManager getEntityManager() {
		return em;
	}

	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, timeout = 30)
	public T findById(K id)  throws Exception {
		T obj = em.find( clazz, id);
		
		return obj;
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS, timeout = 30)
    public List<T> findAll(){
    	Query objects = em.createQuery("select object(o) from " + clazz.getSimpleName() + " as o order by o.id");
    	
		return objects.getResultList();
    }

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
	public T insert(T obj)  throws Exception {
		try {
			em.persist(obj);
			
			em.flush();
		} catch (Exception e) {
			throw new Exception(  clazz.getName() + "DAO.insert: ERRO ( " + e.getMessage() + " )" );
		}
		
    	return obj;
	}

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
    public T update(T obj) throws Exception {
		T objResult = null;
		
		try {
			objResult = em.merge(obj);
			
			em.flush();
		} catch (Exception e) {
			throw new Exception(  clazz.getName() + "DAO.insert: ERRO ( " + e.getMessage() + " )" );
		}
		
    	return objResult;
    }

	@Transactional(propagation=Propagation.REQUIRED,readOnly=false) 
    public boolean delete(K id) throws Exception {
		try {
			T objResult = findById(id);

			em.remove(objResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(  clazz.getName() + "DAO.insert: ERRO ( " + e.getMessage() + " )" );
		}

		return true;
    }

	protected List<T> createQuery(String jpql, Object...params){
		TypedQuery<T> query = em.createQuery(jpql, clazz );
		
		for ( int i = 0; i < params.length; i++) {
			query.setParameter( i + 1, params[i]); 
		}
		
		return query.getResultList();
	}

	protected List<T> createQuery(String jpql){
		TypedQuery<T> query = em.createQuery(jpql, clazz );
		
		return query.getResultList();
	}

}
