package empleado;

import java.io.IOException;

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
	
	public Empleado getEmpleado() {
		return empleado;
	}

	@Override
	public void informaEstado(Empleado empleado) {
		conexion.informarAccionAServidor(empleado,Constantes.EMPLEADO_CAMBIO_ESTADO);
	}

	@Override
	public void cambioEstado(EstadoEmpleado estado) {
		this.empleado.cambioEstado(estado);
		this.informaEstado(empleado);
	}
	public EstadoEmpleado getEstado() {
		return empleado.getEstado();
	}

	public void finalizarAtencion()  {
		this.empleado.cambioEstado(EstadoEmpleado.NoDisponible);
		conexion.informarAccionAServidor(empleado,Constantes.ATENCION_FINALIZADA);
	}

	@Override
	public void clienteAusente() {
		this.empleado.cambioEstado(EstadoEmpleado.NoDisponible);
		conexion.informarAccionAServidor(this.empleado,Constantes.CLIENTE_AUSENTE);
	}

	@Override
	public int crearEmpleado(String nombre, int box) throws BoxYaRegistradoException, IOException {
		this.empleado=new Empleado(nombre,box,EstadoEmpleado.NoDisponible);
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
	public void numeroServidorConectado(int servidorConectado) {
		ce.numeroServidorConectado(servidorConectado);
	}

	public void bloquearVista() {
		this.ce.bloquearVista();
		
	}
	public void desbloquearVista() {
		this.ce.desbloquearVista();
		
	}
}
