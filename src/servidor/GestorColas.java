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
import util.EstadoEmpleado;

public class GestorColas implements IClienteEmpleado {
	private Servidor servidor;
	private Queue<Cliente> clientesEnEspera=new LinkedList<Cliente>();
	private Queue<Empleado> empleadosNoAtendiendo=new LinkedList<Empleado>();
	private ArrayList<Empleado> empleadosAtendiendo=new ArrayList<Empleado>();
	private ControladorNotificaciones cn = new ControladorNotificaciones();
	
	public GestorColas(Servidor servidor) {
		this.servidor=servidor;
	}
	
	public void registrarCliente(Cliente cliente) throws DniYaRegistradoException{
		if (!clientesEnEspera.contains(cliente)) {
			this.clientesEnEspera.add(cliente);
			matchClienteEmpleado();
		}
		else {
			throw new DniYaRegistradoException(Constantes.DNI_YA_REGISTRADO);
		}
	}
	
	public void registrarEmpleado(Empleado empleado) throws BoxYaRegistradoException {
		if (!empleadosNoAtendiendo.contains(empleado) && !empleadosAtendiendo.contains(empleado)) {
			this.empleadosNoAtendiendo.add(empleado);
		}
		else {
			throw new BoxYaRegistradoException(Constantes.BOX_YA_REGISTRADO);
		}
	}

	private void matchClienteEmpleado() {
		System.out.println("Intentando matchear cliente empleado");
		if (this.clientesEnEspera.isEmpty()) {
			Empleado empleado = getEmpleadoDisponible();
			if (empleado!= null) {
				this.empleadosAtendiendo.add(empleado);
				Cliente cliente = this.clientesEnEspera.poll();
				enviarClienteAEmpleado(empleado, cliente);
				System.out.println(empleado+""+cliente);
				//this.servidor.informarAdministradores(empleado);
				//enviarANotificaciones(empleado,cliente);				
			}
		}		
		
	}
	
	
	private Empleado getEmpleadoDisponible() {
		Empleado empleado = null;
				
		while (!this.empleadosNoAtendiendo.isEmpty()) {
			Empleado aux = this.empleadosNoAtendiendo.poll();
			Queue<Empleado> auxQueue=new LinkedList<Empleado>();
			if (aux.getEstado()==EstadoEmpleado.Disponible) {
				empleado=aux;
				break;
			}
			else {
				auxQueue.add(aux);				
			}
			
			while (!this.empleadosNoAtendiendo.isEmpty()) {
				auxQueue.add(this.empleadosNoAtendiendo.poll());	
			}
			while (!auxQueue.isEmpty()) {
				this.empleadosNoAtendiendo.add(auxQueue.poll());
			}			
		}		
		return empleado;
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
	public void enviarClienteAEmpleado(Empleado empleado, Cliente cliente) {
		// TODO Auto-generated method stub	
	}

	public void cambioEstado(Empleado empleado) {
		if (this.empleadosAtendiendo.contains(empleado)) {
			this.empleadosAtendiendo.remove(empleado);
			this.empleadosNoAtendiendo.add(empleado);
		}
		else {
			Queue<Empleado> auxQueue=new LinkedList<Empleado>();
			while (!this.empleadosNoAtendiendo.isEmpty()) {
				Empleado aux = this.empleadosNoAtendiendo.poll();
				if (aux.equals(empleado)) {
					auxQueue.add(empleado);
					break;
				}
				else {
					auxQueue.add(aux);				
				}			
			}						
			while (!this.empleadosNoAtendiendo.isEmpty()) {
				auxQueue.add(this.empleadosNoAtendiendo.poll());	
			}
			while (!auxQueue.isEmpty()) {
				this.empleadosNoAtendiendo.add(auxQueue.poll());
			}
			if(empleado.getEstado()==EstadoEmpleado.Disponible) {
				matchClienteEmpleado();
			}
		}
		
	}

	public Object actualizarMetricas() {
		return null;
	}
	
	
}
