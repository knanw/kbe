package de.htw_berlin.ai_bachelor.kbe.checklist.mb;

import java.io.Serializable;

import javax.el.ELResolver;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import de.htw_berlin.ai_bachelor.kbe.checklist.model.ToDo;
import de.htw_berlin.ai_bachelor.kbe.dao.ToDoDAO;
import interceptor2.Secure;

//@ManagedBean(name="editToDo", eager=true)

@Named("editToDo")
@SessionScoped
public class EditToDoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ToDo toDo;
		
	public EditToDoMB() {
		toDo = new ToDo("");
	}    

    public ToDo getToDo() {
    	return toDo;
    }
    
   @Secure("#{editToDo.name == editToDo.name}") 
	public String save() throws CloneNotSupportedException {
    	// Holt die Liste ToDoListMB list aus der HTML-Schicht als Java-Objekt
		FacesContext fctx = FacesContext.getCurrentInstance();
		ELResolver resolver = fctx.getELContext().getELResolver();
		ToDoDAO dao = new ToDoDAO();
		ToDo toDoCopy = toDo.clone();
		dao.save(toDoCopy);//todo zum dao hinzufügen dass es managed ist und es bekommt eine id
		ToDoListMB list = (ToDoListMB) resolver.getValue( fctx.getELContext(), null, "list");
		list.getToDoList().getToDos().add(toDoCopy);
    	return "save";
    }   
    
}
