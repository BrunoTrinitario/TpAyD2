package persistencia;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import cliente.Cliente;
import util.Constantes;



public class LectoGuardadoXML implements ILectoEscritura {
	private File archivoLOG=null,archivoDATOS=null;
	private String dirLOG,dirDATOS;
	private HashMap<String,String[]>clienteMemoria=null;
	public LectoGuardadoXML() {
		dirLOG=Constantes.PATH_LOG+".xml";
		dirDATOS=Constantes.PATH_DATOS+".xml";
		archivoLOG=new File(dirLOG);
		archivoDATOS=new File(dirDATOS);
	}
	@Override
	public void guardar(Cliente cliente, String accion) {
		LocalTime tl=LocalTime.now();
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String ha=tl.format(formatoHora);
		ClienteAXML clienteXml=new ClienteAXML(cliente.getDni(),ha,accion);
		if (archivoLOG.exists()) {
			try {
				XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(dirLOG)));
				ListaClientesXML lectura=(ListaClientesXML)decoder.readObject();
				lectura.addLista(clienteXml);
				XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(dirLOG)));
				encoder.writeObject(lectura);
				encoder.close();
			} catch (FileNotFoundException e) {	
			}
		}else {
			try {
				XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(dirLOG)));
				ListaClientesXML lcxml=new ListaClientesXML();
				lcxml.addLista(clienteXml);
				encoder.writeObject(lcxml);
				encoder.close();
			} catch (FileNotFoundException e) {
				
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
		ArrayList<String> datos=new ArrayList<String>();
		if (clienteMemoria == null) {
			if (archivoDATOS.exists()) {
				XMLDecoder decoder;
				try {
					decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(dirDATOS)));
					ListaInfoClientesXML lectura = (ListaInfoClientesXML) decoder.readObject();
					ArrayList<ClienteRegistradoXML> lista = lectura.getLista();
					for (ClienteRegistradoXML i : lista) {
						String[] aux= {i.getFecha(),i.getGrupo()};
						clienteMemoria.put(i.getDni(), aux);
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


}
