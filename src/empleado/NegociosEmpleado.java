package empleado;

import cliente.Cliente;
import excepciones.BoxYaRegistradoException;
import util.Conexion;
import util.EstadoEmpleado;

public class NegociosEmpleado implements IActualizar {
	private Conexion conexion=new Conexion();
	private Empleado empleado;
	
	@Override
	public void informaEstado(Empleado empleado) {
		//conexion.envioEmpleadoAServidor(empleado,"estado");
	}

	@Override
	public void cambioEstado(EstadoEmpleado estado) {
		this.empleado.cambioEstado(estado);
		this.informaEstado(empleado);
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
	public void crearEmpleado(String nombre, int box) throws BoxYaRegistradoException {
		this.empleado=new Empleado(nombre,box);
		this.informarAcceso(empleado);
	}

	@Override
	public void informarAcceso(Empleado empleado) throws BoxYaRegistradoException {
		conexion.envioEmpleadoAServidor(empleado,"agregar");
	}


}
