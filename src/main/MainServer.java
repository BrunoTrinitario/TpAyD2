package main;

import controlador.ControladorServidor;
import servidor.Servidor;
import util.Constantes;
import vista.VistaServidor;

public class MainServer {

	public static void main(String[] args) {
		
		Servidor servidor = new Servidor(Constantes.PUERTO);
		servidor.start();
		VistaServidor vistaServidor = new VistaServidor(1, Constantes.PUERTO);
		ControladorServidor controladorServidor = new ControladorServidor(vistaServidor, servidor);		
	}
}
