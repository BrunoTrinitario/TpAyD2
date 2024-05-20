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
	public Cliente ordenClientes(Queue<Cliente> clientesEnEspera,ILectoEscritura archivo) {
		String grupoAfinidad;
		Iterator<Cliente> iteratorClientes=clientesEnEspera.iterator();
		Cliente cliente,clienteMejor=null;
		int nroPrioridad;
		int nroPrioridadMejor=999; 
		while(iteratorClientes.hasNext() || nroPrioridadMejor>1 ) {
			cliente=iteratorClientes.next();
			grupoAfinidad=archivo.buscarGrupo(cliente.getDni()); 
			if(grupoAfinidad==null) {
				nroPrioridad=5;
			}
			else {
				if(grupoAfinidad.equals(Constantes.GRUPO_CLASSIC))
					nroPrioridad=4;
				else
					if(grupoAfinidad.equals(Constantes.GRUPO_GOLD))
						nroPrioridad=3;
					else
						if(grupoAfinidad.equals(Constantes.GRUPO_PLATINUM))
							nroPrioridad=2;
						else
							nroPrioridad=1;
			}
			if(nroPrioridad<nroPrioridadMejor) {
				nroPrioridadMejor=nroPrioridad;
				clienteMejor=cliente;
			}
		}
		
		return clienteMejor;
	}
	

}
