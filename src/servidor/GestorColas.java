package servidor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cliente.Cliente;
import empleado.Empleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.Constantes;
import util.EstadoEmpleado;
import util.GestorColasDTO;

public class GestorColas implements IClienteEmpleado {

	private Servidor servidor;
	private Queue<Cliente> clientesEnEspera=new LinkedList<Cliente>();
	//emplados disponibles y no disponibles
	private ArrayList<Empleado> empleadosNoAtendiendo=new ArrayList<Empleado>();
	//empleados que SOLO ANTIENDEN EN UN ISNTANTE DADO
	private ArrayList<Empleado> empleadosAtendiendo=new ArrayList<Empleado>();
	private ArrayList<Cliente> clientesAtendidos=new ArrayList<Cliente>();
	
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
		if (!this.clientesEnEspera.isEmpty()) {
			Empleado empleado = getEmpleadoDisponible();
			if (empleado!= null) {
				this.empleadosNoAtendiendo.remove(empleado);
				this.empleadosAtendiendo.add(empleado);
				Cliente cliente = this.clientesEnEspera.poll();
				empleado.atenderCliente(cliente);
				cliente.setHoraAtencion();
				enviarClienteAEmpleado(empleado, cliente);
				servidor.informarNotificaciones(empleado);
			}
		}
	}
	
	
	private Empleado getEmpleadoDisponible() {
		Empleado empleado = null;
				
		for (Empleado aux : empleadosNoAtendiendo) {
		    if (aux.getEstado().equals(EstadoEmpleado.Disponible)) {
		    		empleado=aux;
		    	break;
		    }	
		}
		return empleado;
	}

	@Override
	public void clienteNoPresentado(Empleado empleado) {
		Cliente cliente = empleado.getCliente();
		for(Empleado aux:empleadosAtendiendo) {
			if(aux.equals(empleado)) {
				aux.quitarCliente();
				break;
			}
		}
		if (cliente.getIntento()<2) {
			cliente.sumarIntento();
			this.clientesEnEspera.add(cliente);
			matchClienteEmpleado();
		}
		this.cambioEstadoEmpleado(empleado);
	}

	@Override
	public void eliminaCliente(Cliente cliente) {
		this.clientesEnEspera.remove(cliente);
	}

	@Override
	public void enviarClienteAEmpleado(Empleado empleado, Cliente cliente) {
		servidor.informarEmpleado(empleado, cliente);	
	}

	public void cambioEstadoEmpleado(Empleado empleado) {
		if (this.empleadosAtendiendo.contains(empleado)) {
			this.empleadosAtendiendo.remove(empleado);
			this.empleadosNoAtendiendo.add(empleado);
		}
		else {
			for (Empleado aux : empleadosNoAtendiendo) {
			    if (aux.equals(empleado)) {
			    	aux.cambioEstado(empleado.getEstado()); 
			    	if (empleado.getEstado().equals(EstadoEmpleado.Disponible)) {
			    		matchClienteEmpleado();
			    	}
			    	break;
			    }	
			}
		}
	}
	public void finalizarAtencion(Empleado empleado) {
		for(Empleado aux:empleadosAtendiendo) {
			if(aux.equals(empleado)) {				
				aux.quitarCliente();
				break;
			}
		}
		this.clientesAtendidos.add(empleado.getCliente());
		this.cambioEstadoEmpleado(empleado);
	}
	public Metrica actualizarMetricas() {
		ArrayList<Empleado> aux=new ArrayList<Empleado>();
		aux.addAll(empleadosNoAtendiendo);
		aux.addAll(empleadosAtendiendo);
		return new Metrica(aux,clientesAtendidos,clientesEnEspera);
	}

	public void desconectarEmpleado(Empleado empleado) {
		this.empleadosAtendiendo.remove(empleado);
		this.empleadosNoAtendiendo.remove(empleado);
	}
	public void gestorColasDTO() {
		GestorColasDTO dto=new GestorColasDTO(clientesAtendidos, clientesEnEspera, empleadosAtendiendo, empleadosAtendiendo);
		System.out.println("invocando metodo resincronizar pasivos");
		servidor.resincronizarServidoresPasivos(dto);
	}
	
	public void reesincronizar(GestorColasDTO dto2) {
		System.out.println("Sincronizando desde: "+dto2);
		this.clientesAtendidos=dto2.getClientesAtendidos();
		this.clientesEnEspera=dto2.getClientesEnEspera();
		this.empleadosAtendiendo=dto2.getEmpleadosAtendiendo();
		this.empleadosNoAtendiendo=dto2.getEmpleadosNoAtendiendo();
		System.out.println("db resincronizada: "+this);
	}
	@Override
	public String toString() {
		return "GestorColas [servidor=" + servidor + ", clientesEnEspera=" + clientesEnEspera
				+ ", empleadosNoAtendiendo=" + empleadosNoAtendiendo + ", empleadosAtendiendo=" + empleadosAtendiendo
				+ ", clientesAtendidos=" + clientesAtendidos + "]";
	}

	
}

