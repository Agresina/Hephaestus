package tests;

import util.Translator;

public class TranslationTest {

	public static void main(String[] args) throws Exception {
		Translator translator = new Translator("en", "es");
		
		System.out.println(translator.translate("Hello"));
		//Ya se que no es un test como tal, pero era por probar
	}
}


