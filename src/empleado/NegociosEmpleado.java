package empleado;

import cliente.Cliente;

public class NegociosEmpleado implements IActualizar {

	private Empleado empleado;
	
	public NegociosEmpleado(Empleado empleado) {
		this.empleado=empleado;
	}
	
	@Override
	public void informaEstado(Empleado empleado, boolean estado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambioEstado() {
		this.empleado.cambioEstado();
		this.informaEstado(empleado, false);
	}

	@Override
	public void finalizarAtencion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clienteAtendido(Cliente cliente, Empleado empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clienteAusente(Cliente cliente, Empleado empleado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearEmpleado(String nombre, int box) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void informarAcceso(Empleado empleado) {
		// TODO Auto-generated method stub
		
	}

}
