package servidor;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import cliente.Cliente;
import persistencia.ILectoEscritura;

public class OrdenPorLlegada implements IStrategyOrdenAtencion {

	@Override
	public Cliente ordenClientes(Queue<Cliente> clientesEnEspera,ILectoEscritura archivo) {
		ArrayList<String> datosCliente=null;
		Iterator<Cliente> iteratorClientes=clientesEnEspera.iterator();
		Cliente cliente,clienteMejor=null;
		int nroPrioridad;
		int nroPrioridadMejor=999; //valor para que siempre agarre el primero
		while(iteratorClientes.hasNext() || nroPrioridadMejor>1 ) {
			cliente=iteratorClientes.next();
			datosCliente=archivo.buscar(cliente.getDni());
			//y devuelve nulo si no estaba o fecha nacimiento grupo afinidad 
			if(datosCliente==null) // si no esta peor prioridad=2
				nroPrioridad=2;
			else
				nroPrioridad=1;
			if(nroPrioridad<nroPrioridadMejor) {
				nroPrioridadMejor=nroPrioridad;
				clienteMejor=cliente;
			}
		}
		return clienteMejor;
	}

}
