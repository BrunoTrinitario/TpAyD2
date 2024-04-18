package servidor;

import cliente.Cliente;
import empleado.Empleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;

public interface IClienteEmpleado {
	void registrarCliente(Cliente cliente) throws DniYaRegistradoException;
	void clienteNoPresentado(Cliente cliente);
	void eliminaCliente(Cliente cliente);
	void enviarClienteAEmpleado(Empleado empleado, Cliente cliente);
	void agregarEmpleadoAAtendiendo(Empleado empleado);
	void agregarEmpleadoANoAtendiendo(Empleado empleado) throws BoxYaRegistradoException;
}
