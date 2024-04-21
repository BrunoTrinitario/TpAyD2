package controlador;

import cliente.Cliente;
import empleado.NegociosEmpleado;
import excepciones.BoxYaRegistradoException;
import util.EstadoEmpleado;
import vista.VistaEmpleadoRegistro;
import vista.VistaEmpleado;

public class ControladorEmpleado {
	NegociosEmpleado ne=new NegociosEmpleado(this);
	VistaEmpleadoRegistro er = new VistaEmpleadoRegistro(this);
	VistaEmpleado vistaEmpleado;
		
	public void crearEmpleado(String dni,int box) throws BoxYaRegistradoException{
		ne.crearEmpleado(dni, box);
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
}
