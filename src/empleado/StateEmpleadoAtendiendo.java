package empleado;

import java.io.Serializable;

import cliente.Cliente;

public class StateEmpleadoAtendiendo implements IStateEmpleado, Serializable {

	@Override
	public void atenderCliente(Empleado empleado, Cliente cliente) {
		// no hace nada, ya esta ataendiendo
		
	}

	@Override
	public void finalizarAtencion(Empleado empleado) {
		empleado.setCliente(null);
		empleado.cambioEstado(new StateEmpleadoNoDisponible());
		
	}

	@Override
	public void clienteAusente(Empleado empleado) {
		empleado.setCliente(null);
		empleado.cambioEstado(new StateEmpleadoNoDisponible());		
	}

	@Override
	public IStateEmpleado getEstadoDTO() {
		return new StateEmpleadoAtendiendo();
	}

}
