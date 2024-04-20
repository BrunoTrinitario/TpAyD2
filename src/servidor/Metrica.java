package servidor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import cliente.Cliente;
import empleado.Empleado;

public class Metrica implements iMetrica, Serializable {
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
		int auxHora=0,auxMinutos=0, auxSegundos=0;
		int i=0;
		String aux="";
		while (i<clientesAtendidos.size()) {
			auxHora+=clientesAtendidos.get(i).getHoraEgreso().getHour()-clientesAtendidos.get(i).getHoraIngreso().getHour();
			auxMinutos+=clientesAtendidos.get(i).getHoraEgreso().getMinute()-clientesAtendidos.get(i).getHoraIngreso().getMinute();
			auxSegundos+=clientesAtendidos.get(i).getHoraEgreso().getSecond()-clientesAtendidos.get(i).getHoraIngreso().getSecond();
			i++;
		}
		if (i>0) {
		auxHora/=i;
		auxMinutos/=i;
		auxSegundos/=i;
		aux=auxHora+":"+auxMinutos+":"+auxSegundos;
		}else
			aux="0";
		return aux;
	}
	public ArrayList<Empleado> getListaEmpleados(){
		return this.empleados;
	}
	
}
