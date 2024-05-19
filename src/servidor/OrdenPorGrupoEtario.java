package servidor;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import cliente.Cliente;
import util.Constantes;

public class OrdenPorGrupoEtario implements IStrategyOrdenAtencion{

	@Override
	public ArrayList<Cliente> ordenClientes(Cliente cliente,ArrayList<Cliente> listaClientes,String tipoArchivo) {
		int pos=0;
		int nroPrioridad; 
		String datosCliente=null;
		String fechaNacimiento=null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date1;
		LocalDate date2 ;
		// invoco datosCliente=metodo(cliente.getDni()) que recorre arch 
		//y devuelve nulo si no estaba o fecha nacimiento grupo afinidad
		//Los numeros de prioridad los defino asi: 
		//peor prioridad nro mas alto 
		// ya que el arreglo va de 0 a n y cuando se toma un cliente
		// en espera agarra el primero del arreglo
		if(datosCliente==null) {
			nroPrioridad=4;	
		}
		else {
			//corto string en fecha
			//fechaNacimiento=datosCliente[];
			date1 = LocalDate.parse(fechaNacimiento, formatter);
			date2 = LocalDate.parse(Constantes.FECHA_JOVEN, formatter); //definir constante
			if(date2.isAfter(date1)) {
				nroPrioridad=3;
			}
			else {
				date2 = LocalDate.parse(Constantes.FECHA_ADULTO, formatter); //definir constante
				if(date2.isAfter(date1)) {
					nroPrioridad=2;
				}
				else { //grupo adulto mayor
					nroPrioridad=1;
				}
			}
		}
		//cliente.setNroPrioridad(nroPrioridad);
		/*
		while (pos < listaClientes.size() && listaClientes.get(pos).getNroPrioridad() <= nroPrioridad) {
	            pos++;
	    }
	    */
		listaClientes.add(pos, cliente);
		return listaClientes;
	}

}
