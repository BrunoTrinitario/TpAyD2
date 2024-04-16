package servidor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cliente.Cliente;
import empleado.Empleado;

public class GestorColas implements IClienteEmpleado {
	private Queue<Cliente> ClientesEnEspera=new LinkedList<Cliente>();
	private ArrayList<Empleado> EmpleadosNoDisponibles=new ArrayList<Empleado>();
	private ArrayList<Empleado> EmpleadosDisponibles=new ArrayList<Empleado>();
	private ArrayList<Empleado> EmpleadosAtendiendo=new ArrayList<Empleado>();
	
	@Override
	public void registrarCliente(Cliente cliente) {
		this.ClientesEnEspera.add(cliente);
	}

	@Override
	public void agregarEmpleadoANoDisponible(Empleado empleado) {
		this.EmpleadosNoDisponibles.add(empleado);
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
			this.ClientesEnEspera.add(cliente);
		}
	}

	@Override
	public void eliminaCliente(Cliente cliente) {
		this.ClientesEnEspera.remove(cliente);
	}

	@Override
	public void enviarClienteAEmpleado(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}
	
	
}
