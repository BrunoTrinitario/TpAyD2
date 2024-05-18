package persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import cliente.Cliente;
import util.Constantes;

public class GuardarTXT implements IGuardado {

	@Override
	public void guardar(Cliente cliente,String accion) {
		String dir=Constantes.PATH_LOG+".txt";
		File archivo=new File(dir);
		LocalTime tl=LocalTime.now();
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String ha=tl.format(formatoHora);
		if (archivo.exists()) {
			try (FileWriter escribir = new FileWriter(dir, true)) {
		       escribir.write(ha+" "+cliente.getDni()+" "+accion+"\n");
		       escribir.close();
		    } catch (IOException e) {
		    }
		}else {
			try (FileWriter escribir = new FileWriter(dir)) {
				escribir.write(ha+" "+cliente+" "+accion+"\n");
			    escribir.close();
	        } catch (IOException e) {
	        }
		}
	}

}
