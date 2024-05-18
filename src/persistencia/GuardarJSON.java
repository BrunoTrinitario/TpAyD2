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
import java.util.Map;



import cliente.Cliente;
import util.Constantes;


public class GuardarJSON implements IGuardado {

	@Override
	public void guardar(Cliente cliente, String accion) {
		String dir=Constantes.PATH_LOG+".json";
		File archivo=new File(dir);
		LocalTime tl=LocalTime.now();
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String ha=tl.format(formatoHora);
		ClienteAJson clienteJson=new ClienteAJson(cliente.getDni(),ha,accion);
		if (archivo.exists()) {
			try {
				String dato="";
				BufferedReader br=new BufferedReader(new FileReader(dir));
				String linea;
				while ((linea=br.readLine()) !=null) {
					dato+=linea;
				}
				ArrayList<ClienteAJson> lista=ListaFromJson(dato);
				try (FileWriter escribir = new FileWriter(dir)) {
					for (ClienteAJson i:lista)
						escribir.write(i.toJson());
					escribir.write(clienteJson.toJson());
				} catch (IOException e) {
				}
			} catch (IOException e) {
			}
		} else {
			try (FileWriter escribir = new FileWriter(dir)) {
				escribir.write(clienteJson.toJson());
			} catch (IOException e) {
			}
		}
	}
	private ArrayList<ClienteAJson> ListaFromJson(String dato){
		String aux=dato;
		aux=aux.replace('}',' ');
		aux=aux.replace('{', ',');
		System.out.println(aux);
		String[] vec=aux.split(",");
		ArrayList<ClienteAJson> lista=new ArrayList<ClienteAJson>();
		for(int i=1;i<vec.length;i+=3) {
			String auxHora=vec[i].split(" ")[1].replace('"','\0');
			String auxDni=vec[i+1].split(" ")[2].replace('"','\0');
			String auxAccion=vec[i+2].split(" ")[2].replace('"','\0');
			lista.add(new ClienteAJson(auxDni,auxHora,auxAccion));
		}
		return lista;
	}

}
