package main;

import java.io.IOException;

import controlador.ControladorNotificaciones;
import util.Conexion;
import util.Constantes;

public class MainNotificaciones {

	public static void main(String[] args) {
		
		ControladorNotificaciones cn = new ControladorNotificaciones();
		Conexion conexion = new Conexion();
		try {
			conexion.envioNotificacionesAServidor(cn, Constantes.NOTIFICACIONES);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
