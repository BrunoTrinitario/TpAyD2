package controlador;

import java.time.LocalTime;
import servidor.Metrica;
import util.Conexion;
import util.Constantes;

public class ControladorAdministrador {
	private Conexion conexion=new Conexion();
	public Metrica solicitarMetricas() {
		return conexion.solicitudDeActulizacionMetricas(null,Constantes.SOLICITAR_METRICAS);
	}
	
}
