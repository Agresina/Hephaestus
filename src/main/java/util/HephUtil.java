package util;

import java.io.FileWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Class;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HephUtil {

	// Numero de instancias de cada entidad en el populate
	private static final int NUM_POPULATE = 3;

	// Constantes que almacenen las rutas de las distintas partes del proyecto
	private static final String DOMAIN = "\\src\\main\\java\\domain\\";
	private static final String REPOSITORY = "\\src\\main\\java\\repositories\\";
	private static final String SERVICE = "\\src\\main\\java\\services\\";
	private static final String CONVERTER = "\\src\\main\\java\\converters\\";
	private static final String CONTROLLER = "\\src\\main\\java\\controller\\";

	// Archivos de configuracion
	private static final String CONFIG = "\\src\\main\\resources\\spring\\config\\";
	
	private static final String POPULATE = "\\src\\main\\resources\\";

	private static final String VIEWS = "\\src\\main\\webapp\\views\\";

	// TODO Añadir booleano para ver si la clase que se esta haciendo se ha
	// generado correctamente
	public static void genera(String proyectFolder, Collection<domain.Class> classes) {
		generateDomain(proyectFolder, classes);
		generateRepository(proyectFolder, classes);
		generateService(proyectFolder, classes);
		generateConverter(proyectFolder, classes);
		generateController(proyectFolder, classes);
		generateConfig(proyectFolder, classes);
		generateViews(proyectFolder, classes);
	}

	public static void genera(String proyectFolder, String rutaClases) {
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

	public static void generateViews(String proyectFolder, Collection<domain.Class> classes) {
		for (domain.Class clas : classes) {
			generateFile(proyectFolder, VIEWS, "message.properties", clas, "messagesEN.ftlh");
			generateFile(proyectFolder, VIEWS, "message_es.properties", clas, "messagesES.ftlh");
			generateFile(proyectFolder, VIEWS, "list.jsp", clas, "listView.ftlh");
			generateFile(proyectFolder, VIEWS, "edit.jsp", clas, "editView.ftlh");
			generateFile(proyectFolder, VIEWS, "tiles.xml", clas, "tilesEN.ftlh");
			generateFile(proyectFolder, VIEWS, "tiles_es.xml", clas, "tilesES.ftlh");
		}
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

			for (String a : actors.keySet()) {
				root.put(a, actors.get(a));
			}

			temp.process(root, fw);
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

}
