package de.htw_berlin.ai_bachelor.kbe.checklist.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.htw_berlin.ai_bachelor.kbe.checklist.model.ToDo;
import de.htw_berlin.ai_bachelor.kbe.checklist.model.ToDoList;
import de.htw_berlin.ai_bachelor.kbe.dao.GenericDAOFacade;
import de.htw_berlin.ai_bachelor.kbe.dao.ToDoDAO;

@Named("list")
@SessionScoped
public class ToDoListMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
    private ToDoList toDoList;
    
	
	@Inject 
    private Conversation conversation;

	
	public ToDoListMB() { //jedesmal bei speichern
		super();
		this.toDoList = new ToDoList();
		//this.toDoList = toDoList.loadIt();
		this.toDoList.loadToDos();//liste aus db laden
		
	}    
	
	
	@PostConstruct
	public void init(){
		this.toDoList = new ToDoList();
		this.toDoList.loadToDos();//liste aus db laden
	}
	

	public ToDoList getToDoList() {
        return toDoList;
    }

	//Should be called if the Button "Speichern" is pushed.
	//Needs configuration in the faces-config.xml.
	
    public String save() {
    	System.out.println("Id::"+ conversation.getId());
    	System.out.println(toDoList.getToDos().get(4));
    	System.out.println(toDoList.getToDos().get(16));

    	// in der Datenbank speichern 
		GenericDAOFacade<ToDo> facade = new GenericDAOFacade<ToDo>(new ToDoDAO());
		facade.saveList(getToDoList().getToDos()); 
		// WARNUNG! Nicht getToDoList() aufrufen!  da sonst frisch von db geladen und änderungen verloren gehen
		//wie textdoku save macht neu laden von platte und speichern.
		
		//liste aus db laden um die datenbanksynchronisation perfektionieren nicht nur schreiben sondern auch lesen synchron
    	this.toDoList.loadToDos();

    	return "save";
    	
    }
    
    public void load(){
    	this.toDoList = new ToDoList();
    	this.toDoList.loadToDos();
    }
    
    /*ï¿¼public void validate(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {
    	
    }*/
}
