package empleado;

import cliente.Cliente;
import controlador.ControladorEmpleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.Conexion;
import util.Constantes;
import util.EstadoEmpleado;

public class NegociosEmpleado implements IActualizar {
	private Conexion conexion=new Conexion();
	private Empleado empleado;
	private ControladorEmpleado ce;
	
	public NegociosEmpleado(ControladorEmpleado ce) {
		this.ce=ce;
	}
	
	@Override
	public void informaEstado(Empleado empleado) {
		conexion.cambioEstadoEmpleado(empleado,Constantes.EMPLEADO_CAMBIO_ESTADO);
	}

	@Override
	public void cambioEstado(EstadoEmpleado estado) {
		this.empleado.cambioEstado(estado);
		this.informaEstado(empleado);
	}
	public EstadoEmpleado getEstado() {
		return empleado.getEstado();
	}
	public void informaAtencionFinalizada(Cliente cliente)   {
		try {
			conexion.envioClienteAServidor(cliente, "AtencionFinalizada");
		} catch (DniYaRegistradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void finalizarAtencion()  {
		this.empleado.cambioEstado(EstadoEmpleado.NoDisponible);
		this.informaAtencionFinalizada(this.empleado.getCliente());
	}

	@Override
	public void clienteAusente() throws DniYaRegistradoException {
		this.empleado.cambioEstado(EstadoEmpleado.Disponible);
		conexion.envioClienteAServidor(this.empleado.getCliente(),Constantes.ClienteAusente);
	}

	@Override
	public void crearEmpleado(String nombre, int box) throws BoxYaRegistradoException {
		this.empleado=new Empleado(nombre,box);
		this.informarAcceso(empleado);
	}

	@Override
	public void informarAcceso(Empleado empleado) throws BoxYaRegistradoException {
		conexion.envioEmpleadoAServidor(this,empleado,Constantes.EMPLEADO_NUEVO);
	}

	public void enviarClienteAEmpleado(Cliente cliente) {
		this.empleado.atenderCliente(cliente);
		this.ce.informarAtencionAVista(cliente);
	}
}
