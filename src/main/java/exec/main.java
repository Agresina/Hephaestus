package exec;

import java.io.File;

import javax.swing.JOptionPane;

import util.HephUtil;

public class main {

	private static final String rutaProyecto = "//Proyecto//";
	private static final String rutaClases = "//Clases//";

	public static final void main(final String[] args) {
		
		System.out.println("========== Hephaestus Generator ==========");
		
		try {
			File f = new File(".");
			//System.out.println(f.getCanonicalPath());
			System.out.println("---------- Generating ----------");
			HephUtil.genera(f.getCanonicalPath() + rutaProyecto, f.getCanonicalPath() + rutaClases);
			System.out.println("========== All done! ==========");
			//JOptionPane.showMessageDialog(null, "Generado");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
