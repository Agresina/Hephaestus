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

	public static FileWriter initFileWriter(String project, String nombreClase) {
		FileWriter fw = null;
		File f = null;
		try {
			f = new File(project);
			f.getParentFile().mkdirs();
			fw = new FileWriter(project + nombreClase + ".java");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fw;
	}

}
