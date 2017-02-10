package exec;

import java.io.File;

import util.HephUtil;

public class main {
	
	private static final String rutaProyecto = "\\Proyecto\\";
	private static final String rutaClases = "\\Clases\\";

	public static void main(String[] args) {
		try {
			File f = new File(".");
			System.out.println(f.getCanonicalPath());
			// TODO Auto-generated method stub
			HephUtil.genera(f.getCanonicalPath()+rutaProyecto,f.getCanonicalPath()+ rutaClases);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
