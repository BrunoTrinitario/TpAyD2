package empleado;

import cliente.Cliente;

public interface IStateEmpleado {
	
	
	public void atenderCliente(Empleado empleado, Cliente cliente);
	public void finalizarAtencion(Empleado empleado);
	public void clienteAusente(Empleado empleado);
	public IStateEmpleado getEstadoDTO();
}
