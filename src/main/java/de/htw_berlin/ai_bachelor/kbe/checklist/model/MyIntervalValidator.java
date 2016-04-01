package de.htw_berlin.ai_bachelor.kbe.checklist.model;

import javax.el.ELResolver;
import javax.faces.context.FacesContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.htw_berlin.ai_bachelor.kbe.checklist.mb.PriorityIntervalMB;

public class MyIntervalValidator implements ConstraintValidator<MyInterval, Integer> {

	public void initialize(MyInterval args) {		
	}

	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		FacesContext fctx = FacesContext.getCurrentInstance();
		if (fctx == null)
			return true;
		ELResolver resolver = fctx.getELContext().getELResolver();
		PriorityIntervalMB priorities = (PriorityIntervalMB) resolver.getValue( fctx.getELContext(), null, "priority");
		ToDo todo = (ToDo) resolver.getValue(fctx.getELContext(), null, "toDo"); 
		System.out.println(value);
		return (((priorities.getMin() <= value) && (value <= priorities.getMax())) || (todo.getPriority() == value));
	}

}
