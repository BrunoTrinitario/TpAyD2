package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cliente.Cliente;
import empleado.Empleado;

public class GestorColasDTO implements Serializable {
	@Override
	public String toString() {
		return "GestorColasDTO [clientesEnEspera=" + clientesEnEspera + ", empleadosNoAtendiendo="
				+ empleadosNoAtendiendo + ", empleadosAtendiendo=" + empleadosAtendiendo + ", clientesAtendidos="
				+ clientesAtendidos + "]";
	}
	private Queue<Cliente> clientesEnEspera;
	private ArrayList<Empleado> empleadosNoAtendiendo;
	private ArrayList<Empleado> empleadosAtendiendo;
	private ArrayList<Cliente> clientesAtendidos;
	
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
