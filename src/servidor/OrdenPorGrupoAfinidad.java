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
		int nroPrioridadMejor=0; 
		//segunda comparacion while se suma 1 ya que la cantidad grupo no tiene en cuenta al que no esta en sistema
		while(iteratorClientes.hasNext() || nroPrioridadMejor<Constantes.MAX_GRUPO_AFINIDAD+1 ) {
			cliente=iteratorClientes.next();
			grupoAfinidad=archivo.buscarGrupo(cliente.getDni()); 
			if(grupoAfinidad==null) {
				nroPrioridad=1;
			}
			else {
				nroPrioridad=0;
				while(!Constantes.GRUPO_AFINIDAD.get(nroPrioridad).equals(grupoAfinidad) || nroPrioridad<Constantes.MAX_GRUPO_AFINIDAD) {
					nroPrioridad++;
				}
				nroPrioridad++; //sumo devuelta ya que vector arranca en 0 
			}
			if(nroPrioridad>nroPrioridadMejor) {
				nroPrioridadMejor=nroPrioridad;
				clienteMejor=cliente;
			}
		}
		
		return clienteMejor;
	}
	

}
