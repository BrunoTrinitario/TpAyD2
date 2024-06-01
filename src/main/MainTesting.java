package main;

import java.io.IOException;
import java.net.ServerSocket;

import conexion.Conexion;
import controlador.ControladorAdministrador;
import controlador.ControladorEmpleado;
import controlador.ControladorNotificaciones;
import controlador.ControladorServidor;
import util.Constantes;
import vista.VentanaEmergente;
import vista.VistaRegistroDni;
import vista.VistaServidor;

public class MainTesting {

	public static void main(String[] args) {
		
		
		ControladorAdministrador ca = new ControladorAdministrador();
		VistaRegistroDni rc=new VistaRegistroDni();
		ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
		ControladorEmpleado controladorEmpleado2 = new ControladorEmpleado();
		ControladorNotificaciones cn = new ControladorNotificaciones();
		Conexion conexion = new Conexion();
		try {
			conexion.envioNotificacionesAServidor(cn, Constantes.NOTIFICACIONES);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
