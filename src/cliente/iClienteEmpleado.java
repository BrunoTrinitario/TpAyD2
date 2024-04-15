package cliente;

public interface iClienteEmpleado {
	void registrarCliente(Cliente cliente);
	void agregarEmpleadoANoDisponible(Empleado empleado);
	void agregarEmpleadoADisponible(Empleado empleado);
	void matchClienteEmpleado();
	void clienteNoPresentado(Cliente cliente);
	void eliminaCliente(Cliente cliente);
	void enviarClienteAEmpleado(Cliente cliente);
}
