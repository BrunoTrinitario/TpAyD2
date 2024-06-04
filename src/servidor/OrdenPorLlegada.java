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
		int nroPrioridad;
		int nroPrioridadMejor=0; //valor para que siempre agarre el primero
		Cliente cliente,clienteMejor=null;
		clienteMejor=clientesEnEspera.get(0);
		while(iteratorClientes.hasNext() && nroPrioridadMejor<2 ) {
			cliente=iteratorClientes.next();
			datosCliente=archivo.buscar(cliente.getDni());
			//y devuelve nulo si no estaba o fecha nacimiento grupo afinidad 
			if(datosCliente.isEmpty()) // si no esta peor prioridad=1
			{
				nroPrioridad=1;
			}
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
