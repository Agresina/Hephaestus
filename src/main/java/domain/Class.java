package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Class {

	private String superclass;
	private String name;
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
	}

	public Class(String name, Collection<Attribute> attributes) {
		this.name = name;
		this.attributes = new ArrayList<Attribute>();
		this.imports = new ArrayList<String>();
		this.superclass = "DomainEntity";

		attributes.addAll(attributes);

	}

	public Class(String superclass, String name, Collection<Attribute> attributes) {
		this.superclass = superclass;
		this.name = name;
		this.attributes = new ArrayList<Attribute>();
		this.imports = new ArrayList<String>();

		attributes.addAll(attributes);

	}

	public String getSuperclass() {
		return superclass;
	}

	public void setSuperclass(String superclass) {
		this.superclass = superclass;
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

	private String checkImport(String cad) {
		String res = "";

		if (cad.contains("Collection")) {
			res = "java.util.Collection";
		} else if (cad.contains("List")) {
			res = "java.util.List";
		} else if (cad.equals("NotBlank")) {
			res = "import javax.validation.constraints.NotBlank;";
		} else if (cad.equals("NotNull")) {
			res = "import javax.validation.constraints.NotNull";
		} else if (cad.equals("Valid")) {
			res = "import javax.validation.Valid";
		} else if (cad.equals("Email")) {
			res = "import javax.validation.constraints.Email";
		} else if (cad.equalsIgnoreCase("URL")) {
			res = "import javax.validation.constraints.URL";
		} else if (cad.equals("CreditCardNumber")) {
			res = "import javax.validation.constraints.CreditCardNumber";
		} else if (cad.equals("Past")) {
			res = "import javax.validation.constraints.Past";
		} else if (cad.equals("Future")) {
			res = "import javax.validation.constraints.Future";
		} else if (cad.contains("Column")) {
			res = "import javax.persistence.Column";
		} else if (cad.contains("Size")) {
			res = "import javax.validation.constraints.Size";
		} else if (cad.contains("Min")) {
			res = "import javax.validation.constraints.Min";
		} else if (cad.contains("Max")) {
			res = "import javax.validation.constraints.Max";
		} else if (cad.contains("Range")) {
			res = "import javax.validation.constraints.Range";
		}

		return res;
	}

}
