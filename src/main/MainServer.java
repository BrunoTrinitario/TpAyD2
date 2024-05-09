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
		
		Servidor servidor = new Servidor(puerto);
		Conexion conexion = new Conexion();
		String msg = null;
		for (int aux : Constantes.PUERTOS) {
			if (aux != puerto) {
				
					break;
			}
		}
		
		servidor.start();
		VistaServidor vistaServidor = new VistaServidor(1, puerto);
		ControladorServidor controladorServidor = new ControladorServidor(vistaServidor, servidor);		
	}
}
