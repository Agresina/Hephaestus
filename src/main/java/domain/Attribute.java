package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Attribute {
	private String name;
	private String type;
	private boolean association;
	private TipoMultiplicity multiplicity;
	private boolean optional;
	private List<String> constraints;
	
	public Attribute() {
		super();
		this.constraints = new ArrayList<String>();
		this.association = false;
	}
	
	public Attribute(String name, boolean association, TipoMultiplicity multiplicity,
			boolean optional, Collection<String> constraints) {
		this.name =name;
		this.association = association;
		this.multiplicity = multiplicity;
		this.optional = optional;
		
		this.constraints = new ArrayList<String>();
		this.constraints.addAll(constraints);		
	}
	
	public Attribute(String name, boolean association) {
		this.name = name;
		this.association = association;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isAssociation() {
		return association;
	}
	public void setAssociation(boolean association) {
		this.association = association;
	}
	public TipoMultiplicity getMultiplicity() {
		return multiplicity;
	}
	public void setMultiplicity(TipoMultiplicity multiplicity) {
		this.multiplicity = multiplicity;
	}
	public boolean isOptional() {
		return optional;
	}
	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public List<String> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<String> constraints) {
		this.constraints = constraints;
	}
	

}
