package servidor;

import cliente.Cliente;
import empleado.Empleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;

public interface IClienteEmpleado {
	void registrarCliente(Cliente cliente) throws DniYaRegistradoException;
	void eliminaCliente(Cliente cliente);
	void enviarClienteAEmpleado(Empleado empleado, Cliente cliente);
	void registrarEmpleado(Empleado empleado) throws BoxYaRegistradoException;
	void clienteNoPresentado(Empleado empleado);
}
