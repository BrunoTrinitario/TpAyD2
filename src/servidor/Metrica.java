package servidor;

import java.util.ArrayList;

import cliente.Cliente;
import empleado.Empleado;

public class Metrica implements iMetrica {
	private ArrayList<Empleado> empleados=new ArrayList<Empleado>();
	private ArrayList<Cliente> clientesAtendidos=new ArrayList<Cliente>();
	//cliente tiene que tener el atributo tiempo de inicio y tiempo de atencion
	@Override
	public void consultarRendimiento() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buscaMetricas(ArrayList<Empleado> listaEmpleado, ArrayList<Metrica> listaMetricas) {
		// TODO Auto-generated method stub
		
	}
	
}
