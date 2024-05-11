package controlador;

import java.io.IOException;

import servidor.Metrica;
import util.Conexion;
import util.Constantes;
import util.PingEcho;
import vista.VistaAdministrador;

public class ControladorAdministrador {
	private VistaAdministrador va;
	private Conexion conexion;
	
	public ControladorAdministrador() {
		this.va = new VistaAdministrador(this);
		conexion = new Conexion();
		for (int i:Constantes.PUERTOS) {
			new PingEcho(Constantes.IP,i+1000,this).start();
		}

	}
	
	public Metrica solicitarMetricas() throws IOException {
		return conexion.solicitudDeActulizacionMetricas(null,Constantes.SOLICITAR_METRICAS);
	}
	
	public void pingEcho(String texto) {
		va.pingEchoTabla(texto);
	}
}
