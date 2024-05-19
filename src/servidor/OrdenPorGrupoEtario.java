package servidor;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import cliente.Cliente;
import util.Constantes;

public class OrdenPorGrupoEtario implements IStrategyOrdenAtencion{
	
	@Override
	public int ordenClientes(Cliente cliente,String tipoArchivo) {
		int nroPrioridad,edad; 
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
	
		return nroPrioridad;
	}

}
