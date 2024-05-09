package controlador;

import servidor.Servidor;
import vista.VistaServidor;

public class ControladorServidor {
	private Servidor servidor;
	private VistaServidor vistaServidor;
	
	public ControladorServidor(VistaServidor vistaServer, Servidor servidor) {
		this.vistaServidor=vistaServidor;
		this.servidor= new Servidor(0);
		this.servidor.start();

	}

}
