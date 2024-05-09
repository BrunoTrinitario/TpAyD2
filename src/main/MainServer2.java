package main;

import controlador.ControladorServidor;
import servidor.Servidor;
import util.Constantes;
import vista.VistaServidor;

public class MainServer2 {

	public static void main(String[] args) {
		
		Servidor servidor = new Servidor(Constantes.PUERTO2);
		servidor.start();
		VistaServidor vistaServidor = new VistaServidor(2, Constantes.PUERTO2);
		ControladorServidor controladorServidor = new ControladorServidor(vistaServidor, servidor);	
	}

}
