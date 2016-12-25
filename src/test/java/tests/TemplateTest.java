package tests;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Class;
import freemarker.template.Configuration;
import freemarker.template.Template;
import util.HephParser;
import util.HephTemplate;

public class TemplateTest {

	private static String project = "C:\\Users\\agres\\Desktop\\prueba\\";
	private static String rutaCarpeta = "./tests/";

	public static void main(String[] args) {
		
		System.out.println("Prueba de escritura de archivos con Freemarker");
		try {
			Configuration cfg = HephTemplate.initConfig();
			System.out.println("\t --- Leyendo Archivos ---");
			List<Class> classesPrueba = HephParser.parseAllFiles(rutaCarpeta);
			Map<String, Class> root = new HashMap<String, Class>();
			Template temp = cfg.getTemplate("domain.ftlh");

			for (Class clas : classesPrueba) {
				System.out.println("\t--- Escribiendo archivo -" + clas.getName() + "-");
				FileWriter fw = HephTemplate.initFileWriter(project, clas.getName());
				root.put("class", clas);
				temp.process(root, fw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
