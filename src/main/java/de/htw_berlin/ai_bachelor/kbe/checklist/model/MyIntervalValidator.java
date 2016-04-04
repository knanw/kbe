package de.htw_berlin.ai_bachelor.kbe.checklist.model;

import javax.el.ELResolver;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.htw_berlin.ai_bachelor.kbe.checklist.mb.PriorityIntervalMB;

public class MyIntervalValidator implements ConstraintValidator<MyInterval, Integer> {

	
	@Inject
	private PriorityIntervalMB priorities;
	
	
	@Override
	public void initialize(MyInterval args) {
		System.out.println("Validator init");
	}
	

	/*public boolean isValid(Integer value, ConstraintValidatorContext context) {
		FacesContext fctx = FacesContext.getCurrentInstance();
		if (fctx == null)
			return true;
		ELResolver resolver = fctx.getELContext().getELResolver();
		PriorityIntervalMB priorities = (PriorityIntervalMB) resolver.getValue( fctx.getELContext(), null, "priority");
		ToDo todo = (ToDo) resolver.getValue(fctx.getELContext(), null, "toDo"); 
		System.out.println(value);
		return (((priorities.getMin() <= value) && (value <= priorities.getMax())) || (todo.getPriority() == value));
	}*/

	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		FacesContext fctx = FacesContext.getCurrentInstance();
		if (fctx == null)
			return true;
		ELResolver resolver = fctx.getELContext().getELResolver();
		
		//priorities nicht mehr über facescontext holen sondern über cdi-injection:::!!!!!!! => toDo ist laufvariable aus html (mittels facescontext auslesen)
		//PriorityIntervalMB prio = (PriorityIntervalMB) resolver.getValue( fctx.getELContext(), null, "priority");
		
		ToDo todo = (ToDo) resolver.getValue(fctx.getELContext(), null, "toDo");
		//System.out.println(value);
		if(priorities != null)
			System.out.println("-- max="+priorities.getMax() + " min=" + priorities.getMin());
		else
			System.out.println("-- priorities->ist null");
		
		return (((priorities.getMin() <= value) && (value <= priorities.getMax())) || (todo.getPriority() == value));
	}
	
}
