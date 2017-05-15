package util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import domain.Attribute;
import domain.Class;
import domain.TipoMultiplicity;

public class HephHandler extends DefaultHandler {

	// Atributos para crear clases
	private List<Class> classes;
	private Class hClass;

	private String superclass;
	
	// Atributos para crear atributos
	private List<Attribute> atbs;
	private Attribute atb;

	// Atributos para crear constraints;
	private List<String> constraints;
	private String constraint;

	// Booleanos de control
	boolean bType = false;
	boolean bConstraint = false;
	boolean bMultiplicity = false;
	boolean bOptional = false;
	boolean bSuperclass = false;

	public List<Class> getClasses() {
		return classes;
	}
	
	public Class getOneClass() {
		if(!classes.isEmpty()) {
			hClass = classes.get(0);
		}
		return hClass;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {

		// La etiqueta es una clase
		if (qName.equalsIgnoreCase("Class")) {
			// crear una nueva Clase
			String name = attributes.getValue("name");
			hClass = new Class(name);

			// Inicializar lista de clases
			if (classes == null)
				classes = new ArrayList<Class>();
			
		//la etiqueta es superclass	
		}else if(qName.equalsIgnoreCase("superclass")) {
			bSuperclass = true;
			// la etiqueta es un atributo
		} else if (qName.equalsIgnoreCase("atb")) {
			// Crear un nuevo atributo con los datos dados:
			String atbName = attributes.getValue("name");
			Boolean association = Boolean.valueOf(attributes.getValue("association"));
			atb = new Attribute(atbName, association);

			// Inicializar lista de atributos
			if (atbs == null)
				atbs = new ArrayList<Attribute>();

		} else if (qName.equalsIgnoreCase("type")) {
			bType = true;
		} else if (qName.equalsIgnoreCase("constraints")) {
			// Inicializar lista de constraints
			if (constraints == null)
				constraints = new ArrayList<String>();
		} else if (qName.equalsIgnoreCase("constraint")) {
			bConstraint = true;
		} else if (qName.equalsIgnoreCase("Multiplicity")) {
			bMultiplicity = true;
		} else if (qName.equalsIgnoreCase("Optional")) {
			bOptional = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equalsIgnoreCase("Class")) {
			// A�adir la lista de atributos a la clase
			hClass.setAttributes(atbs);
			hClass.generateImports();
			
			// A�adir clase a la lista de clases
			classes.add(hClass);
			// Limpiar datos
			atb = null;
			atbs = null;
			hClass = null;
			superclass=null;
			constraints=null;
		} else if(qName.equalsIgnoreCase("superclass")) {
			//Set the superclass, if none specified, it is "DomainEntity"
			if(superclass!=null)
				hClass.setSuperclass(superclass);
			else
				hClass.setSuperclass("DomainEntity");
		} else if (qName.equalsIgnoreCase("atb")) {
			// A�adir atributo a la lista de atributos
			atbs.add(atb);
			// Limpiar
			//atb = null;
		} else if (qName.equalsIgnoreCase("constraints")) {
			// A�adir lista de constraints al atributo
			atb.setConstraints(constraints);
			// Limpiar
			constraints = null;
		} else if (qName.equalsIgnoreCase("constraint")) {
			// A�adir constraint a la lista de constraints
			if(!constraints.contains(constraint))
				constraints.add(constraint);
			// Limpiar
			constraint = null;
		}
	}
	
	@Override
    public void characters(char ch[], int start, int length) throws SAXException {

        if (bType) {
            //Elemento type, set type de Atributo
            atb.setType(new String(ch, start, length));
            bType = false;
        } else if(bSuperclass) {
        	superclass = new String(ch,start,length);
        	bSuperclass = false;
        } else if (bConstraint) {
            constraint = new String(ch, start, length);
            if(constraint.contains("Pattern"))
            	//System.out.println("es un pattern");
            constraint = checkConstraint(constraint);
            bConstraint = false;
        } else if (bMultiplicity) {
            atb.setMultiplicity(TipoMultiplicity.valueOf(new String(ch, start, length)));
            bMultiplicity = false;
        } else if (bOptional) {
            atb.setOptional(Boolean.valueOf(new String(ch, start, length)));
            bOptional = false;
        }
    }
	//TODO Cuando se incluye valid? Cuando ElementCollection?

	private String checkConstraint(String constraint) {
		String res;
		if(constraint.equals("Unique")) {
			res = "Column(unique=true)";
		} else if(constraint.toUpperCase().contains("RANGE")) {
			String aux1 = constraint.substring(constraint.indexOf("(")+1,constraint.indexOf(","));
			String aux2 = constraint.substring(constraint.indexOf(",")+1,constraint.indexOf(")"));
			res = "Range(min=" + aux1 + ", max=" + aux2 + ")";
//		} else if(constraint.contains("Pattern")) {
//			res = formatPattern(constraint);
		} else {
			res = constraint;
		}
		
		return res;		
	}
	
	private String formatPattern(String pattern) {
		boolean first = true;
		boolean fin = false;
		String res = "";
		int index = 0;
		int index2 = 0;
		if(first) {
			index = pattern.indexOf("\\");
			res = pattern.substring(0, index) + "\\";
		} else {
			while(!fin) {
				index2 = pattern.indexOf("\\", index+1);
				if(index2 == -1) {
					fin = true;
				} else {
					res+= pattern.substring(index+1, index2) + "\\";
					index = index2;
				}
			}
		}
		return res;
	}
	
	//TODO Superclass y multiplicidad
}
