package controlador;

import servidor.Servidor;
import vista.VistaServidor;

public class ControladorServidor {
	private Servidor servidor;
	private VistaServidor vistaServidor;
	
	public ControladorServidor(VistaServidor vistaServidor, int puerto) {
		this.vistaServidor=vistaServidor;
	}

	public void informarServidorActivo() {
		vistaServidor.informarServidorActivo();
	}
	
	public void addServidor(Servidor servidor) {
		this.servidor = servidor;
	}

}
