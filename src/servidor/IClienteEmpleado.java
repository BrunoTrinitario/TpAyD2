package servidor;

import cliente.Cliente;
import empleado.Empleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;

public interface IClienteEmpleado {
	void registrarCliente(Cliente cliente) throws DniYaRegistradoException;
	void agregarEmpleadoANoDisponible(Empleado empleado) throws BoxYaRegistradoException;
	void agregarEmpleadoADisponible(Empleado empleado);
	void matchClienteEmpleado();
	void clienteNoPresentado(Cliente cliente);
	void eliminaCliente(Cliente cliente);
	void enviarClienteAEmpleado(Cliente cliente);
	void agregarEmpleadoAAtendiendo(Empleado empleado);
}
