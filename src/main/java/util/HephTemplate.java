package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class HephTemplate {

	public static Configuration initConfig() {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
		try {
			cfg.setDirectoryForTemplateLoading(new File("./templates"));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			cfg.setLogTemplateExceptions(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cfg;
	}

	//TODO: No crea la carpeta suministrada, si no existe da fallo
	//TODO: Multiplicidad de atributos no relaciones -> EN servicio incluirlo
	//TODO: Cargar todos los xml en uno solo y de ahí cargar los actores para introducirlos en Authority(dentro de security)
	//TODO: Tener en cuenta multiplicidad en las vistas
	//TODO: configuración. Con el xml que incluye todas las clases (probablemente solo nombre y si es actor) cargar con una plantilla todas
	//TODO: XML completo: cargar todas las clases y escribirlas con una plantilla
	public static FileWriter initFileWriter(String project, String nombreClase) {
		FileWriter fw = null;
		File f = null;
		try {
			f = new File(project);
			f.mkdirs();
			fw = new FileWriter(project + nombreClase);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fw;
	}

}
