package empleado;

import cliente.Cliente;
import excepciones.BoxYaRegistradoException;
import util.EstadoEmpleado;

public interface IActualizar {
	void informaEstado(Empleado empleado);
	void finalizarAtencion();
	void clienteAtendido(Cliente cliente,Empleado empleado);
	void clienteAusente(Cliente cliente,Empleado empleado);
	void crearEmpleado(String nombre,int box) throws BoxYaRegistradoException;
	void informarAcceso(Empleado empleado) throws BoxYaRegistradoException;
	void cambioEstado(EstadoEmpleado estado);
}
