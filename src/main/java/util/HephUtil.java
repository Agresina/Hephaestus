package util;

import java.io.FileWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Class;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class HephUtil {

	// Constantes que almacenen las rutas de las distintas partes del proyecto
	private static final String DOMAIN = "\\src\\main\\java\\domain\\";
	private static final String REPOSITORY = "\\src\\main\\java\\repositories\\";
	private static final String SERVICE = "\\src\\main\\java\\services\\";
	private static final String CONVERTER = "\\src\\main\\java\\converters\\";
	private static final String CONTROLLER = "\\src\\main\\java\\controller\\";
	
	//TODO Añadir booleano para ver si la clase que se esta haciendo se ha generado correctamente
	public static void genera(String proyectFolder, Collection<domain.Class> classes) {
		generateDomain(proyectFolder, classes);
		generateRepository(proyectFolder, classes);
		generateService(proyectFolder, classes);
		generateConverter(proyectFolder, classes);
		generateController(proyectFolder, classes);
	}
	
	public static  void genera(String proyectFolder, String rutaClases) {
		List<domain.Class> classes = HephParser.parseAllFiles(rutaClases);
		genera(proyectFolder, classes);
	}
	
	//Genera las clases de dominio dada una carpeta de proyecto y un listado de clases
	public static void generateDomain(String proyectFolder, Collection<domain.Class> classes) {
		for(domain.Class clas: classes) {
			generateFile(proyectFolder, DOMAIN, clas.getName()+".java", clas, "domain.ftlh");
		}
	}

	public static void generateRepository(String proyectFolder, Collection<domain.Class> classes) {
		for(domain.Class clas: classes) {
			generateFile(proyectFolder, REPOSITORY, clas.getName()+"Repository.java", clas, "repository.ftlh");
		}
	}
	
	public static void generateService(String proyectFolder, Collection<domain.Class> classes) {
		for(domain.Class clas: classes) {
			generateFile(proyectFolder, SERVICE, clas.getName()+"Service.java", clas, "service.ftlh");
		}
	}
	
	public static void generateConverter(String proyectFolder, Collection<domain.Class> classes) {
		for(domain.Class clas: classes) {
			generateFile(proyectFolder, CONVERTER, clas.getName()+"toStringConverter.java", clas, "converterToString.ftlh");
			generateFile(proyectFolder, CONVERTER, "StringTo"+clas.getName()+"Converter.java", clas, "converterToClass.ftlh");
		}
	}
	
	public static void generateController(String proyectFolder, Collection<domain.Class> classes) {
		for(domain.Class clas: classes) {
			generateFile(proyectFolder, CONTROLLER, clas.getName()+"Controller.java", clas, "controller.ftlh");
		}
	}

	//TODO: Ver si se puede usar un map de object, para añadir distintos elementos
	/**
	 * Genera un archivo en la ruta de proyecto proyectFolder dentro de
	 * destFolder, con el nombre nombreClase y la información contenida en clas
	 * con la plantilla template (Localizada dentro de templates)
	 **/
	public static void generateFile(String proyectFolder, String destFolder, String nombreClase, domain.Class clas,
			String template) {
		try {
			String ruta = proyectFolder + destFolder;
			Map<String, Class> root = new HashMap<String, Class>();

			Configuration cfg = HephTemplate.initConfig();
			Template temp = cfg.getTemplate(template);
			FileWriter fw = HephTemplate.initFileWriter(ruta, nombreClase);

			root.put("class", clas);

			temp.process(root, fw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
