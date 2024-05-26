package servidor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cliente.Cliente;
import empleado.Empleado;

public class Metrica implements iMetrica, Serializable {
	private ArrayList<Empleado> empleados=new ArrayList<Empleado>();
	private ArrayList<Cliente> clientesAtendidos=new ArrayList<Cliente>();
	private ArrayList<Cliente> clientesEnEspera=new ArrayList<Cliente>();
	
	public Metrica(ArrayList<Empleado> empleados,ArrayList<Cliente> clientesAtendidos,ArrayList<Cliente> clientesEnEspera) {
		this.empleados=empleados;
		this.clientesAtendidos=clientesAtendidos;
		this.clientesEnEspera=clientesEnEspera;
	}

	public int cantidadEnEspera() {
		int cantidad=0;
		Iterator<Cliente> iteradorClientesEnEspera=this.clientesEnEspera.iterator();
		while (iteradorClientesEnEspera.hasNext()) {	
			iteradorClientesEnEspera.next();
	        cantidad++;
	     }
		return cantidad;
	}
	public int cantidadAtendidos() {
		int cantidad=0;
		Iterator<Cliente> iteradorClientesAtendidos=this.clientesAtendidos.iterator();
		while (iteradorClientesAtendidos.hasNext()) {	
			iteradorClientesAtendidos.next();
	        cantidad++;
	     }
		return cantidad;
	}

	public String promedioEspera() {
		int auxHora = 0, auxMinutos = 0, auxSegundos = 0;
		int i = 0;
		String aux = "";
		Iterator<Cliente> iteradorClientesAtendidos=this.clientesAtendidos.iterator();
		Cliente clienteAtendido;
		while (iteradorClientesAtendidos.hasNext()) {
			clienteAtendido=iteradorClientesAtendidos.next();
			auxHora += Math.abs(clienteAtendido.getHoraEgreso().getHour()
					- clienteAtendido.getHoraIngreso().getHour());
			auxMinutos += Math.abs(clienteAtendido.getHoraEgreso().getMinute()
					- clienteAtendido.getHoraIngreso().getMinute());
			auxSegundos += Math.abs(clienteAtendido.getHoraEgreso().getSecond()
					- clienteAtendido.getHoraIngreso().getSecond());
			i++;
		}
		if (i > 0) {
			auxHora /= i;
			auxMinutos /= i;
			auxSegundos /= i;
			aux = auxHora + ":" + auxMinutos + ":" + auxSegundos;
		} else
			aux = "0:0:0";
		return aux;
	}
	public ArrayList<Empleado> getListaEmpleados(){
		return this.empleados;
	}
	/*
	public int cantidadEnEspera() {
		return clientesEnEspera.size();
	}
	public int cantidadAtendidos() {
		return clientesAtendidos.size();
	}

	public String promedioEspera() {
		int auxHora = 0, auxMinutos = 0, auxSegundos = 0;
		int i = 0;
		String aux = "";
		while (i < clientesAtendidos.size()) {
			auxHora += Math.abs(clientesAtendidos.get(i).getHoraEgreso().getHour()
					- clientesAtendidos.get(i).getHoraIngreso().getHour());
			auxMinutos += Math.abs(clientesAtendidos.get(i).getHoraEgreso().getMinute()
					- clientesAtendidos.get(i).getHoraIngreso().getMinute());
			auxSegundos += Math.abs(clientesAtendidos.get(i).getHoraEgreso().getSecond()
					- clientesAtendidos.get(i).getHoraIngreso().getSecond());
			i++;
		}
		if (i > 0) {
			auxHora /= i;
			auxMinutos /= i;
			auxSegundos /= i;
			aux = auxHora + ":" + auxMinutos + ":" + auxSegundos;
		} else
			aux = "0:0:0";
		return aux;
	}
	public ArrayList<Empleado> getListaEmpleados(){
		return this.empleados;
	}
	*/
	
}
