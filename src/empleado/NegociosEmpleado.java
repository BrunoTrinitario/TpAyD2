package empleado;

import cliente.Cliente;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.Conexion;
import util.Constantes;
import util.EstadoEmpleado;

public class NegociosEmpleado implements IActualizar {
	private Conexion conexion=new Conexion();
	private Empleado empleado;
	
	@Override
	public void informaEstado(Empleado empleado) {
		try {
			conexion.envioEmpleadoAServidor(empleado,"estado");
		} catch (BoxYaRegistradoException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void cambioEstado(EstadoEmpleado estado) {
		this.empleado.cambioEstado(estado);
		this.informaEstado(empleado);
	}
	public EstadoEmpleado getEstado() {
		return empleado.getEstado();
	}
	/*public void informaAtencionFinalizada(Cliente cliente)   {
		conexion.envioClienteAServidor(cliente, "AtencionFinalizada");
	}
	@Override
	public void finalizarAtencion(Cliente cliente)  {
		this.empleado.cambioEstado(EstadoEmpleado.NoDisponible);
		this.informaAtencionFinalizada(cliente);
	}
*/


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
		conexion.envioEmpleadoAServidor(empleado,Constantes.EMPLEADO_NUEVO);
	}

	@Override
	public void finalizarAtencion(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}


}
