package de.htw_berlin.ai_bachelor.kbe.checklist.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.Future;

import de.htw_berlin.ai_bachelor.kbe.dao.GenericDAOFacade;
import de.htw_berlin.ai_bachelor.kbe.dao.ToDoDAO;

@Entity
@Table(name = "todos")//test
public class ToDo implements Serializable, Cloneable {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;  // nur für Datenbank
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private boolean done = false;
	
	@Future(message="Das Faelligkeitsdatum muss in der Zukunft liegen.")
	private Date date;
	
	@MyInterval
	private int priority;
	
	public ToDo(){}
	
	private ToDo(String name, boolean done, Date date) {
		this.name = name;
		this.done = done;
		this.date = date;
		this.priority = 1;
	}
	
	public ToDo(String name, Date date) {
		this(name, false, date);
	}
	
	public ToDo(String name) {
		this(name,false, new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = calendar.getTime();
		setDate(tomorrow);
	}
	
	@Override // Object.clone()
	public ToDo clone() throws CloneNotSupportedException {
		return (ToDo)super.clone();
	}
	
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	

//	public static void main(String []args)
//	  { //compiler weiss welche struktur die obj haben
//		ToDoList list = new ToDoList();
//		list.setToDos(); //zum inizialisieren und 1ten schreiben in die datenbank der todos
//
//		GenericDAOFacade<ToDo> facade = new GenericDAOFacade<ToDo>(new ToDoDAO());
//		facade.saveList(list.getToDos());  
//	  }
}
