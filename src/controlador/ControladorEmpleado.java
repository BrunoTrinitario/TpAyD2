package controlador;

import java.io.IOException;

import cliente.Cliente;
import empleado.EmpleadoFacade;
import empleado.IStateEmpleado;
import excepciones.BoxYaRegistradoException;
import util.Constantes;
import util.EstadoEmpleado;
import vista.VentanaEmergente;
import vista.VistaEmpleado;
import vista.VistaEmpleadoRegistro;

public class ControladorEmpleado {
	EmpleadoFacade ne=new EmpleadoFacade(this);
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
	public void cambioEstado(IStateEmpleado estado) {
		ne.cambioEstado(estado);
	}
	
	public void clienteAusente() {
		this.ne.clienteAusente();
	}
	
	public IStateEmpleado getEstado() {
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
	public void bloquearVista() {
		this.vistaEmpleado.bloquearVista();
		
	}
	public void desbloquearVista() {
		this.vistaEmpleado.desbloquearVista();
		
	}

}
