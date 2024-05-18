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

import cliente.Cliente;
import util.Constantes;



public class GuardarXML implements IGuardado {

	@Override
	public void guardar(Cliente cliente, String accion) {
		String dir=Constantes.PATH_LOG+".xml";
		File archivo=new File(dir);
		LocalTime tl=LocalTime.now();
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		String ha=tl.format(formatoHora);
		ClienteAXML clienteXml=new ClienteAXML(cliente.getDni(),ha,accion);
		if (archivo.exists()) {
			try {
				XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(new FileInputStream(dir)));
				ListaClientesXML lectura=(ListaClientesXML)decoder.readObject();
				lectura.addLista(clienteXml);
				XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(dir)));
				encoder.writeObject(lectura);
				encoder.close();
			} catch (FileNotFoundException e) {	
			}
		}else {
			try {
				XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(dir)));
				ListaClientesXML lcxml=new ListaClientesXML();
				lcxml.addLista(clienteXml);
				encoder.writeObject(lcxml);
				encoder.close();
			} catch (FileNotFoundException e) {
				
			} 
		}
	}

}