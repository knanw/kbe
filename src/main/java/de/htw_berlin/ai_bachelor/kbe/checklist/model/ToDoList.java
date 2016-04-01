package de.htw_berlin.ai_bachelor.kbe.checklist.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.htw_berlin.ai_bachelor.kbe.dao.ToDoDAO;


public class ToDoList implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private List<ToDo> toDos =  new ArrayList<ToDo>();
		
	public ToDoList() {
		super();
	}
		
	public List<ToDo> getToDos() {
		return toDos;
	}
	
	public int getDoneToDos() {
		int count = 0;
		for (ToDo td : toDos) {
			if (td.isDone())
				count++;
		}
		return count;
	}
	
	public int getAmountOfToDos() {
		return toDos.size();
	}
	
	// lade die Liste aus der Datenbank und füge zur bestehenden Liste hinzu
	public void loadToDos() {
		ToDoDAO dao = new ToDoDAO();
		List<ToDo> list = dao.findAll();
		for (ToDo td : list) {			
			toDos.add(td);
		}
	}
	
	public List<ToDo> loadIt() {
		ToDoDAO dao = new ToDoDAO();
		List<ToDo> list = dao.findAll();
		for (ToDo td : list) {			
			toDos.add(td);
		}
		return toDos;
	}
	
	
//	public void setToDos() {
//	//	toDos.add(new ToDo("KBE: Aufgabenzettel 2 bearbeiten"));
//		//toDos.add(new ToDo("KBE: Zweite Vorlesung nacharbeiten"));
//		toDos.add(new ToDo("Lebensmittel einkaufen "));
//		toDos.add(new ToDo("Leergut wegbringen"));
//		toDos.add(new ToDo("Geburtstagsgeschenk besorgen"));
//		toDos.add(new ToDo("Putzen"));
//	}
}
