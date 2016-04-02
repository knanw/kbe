package de.htw_berlin.ai_bachelor.kbe.checklist.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.validation.constraints.Min;

//@ManagedBean(name="priority", eager=true)
@Named("priority")
@ApplicationScoped
@Singleton
public class PriorityIntervalMB implements Serializable {

	private static final long serialVersionUID = 1L;

	
	private final int min = 1;
	@Min(value=2)
	private int max;
    
	public PriorityIntervalMB() {
		this.max = 6;
	}    
	
	
	@PostConstruct
	public void init(){
		this.max = 2;
	}
	

	public PriorityIntervalMB(int max) {
		this.max = max;
	}	

    public String save() {
    	return "save";
    }
    
    public int getMin() {
    	return min;
    }
    
    public int getMax() {
    	return max;
    }
    
    public void setMax(int max) {
    	this.max = max;
    }
    
}
