package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



import cliente.Cliente;
import util.Constantes;


public class LectoGuardadoJSON implements ILectoEscritura {
	private File archivoLOG=null,archivoDATOS=null;
	private String dirLOG,dirDATOS;
	private HashMap<String,String[]>clienteMemoria=null;
	public LectoGuardadoJSON() {
		dirLOG=Constantes.PATH_LOG+".json";
		dirDATOS=Constantes.PATH_DATOS+".json";
		archivoLOG=new File(dirLOG);
		archivoDATOS=new File(dirDATOS);
	}
	@Override
	public void guardar(Cliente cliente, String accion) {
		LocalTime tl=LocalTime.now();
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String ha=tl.format(formatoHora);
		ClienteAJson clienteJson=new ClienteAJson(cliente.getDni(),ha,accion);
		if (archivoLOG.exists()) {
			try {
				String dato="";
				BufferedReader br=new BufferedReader(new FileReader(dirLOG));
				String linea;
				while ((linea=br.readLine()) !=null) {
					dato+=linea;
				}
				ArrayList<ClienteAJson> lista=ListaFromJson(dato);
				try (FileWriter escribir = new FileWriter(dirLOG)) {
					for (ClienteAJson i:lista)
						escribir.write(i.toJson());
					escribir.write(clienteJson.toJson());
				} catch (IOException e) {
				}
			} catch (IOException e) {
			}
		} else {
			try (FileWriter escribir = new FileWriter(dirLOG)) {
				System.out.println("ACACACACACACACACACACACCACA"+clienteJson.toJson());
				escribir.write(clienteJson.toJson());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private ArrayList<ClienteAJson> ListaFromJson(String dato){
		String aux=dato;
		aux=aux.replace('}',' ');
		aux=aux.replace('{', ',');
		String[] vec=aux.split(",");
		ArrayList<ClienteAJson> lista=new ArrayList<ClienteAJson>();
		for(int i=1;i<vec.length;i+=3) {
			String auxST=vec[i].split(" ")[1];
			String auxHora=auxST.substring(1,auxST.length()-1);
			auxST=vec[i+1].split(" ")[2];
			String auxDni=auxST.substring(1,auxST.length()-1);
			auxST=vec[i+2].substring(11,vec[i+2].length());
			String auxAccion=auxST.substring(1,auxST.length()-2);
			lista.add(new ClienteAJson(auxHora,auxDni,auxAccion));
		}
		return lista;
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
		if (clienteMemoria == null) {
			if (archivoDATOS.exists()) {
				clienteMemoria=new HashMap<>();
				String dato = "";
				try {
					BufferedReader br = new BufferedReader(new FileReader(dirDATOS));
					String linea;
					try {
						while ((linea = br.readLine()) != null) {
							dato += linea;
						}
						ArrayList<ClienteRegistradoJSON> lista = DatosFromJson(dato);
						for (ClienteRegistradoJSON i : lista) {
							String[] aux= {i.getFecha(),i.getGrupo()};
							clienteMemoria.put(i.getDni(), aux);
						}
					} catch (IOException e) {
					}
				} catch (FileNotFoundException e) {
				}
			}
		}
		if (clienteMemoria.get(dni)!=null && clienteMemoria!=null) {
			datos.add(clienteMemoria.get(dni)[0]);
			datos.add(clienteMemoria.get(dni)[1]);
		}
		return datos;
	}
	private ArrayList<ClienteRegistradoJSON> DatosFromJson(String dato){
		String aux=dato;
		aux=aux.replace('}',' ');
		aux=aux.replace('{', ',');
		String[] vec=aux.split(",");
		ArrayList<ClienteRegistradoJSON> lista=new ArrayList<ClienteRegistradoJSON>();
		for(int i=1;i<vec.length;i+=3) {
			String auxDni=vec[i].split(" ")[1];
			auxDni=auxDni.substring(1,auxDni.length()-1);
			String auxFecha=vec[i+1].split(" ")[2];
			auxFecha=auxFecha.substring(1,auxFecha.length()-1);
			String auxGrupo=vec[i+2].split(" ")[2];
			auxGrupo=auxGrupo.substring(1,auxGrupo.length()-1);
			lista.add(new ClienteRegistradoJSON(auxDni,auxFecha,auxGrupo));
		}
		return lista;
	}

}
