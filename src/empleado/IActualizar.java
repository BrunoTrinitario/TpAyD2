package empleado;

import cliente.Cliente;
import util.EstadoEmpleado;

public interface IActualizar {
	void informaEstado(Empleado empleado);
	void finalizarAtencion();
	void clienteAtendido(Cliente cliente,Empleado empleado);
	void clienteAusente(Cliente cliente,Empleado empleado);
	void crearEmpleado(String nombre,int box);
	void informarAcceso(Empleado empleado);
	void cambioEstado(EstadoEmpleado estado);
}
