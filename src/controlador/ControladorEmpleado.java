package controlador;

import java.io.IOException;

import cliente.Cliente;
import empleado.NegociosEmpleado;
import excepciones.BoxYaRegistradoException;
import util.Constantes;
import util.EstadoEmpleado;
import vista.VentanaEmergente;
import vista.VistaEmpleado;
import vista.VistaEmpleadoRegistro;

public class ControladorEmpleado {
	NegociosEmpleado ne=new NegociosEmpleado(this);
	VistaEmpleadoRegistro er = new VistaEmpleadoRegistro(this);
	VistaEmpleado vistaEmpleado;
		
	public int crearEmpleado(String dni,int box) throws BoxYaRegistradoException, IOException{	
		
		int servidor = ne.crearEmpleado(dni, box);
		System.out.println(servidor);
		for (int i=0; i<Constantes.PUERTOS.size();i++) {
			if (servidor == Constantes.PUERTOS.get(i)) {
				servidor=i+1;
				break;
			}
		}
		System.out.println(servidor);
		return servidor;
	}
	public void cambioEstado(EstadoEmpleado estado) {
		ne.cambioEstado(estado);
	}
	
	public void clienteAusente() {
		this.ne.clienteAusente();
	}
	
	public EstadoEmpleado getEstado() {
		return ne.getEstado();
	}
	public void finalizarAtencion() {
		ne.finalizarAtencion();
	}
	public void informarAtencionAVista(Cliente cliente) {
		this.vistaEmpleado.recepcionClientes(cliente);		
	}
	public void setVistaPrincipal(VistaEmpleado vistaEmpleado) {
		this.vistaEmpleado=vistaEmpleado;		
	}
	public void conexionCaida() {
		VentanaEmergente ve = new VentanaEmergente(Constantes.ERROR_CONEXION);
		this.vistaEmpleado.ConexionCaida();
	}
	public void numeroServidorConectado(int servidorConectado) {
		this.vistaEmpleado.cambiarNumeroServidor(servidorConectado);
	}
}
