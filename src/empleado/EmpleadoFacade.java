package empleado;

import java.io.IOException;

import cliente.Cliente;
import conexion.Conexion;
import controlador.ControladorEmpleado;
import excepciones.BoxYaRegistradoException;
import util.Constantes;
import util.EstadoEmpleado;

public class EmpleadoFacade implements IActualizar {
	private Conexion conexion;
	private Empleado empleado;
	private ControladorEmpleado ce;
	
	public EmpleadoFacade(ControladorEmpleado ce) {
		this.ce=ce;
		this.conexion=new Conexion(ce);
	}
	
	public Empleado getEmpleado() {
		return empleado;
	}

	@Override
	public void informaEstado(Empleado empleado) {
		conexion.informarAccionAServidor(empleado,Constantes.EMPLEADO_CAMBIO_ESTADO);
	}

	@Override
	public void cambioEstado(IStateEmpleado estado) {
		this.empleado.cambioEstado(estado);
		this.informaEstado(empleado);
	}
	public IStateEmpleado getEstado() {
		return empleado.getEstado();
	}

	public void finalizarAtencion()  {
		
		conexion.informarAccionAServidor(this.empleado,Constantes.ATENCION_FINALIZADA);
		this.empleado.finalizarAtencion();
	}

	@Override
	public void clienteAusente() {
		
		conexion.informarAccionAServidor(this.empleado,Constantes.CLIENTE_AUSENTE);
		this.empleado.clienteAusente();
	}

	@Override
	public int crearEmpleado(String nombre, int box) throws BoxYaRegistradoException, IOException {
		this.empleado=new Empleado(nombre,box,new StateEmpleadoNoDisponible());
		this.informarAcceso(empleado);
		return conexion.getPuertoConectado();
	}

	@Override
	public void informarAcceso(Empleado empleado) throws BoxYaRegistradoException, IOException {
		conexion.envioEmpleadoAServidor(this,Constantes.EMPLEADO_NUEVO);
	}

	public void enviarClienteAEmpleado(Cliente cliente) {
		this.empleado.atenderCliente(cliente);
		this.ce.informarAtencionAVista(cliente);
	}

	public void conexionCaida() {
		ce.conexionCaida();
	}


}
