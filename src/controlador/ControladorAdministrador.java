package controlador;

import java.io.IOException;

import conexion.Conexion;
import servidor.Metrica;
import util.Constantes;
import util.PingEcho;
import vista.VistaAdministrador;

public class ControladorAdministrador {
	private VistaAdministrador va;
	private Conexion conexion;
	private int contador=1;
	
	public ControladorAdministrador() {
		this.va = new VistaAdministrador(this);
		conexion = new Conexion();
		for (int i:Constantes.PUERTOS) {
			new PingEcho(Constantes.IP,i+2000,this).start();
		}

	}
	
	public Metrica solicitarMetricas() throws IOException {
		return conexion.solicitudDeActulizacionMetricas(null,Constantes.SOLICITAR_METRICAS);
	}
	
	public synchronized void pingEcho(String texto) {
		va.pingEchoTabla(contador+". "+texto);
		String pal[] =texto.split(" ");
		va.agregarATablaLog(pal[0],pal[2]);
		contador++;
	}
}
