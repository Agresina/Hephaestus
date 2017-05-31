package tests;

import java.util.List;

import domain.Class;
import util.HephParser;
import util.HephUtil;

public class UtilTest {
	
	
	private static String rutaSalida = "./Output";
	private static String rutaCarpeta = "./tests/";
	
	public static void main(String[] args) throws Exception {
		System.out.println("Prueba de escritura de Populate");
		System.out.println("");
		
		List<domain.Class> classes = HephParser.parseAllFiles(rutaCarpeta);
		
		System.out.println("Clases de actor: (Si no sale ninguna algo esta mal)");
		
		for(Class clas: classes) {
			if(clas.getSuperclass().equalsIgnoreCase("Actor")) {
				System.out.println(clas.getName());
			}
		}
		
		HephUtil.genera(rutaSalida, rutaCarpeta);
		HephUtil.generatePopulate(rutaSalida, classes);
		
		System.out.println("Hecho");
	}
}
