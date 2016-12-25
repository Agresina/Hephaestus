package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import domain.Class;

public class HephParser {
	
	public static Class parseFile(String ruta) {	
		assert isFile(ruta);
		
		Class hClass = new Class();
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			HephHandler handler = new HephHandler();
			saxParser.parse(new File(ruta), handler);
			// Generar lista de clases
			hClass = handler.getOneClass();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}		
		return hClass;		
	}
	
	public static List<Class> parseAllFiles(String ruta) {
		assert isDirectory(ruta);
		
		//Lista de rutas de archivo
		List<String> archivos = new ArrayList<String>();
		
		//Lista de clases que se generar�n
		List<Class> classList = new ArrayList<Class>();
		
		//Recoger todas las rutas de archivos de la carpeta
		try(Stream<Path> paths = Files.walk(Paths.get(ruta))) {
		    paths.forEach(filePath -> {
		        if (Files.isRegularFile(filePath) && filePath.toString().contains(".xml")) {
		        	//A�adimos la ruta de cada archivo a la lista:
		            archivos.add(filePath.toString());
		        }
		    });
		}catch(IOException e) {
			 e.printStackTrace();
		}
		
		//Recorrer las rutas generando clases
		for(String s: archivos) {
			classList.add(parseFile(s));
		}
		
		return classList;
	}
	
	private static boolean isDirectory(String ruta) {
		File aux = new File(ruta);
		return aux.isDirectory();
	}
	
	private static boolean isFile(String ruta) {
		File aux = new File(ruta);
		return aux.isFile();
	}
	

}
