package main;

import controlador.ControladorServidor;
import servidor.Servidor;
import util.Constantes;
import vista.VistaServidor;

public class MainServer3 {

	public static void main(String[] args) {
		
		final int puerto = Constantes.PUERTO3;
		final int numeroServidor = 3;
		VistaServidor vistaServidor = new VistaServidor(numeroServidor, puerto);
		ControladorServidor controladorServidor = new ControladorServidor(vistaServidor, puerto);	

	}
}
