package servidor;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import cliente.Cliente;
import persistencia.ILectoEscritura;
import util.Constantes;

public class OrdenPorGrupoAfinidad implements IStrategyOrdenAtencion{

	@Override
	public Cliente ordenClientes(ArrayList<Cliente> clientesEnEspera,ILectoEscritura archivo) {
		String grupoAfinidad;
		Iterator<Cliente> iteratorClientes=clientesEnEspera.iterator();
		Cliente cliente,clienteMejor=null;
		int nroPrioridad;
		int nroPrioridadMejor=-1; 
		//segunda comparacion while se suma 1 ya que la cantidad grupo no tiene en cuenta al que no esta en sistema
		while(iteratorClientes.hasNext() && nroPrioridadMejor<Constantes.MAX_GRUPO_AFINIDAD+1 ) {
			cliente=iteratorClientes.next();
			
			grupoAfinidad=archivo.buscarGrupo(cliente.getDni());
			System.out.println("Datos obtenidos:"+ grupoAfinidad);
			
			nroPrioridad=Constantes.GRUPO_AFINIDAD.indexOf(grupoAfinidad);
			
			if(nroPrioridad>nroPrioridadMejor) {
				nroPrioridadMejor=nroPrioridad;
				clienteMejor=cliente;
			}
			if (nroPrioridad == Constantes.GRUPO_AFINIDAD.size())
				break;
		}		
		
		return clienteMejor;
	}
	

}
