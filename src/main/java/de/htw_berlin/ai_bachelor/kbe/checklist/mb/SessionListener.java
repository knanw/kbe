package de.htw_berlin.ai_bachelor.kbe.checklist.mb;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
 
public class SessionListener implements HttpSessionListener {
 
    public void sessionCreated(HttpSessionEvent event) {
/*		FacesContext fctx = FacesContext.getCurrentInstance();
		ELResolver resolver = fctx.getELContext().getELResolver();
		
		ToDoListMB list = (ToDoListMB) resolver.getValue( fctx.getELContext(), null, "list");
		list.getToDoList().loadToDos();
  */  
        //System.out.println("Session Created: " + event.getSession().getId());
    }
 
    public void sessionDestroyed(HttpSessionEvent event) {
        //System.out.println("Session Destroyed: " + event.getSession().getId());
    }
}