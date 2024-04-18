package controlador;

import util.Conexion;

public class ControladorAdministrador {
	private Conexion conexion=new Conexion();
	public void solicitarMetricas() {
		conexion.solicitudDeActulizacionMetricas();
		
	}
	
}
