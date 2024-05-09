package controlador;

import java.io.IOException;

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
	
	public Metrica solicitarMetricas() throws IOException {
		return conexion.solicitudDeActulizacionMetricas(null,Constantes.SOLICITAR_METRICAS);
	}
	
	public void conexionAServidor() {
		try {
			conexion.envioAdministrador(this,Constantes.ADMINISTRADOR);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
