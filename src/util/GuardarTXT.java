package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cliente.Cliente;

public class GuardarTXT implements IGuardado {

	@Override
	public void guardar(Cliente cliente,String hora,String accion) {
		String dir=Constantes.PATH_LOG+".txt";
		File archivo=new File(dir);
		if (archivo.exists()) {
			try (FileWriter escribir = new FileWriter(dir, true)) {
		       escribir.write(hora+" "+cliente.getDni()+" "+accion+"\n");
		       escribir.close();
		    } catch (IOException e) {
		    }
		}else {
			try (FileWriter escribir = new FileWriter(dir)) {
				escribir.write(hora+" "+cliente+" "+accion+"\n");
			    escribir.close();
	        } catch (IOException e) {
	        }
		}
	}

}
