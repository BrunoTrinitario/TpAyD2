package util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cliente.Cliente;
import empleado.Empleado;

public class GestorColasDTO {
	private Queue<Cliente> clientesEnEspera=new LinkedList<Cliente>();
	private ArrayList<Empleado> empleadosNoAtendiendo=new ArrayList<Empleado>();
	private ArrayList<Empleado> empleadosAtendiendo=new ArrayList<Empleado>();
	private ArrayList<Cliente> clientesAtendidos=new ArrayList<Cliente>();
	
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
