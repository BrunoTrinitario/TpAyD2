package controlador;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import cliente.Cliente;
import empleado.EmpleadoFacade;
import empleado.IStateEmpleado;
import excepciones.BoxYaRegistradoException;
import util.Constantes;
import vista.VentanaEmergente;
import vista.VistaEmpleado;
import vista.VistaEmpleadoRegistro;

public class ControladorEmpleado implements Observer {
	EmpleadoFacade ne=new EmpleadoFacade(this);
	VistaEmpleadoRegistro er = new VistaEmpleadoRegistro(this);
	VistaEmpleado vistaEmpleado;
		
	public int crearEmpleado(String dni,int box) throws BoxYaRegistradoException, IOException{	
		
		int servidor = ne.crearEmpleado(dni, box);
		for (int i=0; i<Constantes.PUERTOS.size();i++) {
			if (servidor == Constantes.PUERTOS.get(i)) {
				servidor=i+1;
				break;
			}
		}
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


	@Override
	public void update(Observable o, Object arg) {
		
		if (arg.equals(Constantes.RECONECTANDO)) {
			this.vistaEmpleado.bloquearVista();
		}
		else
		{
			this.vistaEmpleado.cambiarNumeroServidor((int)arg);
			this.vistaEmpleado.desbloquearVista();
		}
	}

}
