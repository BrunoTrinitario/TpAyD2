package empleado;

import java.io.Serializable;

import cliente.Cliente;

public class StateEmpleadoDisponible implements IStateEmpleado, Serializable  {

	@Override
	public void atenderCliente(Empleado empleado, Cliente cliente) {
		empleado.setCliente(cliente);
		empleado.cambioEstado(new StateEmpleadoAtendiendo());
		
	}

	@Override
	public void finalizarAtencion(Empleado empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clienteAusente(Empleado empleado) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public IStateEmpleado getEstadoDTO() {
		return new StateEmpleadoDisponible();
	}


}
