package main;

import controlador.ControladorServidor;
import servidor.Servidor;
import util.Constantes;
import vista.VistaServidor;

public class MainServer2 {

	public static void main(String[] args) {
		
		final int puerto = Constantes.PUERTO2;
		final int numeroServidor = 2;
		VistaServidor vistaServidor = new VistaServidor(numeroServidor, puerto);
		ControladorServidor controladorServidor = new ControladorServidor(vistaServidor, puerto);	

	}
}
