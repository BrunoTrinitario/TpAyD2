package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cliente.Cliente;
import empleado.Empleado;

public class GestorColasDTO implements Serializable {
	
	
	public Queue<Cliente> clientesEnEspera=new LinkedList<>();
	public ArrayList<Empleado> empleadosNoAtendiendo=new ArrayList<>();
	public ArrayList<Empleado> empleadosAtendiendo=new ArrayList<>();
	public ArrayList<Cliente> clientesAtendidos=new ArrayList<>();
	
	public GestorColasDTO(ArrayList<Cliente> clientesAtendidos2, Queue<Cliente> clientesEnEspera2,
			ArrayList<Empleado> empleadosAtendiendo2, ArrayList<Empleado> empleadosNoAtendiendo2) {
		for (Empleado i : empleadosNoAtendiendo2) {
			Empleado empleado = new Empleado(i.getNombre(), i.getBox(), i.getEstado(), i.getCliente());
			this.empleadosNoAtendiendo.add(empleado);
		}
		for (Empleado i : empleadosAtendiendo2) {
			Empleado empleado = new Empleado(i.getNombre(), i.getBox(), i.getEstado(), i.getCliente());
			this.empleadosAtendiendo.add(empleado);
		}
		for (Cliente i : clientesAtendidos2) {
			Cliente cliente = new Cliente(i.getDni(),i.getHoraIngreso(),i.getHoraEgreso(),i.getIntento());
			this.clientesAtendidos.add(cliente);
		}
		for (Cliente i : clientesEnEspera2) {
			Cliente cliente = new Cliente(i.getDni(),i.getHoraIngreso(),i.getHoraEgreso(),i.getIntento());
			this.clientesEnEspera.add(cliente);
		}
	}
	@Override
	public String toString() {
		return "GestorColasDTO [clientesEnEspera=" + clientesEnEspera + ", empleadosNoAtendiendo="
				+ empleadosNoAtendiendo + ", empleadosAtendiendo=" + empleadosAtendiendo + ", clientesAtendidos="
				+ clientesAtendidos + "]";
	}

	
	public Queue<Cliente> getClientesEnEspera() {
		return clientesEnEspera;
	}
	public void setClientesEnEspera(Queue<Cliente> clientesEnEspera) {
		this.clientesEnEspera = clientesEnEspera;
	}
	public ArrayList<Empleado> getEmpleadosNoAtendiendo() {
		return empleadosNoAtendiendo;
	}
	public void setEmpleadosNoAtendiendo(ArrayList<Empleado> empleadosNoAtendiendo) {
		this.empleadosNoAtendiendo = empleadosNoAtendiendo;
	}
	public ArrayList<Empleado> getEmpleadosAtendiendo() {
		return empleadosAtendiendo;
	}
	public void setEmpleadosAtendiendo(ArrayList<Empleado> empleadosAtendiendo) {
		this.empleadosAtendiendo = empleadosAtendiendo;
	}
	public ArrayList<Cliente> getClientesAtendidos() {
		return clientesAtendidos;
	}
	public void setClientesAtendidos(ArrayList<Cliente> clientesAtendidos) {
		this.clientesAtendidos = clientesAtendidos;
	}

}
