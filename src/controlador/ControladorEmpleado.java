package controlador;

import cliente.Cliente;
import empleado.NegociosEmpleado;
import excepciones.BoxYaRegistradoException;
import util.EstadoEmpleado;

public class ControladorEmpleado {
	NegociosEmpleado ne=new NegociosEmpleado();
	public void crearEmpleado(String dni,int box) throws BoxYaRegistradoException{
		ne.crearEmpleado(dni, box);
	}
	public void cambioEstado(EstadoEmpleado estado) {
		ne.cambioEstado(estado);
	}
	public EstadoEmpleado getEstado() {
		return ne.getEstado();
	}
	public void finalizarAtencion(Cliente cliente) {
		ne.finalizarAtencion(cliente);
	}
}
