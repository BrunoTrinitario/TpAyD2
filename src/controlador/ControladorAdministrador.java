package controlador;

import servidor.Metrica;
import util.Conexion;
import util.Constantes;
import vista.VistaAdministrador;

public class ControladorAdministrador {
	private VistaAdministrador va;
	private Conexion conexion;
	
	public ControladorAdministrador() {
		this.va = new VistaAdministrador(this);
		conexion = new Conexion();
	}
	
	public Metrica solicitarMetricas() {
		return conexion.solicitudDeActulizacionMetricas(null,Constantes.SOLICITAR_METRICAS);
	}
	

	
}
