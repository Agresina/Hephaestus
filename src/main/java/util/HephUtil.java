package util;

import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Attribute;
import domain.Class;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HephUtil {

	// Numero de instancias de cada entidad en el populate
	private static final int NUM_POPULATE = 3;

	// Constantes que almacenen las rutas de las distintas partes del proyecto
	private static final String DOMAIN = "//src//main//java//domain//";
	private static final String REPOSITORY = "//src//main//java//repositories//";
	private static final String SERVICE = "//src//main//java//services//";
	private static final String CONVERTER = "//src//main//java//converters//";
	private static final String CONTROLLER = "//src//main//java//controllers//";
	private static final String MASTERPAGE = "//src//main//webapp//views//master-page//";

	// Archivos de configuracion
	private static final String CONFIG = "//src//main//resources//spring//config//";
	
	private static final String POPULATE = "//src//main//resources//";

	private static final String VIEWS = "//src//main//webapp//views//";

	// TODO Añadir booleano para ver si la clase que se esta haciendo se ha
	// generado correctamente
	public static void genera(String proyectFolder, Collection<domain.Class> classes) throws Exception {
		generateDomain(proyectFolder, classes);
		generateRepository(proyectFolder, classes);
		generateService(proyectFolder, classes);
		generateConverter(proyectFolder, classes);
		generateController(proyectFolder, classes);
		generateConfig(proyectFolder, classes);
		generateViews(proyectFolder, classes);
		generatePopulate(proyectFolder, classes);
		generateHeader(proyectFolder, classes);
	}

	public static void genera(String proyectFolder, String rutaClases) throws Exception {
		List<domain.Class> classes = HephParser.parseAllFiles(rutaClases);
		genera(proyectFolder, classes);
	}

	// Genera las clases de dominio dada una carpeta de proyecto y un listado de
	// clases
	public static void generateDomain(String proyectFolder, Collection<domain.Class> classes) {
		for (domain.Class clas : classes) {
			generateFile(proyectFolder, DOMAIN, clas.getName() + ".java", clas, "domain.ftlh");
		}
	}

	public static void generateRepository(String proyectFolder, Collection<domain.Class> classes) {
		for (domain.Class clas : classes) {
			generateFile(proyectFolder, REPOSITORY, clas.getName() + "Repository.java", clas, "repository.ftlh");
		}
	}

	public static void generateService(String proyectFolder, Collection<domain.Class> classes) {
		for (domain.Class clas : classes) {
			generateFile(proyectFolder, SERVICE, clas.getName() + "Service.java", clas, "service.ftlh");
		}
	}

	public static void generateConverter(String proyectFolder, Collection<domain.Class> classes) {
		for (domain.Class clas : classes) {
			generateFile(proyectFolder, CONVERTER, clas.getName() + "ToStringConverter.java", clas,
					"converterToString.ftlh");
			generateFile(proyectFolder, CONVERTER, "StringTo" + clas.getName() + "Converter.java", clas,
					"converterToClass.ftlh");
		}
	}

	public static void generateController(String proyectFolder, Collection<domain.Class> classes) {
		for (domain.Class clas : classes) {
			generateFile(proyectFolder, CONTROLLER, clas.getName() + "Controller.java", clas, "controller.ftlh");
		}
	}

	public static void generateConfig(String proyectFolder, Collection<domain.Class> classes) {
		generateFile(proyectFolder, CONFIG, "converters.xml", classes, "converters.ftlh");
		generateFile(proyectFolder, CONFIG, "i18n-l10n.xml", classes, "messages.ftlh");
		generateFile(proyectFolder, CONFIG, "security.xml", classes, "security.ftlh");
		generateFile(proyectFolder, CONFIG, "tiles.xml", classes, "tiles.ftlh");
	}

	//Primer método en usar el generateFile nuevo, que recibe un Map
	public static void generateViews(String proyectFolder, Collection<domain.Class> classes) throws Exception {
		//Construimos el map, en el que introduciremos ademas de la clase que estamos procesando los mensajes
		Map<String, Object> elements = new HashMap<String, Object>();
		Map<String, Object> mensajesES = new HashMap<String, Object>();
		Map<String, Object> mensajesEN = new HashMap<String, Object>();
		
		Translator translator = new Translator("en", "es");
		
		for (domain.Class clas : classes) {
			//Introducimos la clase
			elements.put("class", clas);
			
			//Construimos un map para los mensajes, la clave es el nombre de atributo, el valor el mensaje construido. Uno para ES y otro para EN
			String messEs = "";
			String messEn = "";
			for(Attribute att: clas.getAttributes()) {
				String[] mess = att.getName().split("(?=\\p{Upper})");
				
				StringBuilder strBuilder = new StringBuilder();
				for (int i = 0; i < mess.length; i++) {
				   strBuilder.append(" ").append(mess[i]);
				}
				messEn = strBuilder.toString();
				
				messEs = translator.translate(messEn);
				
				System.out.println(messEn);
				
				mensajesEN.put(att.getName(), messEn);
				mensajesES.put(att.getName(), messEs);
			}
			
			elements.put("messagesEN", mensajesEN);
			elements.put("messagesES", mensajesES);
			
			generateFile(proyectFolder, VIEWS + firstToLower(clas.getName()) + "//", "messages.properties", "messagesEN.ftlh", elements);
			generateFile(proyectFolder, VIEWS + firstToLower(clas.getName()) + "//", "messages_es.properties", "messagesES.ftlh", elements);
			generateFile(proyectFolder, VIEWS + firstToLower(clas.getName()) + "//", "list.jsp", clas, "listView.ftlh");
			generateFile(proyectFolder, VIEWS + firstToLower(clas.getName()) + "//", "edit.jsp", clas, "editView.ftlh");
			generateFile(proyectFolder, VIEWS + firstToLower(clas.getName()) + "//", "tiles.xml", clas, "tilesEN.ftlh");
			generateFile(proyectFolder, VIEWS + firstToLower(clas.getName()) + "//", "tiles_es.xml", clas, "tilesES.ftlh");
		}
	}
	
	public static void generateHeader(String proyectFolder, Collection<domain.Class> classes) {
		generateFile(proyectFolder, MASTERPAGE, "header.jsp", classes, "header.ftlh");
		generateFile(proyectFolder, MASTERPAGE, "messages.properties", classes, "headerMessages.ftlh");
		generateFile(proyectFolder, MASTERPAGE, "messages_es.properties", classes, "headerMessagesES.ftlh");
	}
	
	public static void generatePopulate(String proyectFolder, Collection<domain.Class> classes) {
		generateFilePop(proyectFolder, POPULATE, "PopulateDatabase.xml", classes, "populate.ftlh");
	}
	/**
	 * Genera un archivo en la ruta de proyecto proyectFolder dentro de
	 * destFolder, con el nombre nombreClase y la información contenida en clas
	 * con la plantilla template (Localizada dentro de templates)
	 **/
	public static void generateFile(String proyectFolder, String destFolder, String nombreClase, domain.Class clas,
			String template) {
		try {
			String ruta = proyectFolder + destFolder;
			Map<String, Object> root = new HashMap<String, Object>();

			Configuration cfg = HephTemplate.initConfig();
			Template temp = cfg.getTemplate(template);
			FileWriter fw = HephTemplate.initFileWriter(ruta, nombreClase);

			root.put("class", clas);

			temp.process(root, fw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void generateFile(String proyectFolder, String destFolder, String nombreClase,
			Collection<Class> classes, String template) {
		try {
			String ruta = proyectFolder + destFolder;
			Map<String, Object> root = new HashMap<String, Object>();

			Configuration cfg = HephTemplate.initConfig();
			Template temp = cfg.getTemplate(template);
			FileWriter fw = HephTemplate.initFileWriter(ruta, nombreClase);

			root.put("classes", classes);

			temp.process(root, fw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//Usado en generar populate, recibe además de los atributos usuales, un map con sus cuentas de usuario
	public static void generateFilePop(String proyectFolder, String destFolder, String nombreClase,
			Collection<Class> classes, String template) {
		try {
			String ruta = proyectFolder + destFolder;
			Map<String, Object> root = new HashMap<String, Object>();

			Configuration cfg = HephTemplate.initConfig();
			Template temp = cfg.getTemplate(template);
			FileWriter fw = HephTemplate.initFileWriter(ruta, nombreClase);
			
			Map<String, String> actors = loadAccounts(classes);
			
			root.put("classes", classes);
			root.put("NUM_POPULATE", NUM_POPULATE);
			root.put("ACTOR", loadActorClass(classes));

			for (String a : actors.keySet()) {
				root.put(a, actors.get(a));
			}

			temp.process(root, fw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*Metodo al que habrá que migrar el resto, ya que con este podemos añadir desde el principio todos los objectos que queramos, tal y 
	*como se hace con las vistas en spring
	*/
	public static void generateFile(String proyectFolder, String destFolder, String nombreFichero, String template, Map<String, Object> elements) {
		try {
			String ruta = proyectFolder + destFolder;

			Configuration cfg = HephTemplate.initConfig();
			Template temp = cfg.getTemplate(template);
			FileWriter fw = HephTemplate.initFileWriter(ruta, nombreFichero);

			temp.process(elements, fw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Transform a text into hash using MD5 encoding
	public static String hashText(String text) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(text.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1, digest);
		String hashtext = bigInt.toString(16);
		// Now we need to zero pad it if you actually want the full 32
		// chars.
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}
	
	public static Class loadActorClass(Collection<Class> classes) {
		Class res = null;
		for(Class clas: classes) {
			if(clas.getName().equalsIgnoreCase("actor")) {
				res = clas;
				break;
			}
		}
		return res;
	}

	// Returns a list of the actors of the classes
	public static List<Class> loadActors(Collection<Class> classes) {
		List<Class> actors = new ArrayList<Class>();

		// Recorremos todas las clases buscando actores
		for (Class clas : classes) {
			if (clas.getSuperclass().equalsIgnoreCase("Actor")) {
				actors.add(clas);
			}
		}

		return actors;
	}

	// Returns a map with NUM_POPULATE accounts of every actor (with user and
	// password)
	public static Map<String, String> loadAccounts(Collection<Class> classes) throws NoSuchAlgorithmException {
		Map<String, String> actors = new HashMap<String, String>();

		// Recorremos todas las clases buscando actores
		for (Class clas : classes) {
			if (clas.getSuperclass().equalsIgnoreCase("Actor")) {
				// Tantas veces como hayamos especificado generamos una cuenta
				// de usuario (Nombre y cont.)
				for (int i = 1; i <= NUM_POPULATE; i++) {
					// Generamos el nombre que sera el actor en minuscula y el
					// nº correspondiente
					String nombre = clas.getName().toLowerCase() + String.valueOf(i);
					// La contra. sera el nombre pasado a hash
					String contra = hashText(nombre);

					// Por ultimo lo introducimos en el map
					actors.put(nombre, contra);
				}
			}
		}

		return actors;
	}
	
	private static String firstToLower(String s) {
		String res = s.substring(0,1).toLowerCase() + s.substring(1);
		return res;
	}

}
