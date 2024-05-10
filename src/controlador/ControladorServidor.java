package controlador;

import servidor.Servidor;
import vista.VistaServidor;

public class ControladorServidor {
	private Servidor servidor;
	private VistaServidor vistaServidor;
	
	public ControladorServidor(VistaServidor vistaServer, int puerto) {
		this.vistaServidor=vistaServidor;
		servidor = new Servidor(puerto);
	}

}
