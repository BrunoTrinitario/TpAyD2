package empleado;

import java.io.Serializable;

import cliente.Cliente;

public class StateEmpleadoNoDisponible implements IStateEmpleado, Serializable  {

	@Override
	public void atenderCliente(Empleado empleado, Cliente cliente) {
		empleado.setCliente(cliente);
		
	}

	@Override
	public void finalizarAtencion(Empleado empleado) {
		//vacio
		
	}

	@Override
	public void clienteAusente(Empleado empleado) {
		//vacio
		
	}
	
	public IStateEmpleado getEstadoDTO() {
		return new StateEmpleadoNoDisponible();
	}


}
