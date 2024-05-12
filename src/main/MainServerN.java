package main;

import java.io.IOException;
import java.net.ServerSocket;

import controlador.ControladorServidor;
import util.Constantes;
import vista.VentanaEmergente;
import vista.VistaServidor;

public class MainServerN {

	
	
	public static void main(String[] args) {
		int puerto=0;
		ServerSocket s=null;
		for (int i : Constantes.PUERTOS) {
			try {
				System.out.println(i);
				 s = new ServerSocket(i+1000);
				 puerto=i;
				break;
			} catch (IOException e) {
			}
		}
		if (s!=null) {
			VistaServidor vistaServidor = new VistaServidor(Constantes.PUERTOS.indexOf(puerto)+1, puerto);
			ControladorServidor controladorServidor = new ControladorServidor(vistaServidor, puerto);	
		
		}
		else {
			VentanaEmergente ve = new VentanaEmergente("No hay mas puertos disponibles");
		}
		
			
	}
}
