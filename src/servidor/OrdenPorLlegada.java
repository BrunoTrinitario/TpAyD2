package servidor;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import cliente.Cliente;
import persistencia.ILectoEscritura;

public class OrdenPorLlegada implements IOrdenAtencion {

	@Override
	public Cliente ordenClientes(ArrayList<Cliente> clientesEnEspera,ILectoEscritura archivo) {
		ArrayList<String> datosCliente=null;
		Iterator<Cliente> iteratorClientes=clientesEnEspera.iterator();
		Cliente cliente,clienteMejor=null;
		int nroPrioridad;
		int nroPrioridadMejor=0; //valor para que siempre agarre el primero
		while(iteratorClientes.hasNext() && nroPrioridadMejor<2 ) {
			cliente=iteratorClientes.next();
			datosCliente=archivo.buscar(cliente.getDni());
			System.out.println("Datos obtenidos:"+datosCliente );
			//y devuelve nulo si no estaba o fecha nacimiento grupo afinidad 
			if(datosCliente==null) // si no esta peor prioridad=2
				nroPrioridad=1;
			else
				nroPrioridad=2;
			if(nroPrioridad>nroPrioridadMejor) {
				nroPrioridadMejor=nroPrioridad;
				clienteMejor=cliente;
			}
		}
		return clienteMejor;
	}

}
