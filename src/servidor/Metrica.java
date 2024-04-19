package servidor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cliente.Cliente;
import empleado.Empleado;

public class Metrica implements iMetrica {
	private ArrayList<Empleado> empleados=new ArrayList<Empleado>();
	private ArrayList<Cliente> clientesAtendidos=new ArrayList<Cliente>();
	private Queue<Cliente> clientesEnEspera=new LinkedList<Cliente>();
	
	public Metrica(ArrayList<Empleado> empleados,ArrayList<Cliente> clientesAtendidos,Queue<Cliente> clientesEnEspera) {
		this.empleados=empleados;
		this.clientesAtendidos=clientesAtendidos;
		this.clientesEnEspera=clientesEnEspera;
	}
	public int cantidadEnEspera() {
		return clientesEnEspera.size();
	}
	public int cantidadAtendidos() {
		return clientesAtendidos.size();
	}
	public String promedioEspera() {
		int auxHora=0,auxMinutos=0;
		int i=0;
		String aux="";
		while (i<clientesAtendidos.size()) {
			auxHora+=clientesAtendidos.get(i).getHoraEgreso().getHour()-clientesAtendidos.get(i).getHoraIngreso().getHour();
			auxMinutos+=clientesAtendidos.get(i).getHoraEgreso().getMinute()-clientesAtendidos.get(i).getHoraIngreso().getMinute();
			i++;
		}
		i=clientesAtendidos.size();
		auxHora/=i;
		auxMinutos/=i;
		aux=auxHora+":"+auxMinutos;
		return aux;
	}
	public ArrayList<Empleado> getListaEmpleados(){
		return this.empleados;
	}
	
}
