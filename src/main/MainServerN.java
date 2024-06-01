package main;

import java.io.IOException;
import java.net.ServerSocket;

import controlador.ControladorServidor;
import persistencia.AbstractFactoryArchivo;
import persistencia.FactoryDeFactorys;
import persistencia.ILectoEscritura;
import servidor.Servidor;
import util.Constantes;
import util.LectorArchivoTexto;
import vista.VentanaEmergente;
import vista.VistaServidor;

public class MainServerN {

	public static void main(String[] args) {
		int puerto=0;
		ServerSocket s=null;
		for (int i : Constantes.PUERTOS) {
			try {
				 s = new ServerSocket(i+1000);
				 puerto=i;
				break;
			} catch (IOException e) {
			}
		}
		if (s!=null) {
			VistaServidor vistaServidor = new VistaServidor(Constantes.PUERTOS.indexOf(puerto)+1, puerto);
			ControladorServidor controladorServidor = new ControladorServidor(vistaServidor, puerto);
			
			AbstractFactoryArchivo afa = FactoryDeFactorys.crearFactory(leerArchivo());
			
			ILectoEscritura tipoArchivo = afa.getTipoArchivo("Lectoescritura");
							
			Servidor servidor = new Servidor(puerto, controladorServidor, tipoArchivo);
			controladorServidor.addServidor(servidor);
		}
		else {
			VentanaEmergente ve = new VentanaEmergente("No hay mas puertos disponibles");
		}
		
		
		
		
	}
	
	private static String leerArchivo() {
		String contenido = null;

        try {
             contenido = LectorArchivoTexto.leerArchivo(Constantes.ARCHIVO_CONFIGURACION);
            System.out.println("Contenido leído del archivo:");
            System.out.println(contenido);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }
        return contenido;
	}
}
