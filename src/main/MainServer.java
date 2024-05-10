package main;

import java.io.IOException;

import controlador.ControladorServidor;
import servidor.Servidor;
import util.Conexion;
import util.Constantes;
import vista.VistaServidor;

public class MainServer {

	
	
	public static void main(String[] args) {
		
		final int puerto = Constantes.PUERTO;
		final int numeroServidor = 1;
		VistaServidor vistaServidor = new VistaServidor(numeroServidor, puerto);
		ControladorServidor controladorServidor = new ControladorServidor(vistaServidor, puerto);	
		
		
		
			
	}
}
