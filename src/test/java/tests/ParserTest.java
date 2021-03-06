package tests;

import java.util.List;

import domain.Class;
import util.HephParser;

public class ParserTest {
	
	
	private static String rutaUnArchivo = "./tests/Consumer.xml";
	private static String rutaCarpeta = "./tests/";
	
	public static void main(String[] args) {
		System.out.println("Prueba de lectura y parseo de archivos XML mediante SAX");
		System.out.println("");
		
		System.out.println("Prueba de lectura de un archivo - Item");
		
		//Leemos el archivo
		Class pruebaUnArchivo = HephParser.parseFile(rutaUnArchivo);
		
		System.out.println("Nombre de la clase leída: " + pruebaUnArchivo.getName());
		System.out.println("Superclass de la clase leída: " + pruebaUnArchivo.getSuperclass());
		System.out.println("Tipo de la clase leída: " + pruebaUnArchivo.getTipoClass());
		
		//Prueba de lectura de carpeta
		System.out.println("Prueba de lectura de una carpeta");
		List<Class> classesPruebaCarpeta = HephParser.parseAllFiles(rutaCarpeta);
		System.out.println("Le�dos " + classesPruebaCarpeta.size() + " archivos:");
		for(int i=0; i<classesPruebaCarpeta.size(); i++) {
			System.out.println("\tArchivo " + i + ": " + classesPruebaCarpeta.get(i).getName());
		}
		
		System.out.println("Fin de la prueba");
	}
}
