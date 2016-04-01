package de.htw_berlin.ai_bachelor.kbe.dao;

import java.util.List;
 
public class GenericDAOFacade<T>  {
	private GenericDAO<T> dao;
	
    public GenericDAOFacade(GenericDAO<T> dao) {
    	this.dao = dao;
    }
 
    public void save(T entity) {
    	dao.beginTransaction();
        dao.update(entity);
        dao.commitAndCloseTransaction();
    }
 
    public void saveList(List<T> list) {
    	dao.beginTransaction();
	    for (T t : list) {  // kein save sondern update aufrufen für liste die aus db geladen wurde !kein save sondern update , um probleme mit detach zu vermeiden, detach-elemente unterstützen
	    	dao.update(t);  //intern java-reflection: zur laufzeit in java-obj reinschauen , verbindung mit db
	    }
	    dao.commitAndCloseTransaction();
    }

    //
	public void delete(Object id, Class<T> classe) {
        dao.delete(id, classe);
    	dao.beginTransaction();   	
        dao.commitAndCloseTransaction();
    }
	
	// 
    public T update(T entity) {
    	T ent = dao.update(entity);
    	dao.beginTransaction();
        dao.commitAndCloseTransaction();
        return ent;
    }
  
}

