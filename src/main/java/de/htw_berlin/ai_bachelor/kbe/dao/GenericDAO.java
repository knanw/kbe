package de.htw_berlin.ai_bachelor.kbe.dao;

import java.io.Serializable;
import java.util.*;

import javax.enterprise.inject.Produces;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
 
public abstract class GenericDAO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    //"todos" ist festgelegter name in persistence.xml 
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("todos");
    
    //@Produces
   // @PersistenceContext(unitName=" ")
    //@CustomerDatabase
    
   // => @Inject @CustomerDatabase
    
    private EntityManager em;
 
    private Class<T> entityClass;
    
    
    
/* 	DISPOSER:  
 *  @PersistenceContext
    private EntityManager em;
    
    @Produces
    @UserDatabase
    public Enttiy Manager create(){return em;}
    
    public void close(@Dispose @UserDatabase EntityManager em){em.close();}*/
    
    public GenericDAO(Class<T> entityClass) {
    	em = emf.createEntityManager();
        this.entityClass = entityClass;
    }
 
    public EntityManager getEntityManager() {
    	return em;
    }
    
    public void beginTransaction() {        
        em.getTransaction().begin();
    }
 
    public void commit() {
        em.getTransaction().commit();
    }
 
    public void rollback() {
        em.getTransaction().rollback();
    }
 
    public void closeTransaction() {
        em.close();
    }
 
    public void commitAndCloseTransaction() {
        commit();
        closeTransaction();
    }
 
    public void flush() {
        em.flush();
    }
 
    public void save(T entity) {
        em.persist(entity);
    }
 
    protected void delete(Object id, Class<T> classe) {
        T entityToBeRemoved = em.getReference(classe, id);
 
        em.remove(entityToBeRemoved);
    }
 
    // WARNING throws exception if entity was removed. Else updates/create Managed Entity.
    public T update(T entity) {
        return em.merge(entity);
    }
 
    public T find(int entityID) {
        return em.find(entityClass, entityID);
    }
 
    public T findReferenceOnly(int entityID) {
        return em.getReference(entityClass, entityID);
    }
      
    @SuppressWarnings({ "unchecked"})
    public List<T> findByQuery(String q) {	
        return em.createNamedQuery(q).getResultList();
    }
    
    // Lädt alle Entities des Typs T aus der Datenbank. Die Entities sind MANAGED
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> findAll() {
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return em.createQuery(cq).getResultList();
    }
 
}

