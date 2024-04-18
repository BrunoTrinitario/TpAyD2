package servidor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cliente.Cliente;
import controlador.ControladorNotificaciones;
import empleado.Empleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.Constantes;

public class GestorColas implements IClienteEmpleado {
	private Queue<Cliente> clientesEnEspera=new LinkedList<Cliente>();
	private ArrayList<Empleado> EmpleadosNoDisponibles=new ArrayList<Empleado>();
	private ArrayList<Empleado> EmpleadosDisponibles=new ArrayList<Empleado>();
	private ArrayList<Empleado> EmpleadosAtendiendo=new ArrayList<Empleado>();
	private ControladorNotificaciones cn=new ControladorNotificaciones();
	@Override
	public void registrarCliente(Cliente cliente) throws DniYaRegistradoException{
		if (!clientesEnEspera.contains(cliente))
			this.clientesEnEspera.add(cliente);
		else {
			throw new DniYaRegistradoException(Constantes.DNI_YA_REGISTRADO);
		}
	}

	@Override
	public void agregarEmpleadoANoDisponible(Empleado empleado) throws BoxYaRegistradoException {
		if (!EmpleadosNoDisponibles.contains(empleado))
			this.EmpleadosNoDisponibles.add(empleado);
		else {
			throw new BoxYaRegistradoException(Constantes.BOX_YA_REGISTRADO);
		}
	}

	@Override
	public void agregarEmpleadoADisponible(Empleado empleado) {
		this.EmpleadosDisponibles.add(empleado);
	}
	
	@Override
	public void agregarEmpleadoAAtendiendo(Empleado empleado) {
		this.EmpleadosAtendiendo.add(empleado);
	}

	@Override
	public void matchClienteEmpleado() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clienteNoPresentado(Cliente cliente) {
		if (cliente.getIntento()<3) {
			cliente.sumarIntento();
			this.clientesEnEspera.add(cliente);
		}
	}

	@Override
	public void eliminaCliente(Cliente cliente) {
		this.clientesEnEspera.remove(cliente);
	}

	@Override
	public void enviarClienteAEmpleado(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}
	
	
}
