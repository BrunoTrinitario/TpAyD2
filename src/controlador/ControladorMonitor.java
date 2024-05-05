package controlador;

import java.io.IOException;
import java.util.ArrayList;

import servidor.Metrica;
import servidor.Servidor;
import util.Conexion;
import util.Constantes;
import vista.VistaMonitor;

public class ControladorMonitor {
	private VistaMonitor va;
	private Conexion conexion;
	private ArrayList<Servidor> servidores = new ArrayList<>();
	
	public ControladorMonitor() {
		this.va = new VistaMonitor(this);
		Servidor servidor = new Servidor();
		servidor.start();
		servidores.add(servidor);
		conexion = new Conexion();
	}
	
	public Metrica solicitarMetricas() throws IOException {
		return conexion.solicitudDeActulizacionMetricas(null,Constantes.SOLICITAR_METRICAS);
	}
	

	
}
