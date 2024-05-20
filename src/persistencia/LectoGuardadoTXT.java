package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import cliente.Cliente;
import util.Constantes;

public class LectoGuardadoTXT implements ILectoEscritura {
	private File archivoLOG=null,archivoDATOS=null;
	private String dirLOG,dirDATOS;
	public LectoGuardadoTXT() {
		dirLOG=Constantes.PATH_LOG+".txt";
		dirDATOS=Constantes.PATH_DATOS+".txt";
		archivoLOG=new File(dirLOG);
		archivoDATOS=new File(dirDATOS);
	}
	@Override
	public void guardar(Cliente cliente,String accion) {
		LocalTime tl=LocalTime.now();
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String ha=tl.format(formatoHora);
		if (archivoLOG.exists()) {
			try (FileWriter escribir = new FileWriter(dirLOG, true)) {
		       escribir.write(ha+" "+cliente.getDni()+" "+accion+"\n");
		       escribir.close();
		    } catch (IOException e) {
		    }
		}else {
			try (FileWriter escribir = new FileWriter(dirLOG)) {
				escribir.write(ha+" "+cliente+" "+accion+"\n");
			    escribir.close();
	        } catch (IOException e) {
	        }
		}
	}

	@Override
	public String buscaFecha(String dni) {
		ArrayList<String> datos=buscar(dni);
		if (!datos.isEmpty()) {
			return datos.get(0);
		}else
			return null;
	}

	@Override
	public String buscarGrupo(String dni) {
		ArrayList<String> datos=buscar(dni);
		if (!datos.isEmpty()) {
			return datos.get(1);
		}else
			return null;
	}

	@Override
	public ArrayList<String> buscar(String dni) {
		ArrayList<String> datos = new ArrayList<String>();
		if (archivoDATOS.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(archivoDATOS));
				String line;
				try {
					while ((line = br.readLine()) != null) {
						String[] linea = line.split(" ");
						if (linea[0].equals(dni)) {
							datos.add(linea[1]);
							datos.add(linea[2]);
							break;
						}
					}
				} catch (IOException e) {
				}
			} catch (FileNotFoundException e) {
			}
		}
		return datos;
	}

}