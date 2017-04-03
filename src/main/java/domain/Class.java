package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Class {

	private String superclass;
	private String name;
	private TipoClass tipoClass;
	private List<Attribute> attributes;
	private List<String> imports;

	public Class() {
		super();
		this.attributes = new ArrayList<Attribute>();
		this.imports = new ArrayList<String>();
	}

	public Class(String name) {
		this.name = name;
		this.attributes = new ArrayList<Attribute>();
		this.imports = new ArrayList<String>();
		this.superclass = "DomainEntity";
		this.tipoClass = TipoClass.Entity;
	}

	public Class(String name, Collection<Attribute> attributes) {
		this.name = name;
		this.attributes = new ArrayList<Attribute>();
		this.imports = new ArrayList<String>();
		this.superclass = "DomainEntity";
		this.tipoClass = TipoClass.Entity;

		attributes.addAll(attributes);

	}

	public Class(String superclass, String name, Collection<Attribute> attributes) {
		this.superclass = superclass;
		this.name = name;
		this.attributes = new ArrayList<Attribute>();
		this.imports = new ArrayList<String>();

		attributes.addAll(attributes);
		if(superclass.equalsIgnoreCase("Actor")) {
			this.tipoClass = TipoClass.Actor;
		} else {
			this.tipoClass = TipoClass.Entity;
		}

	}

	public String getSuperclass() {
		return superclass;
	}

	public void setSuperclass(String superclass) {
		this.superclass = superclass;
		if(superclass.equalsIgnoreCase("Actor")) {
			this.tipoClass = TipoClass.Actor;
		} else {
			this.tipoClass = TipoClass.Entity;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		if (attributes != null) {
			this.attributes = attributes;
		}
	}

	public List<String> getImports() {
		return imports;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}

	public boolean hasSuperclass() {
		return (superclass != null) ? true : false;
	}

	public void generateImports() {
		List<String> res = new ArrayList<String>();
		String cad;
		if (attributes != null) {
			for (Attribute atb : attributes) {
				cad = checkImport(atb.getType());
				if (!cad.equals("") && !this.imports.contains(cad))
					res.add(cad);
				cad = checkImport(String.valueOf(atb.getMultiplicity()));
				if (!cad.equals("") && !this.imports.contains(cad)) {
					res.add(cad);
					if(!this.imports.contains("javax.validation.Valid")){
						res.add("javax.validation.Valid");
					}
				}
				if (atb.getConstraints() != null) {
					for (String cons : atb.getConstraints()) {
						if (cons != null) {
							cad = checkImport(cons);
							if (!cad.equals("") && !res.contains(cad))
								res.add(cad);
						}
					}
				}
			}
		}

		setImports(res);
	}
	
	public TipoClass getTipoClass() {
		return tipoClass;
	}

	public void setTipoClass(TipoClass tipoClass) {
		this.tipoClass = tipoClass;
	}

	private String checkImport(String cad) {
		String res = "";

		if (cad.contains("Collection")) {
			res = "java.util.Collection";
		} else if (cad.contains("List")) {
			res = "java.util.List";
		} else if (cad.equals("NotBlank")) {
			res = "org.hibernate.validator.constraints.NotBlank";
		} else if (cad.equals("NotNull")) {
			res = "javax.validation.constraints.NotNull";
		} else if (cad.contains("Valid")) {
			res = "javax.validation.Valid";
		} else if (cad.equals("Email")) {
			res = "org.hibernate.validator.constraints.Email";
		} else if (cad.equalsIgnoreCase("URL")) {
			res = "org.hibernate.validator.constraints.URL";
		} else if (cad.equals("CreditCardNumber")) {
			res = "org.hibernate.validator.constraints.CreditCardNumber";
		} else if (cad.equals("Past")) {
			res = "javax.validation.constraints.Past";
		} else if (cad.equals("Future")) {
			res = "javax.validation.constraints.Future";
		} else if (cad.contains("Column")) {
			res = "javax.persistence.Column";
		} else if (cad.contains("Size")) {
			res = "javax.validation.constraints.Size";
		} else if (cad.contains("Min")) {
			res = "javax.validation.constraints.Min";
		} else if (cad.contains("Max")) {
			res = "javax.validation.constraints.Max";
		} else if (cad.contains("Range")) {
			res = "org.hibernate.validator.constraints.Range";
		} else if (cad.contains("Digits")) {
			res = "javax.validation.constraints.Digits";
		} else if (cad.contains("ManyToOne")) {
			res = "javax.persistence.ManyToOne";
		} else if (cad.contains("OneToMany")) {
			res = "javax.persistence.OneToMany";
		} else if (cad.contains("OneToOne")) {
			res = "javax.persistence.OneToOne";
		}
		

		return res;
	}

}
