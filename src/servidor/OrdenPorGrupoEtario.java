package servidor;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import cliente.Cliente;
import persistencia.ILectoEscritura;
import util.Constantes;

public class OrdenPorGrupoEtario implements IStrategyOrdenAtencion{
	
	@Override
	public Cliente ordenClientes(Queue<Cliente> clientesEnEspera,ILectoEscritura archivo) {
		String fechaNacimiento=null;
		Iterator<Cliente> iteratorClientes=clientesEnEspera.iterator();
		Cliente cliente,clienteMejor=null;
		int nroPrioridad,edad;
		int nroPrioridadMejor=999; //valor para que siempre agarre el primero
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1;
		LocalDate date2 ;
	
		while(iteratorClientes.hasNext() || nroPrioridadMejor>1 ) {
			cliente=iteratorClientes.next();
			fechaNacimiento=archivo.buscaFecha(cliente.getDni());
			if(fechaNacimiento==null) {
				nroPrioridad=4;	
			}
			else {
				date1 = LocalDate.parse(fechaNacimiento, formatter);
				date2 = LocalDate.now(); 
				edad = Period.between(date1, date2).getYears();
				if(edad<=Constantes.MAX_EDAD_JOVEN) {
					nroPrioridad=3;
				}
				else {
					if(edad<=Constantes.MAX_EDAD_ADULTO) {
						nroPrioridad=2;
					}
					else { //grupo adulto mayor
						nroPrioridad=1;
					}
				}
			}
			if(nroPrioridad<nroPrioridadMejor) {
				nroPrioridadMejor=nroPrioridad;
				clienteMejor=cliente;
			}
		}
		
	
		return clienteMejor;
	}

}
