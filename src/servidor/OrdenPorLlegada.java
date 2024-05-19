package servidor;

import java.io.File;
import java.util.ArrayList;

import cliente.Cliente;

public class OrdenPorLlegada implements IStrategyOrdenAtencion {

	@Override
	public int ordenClientes(Cliente cliente,String tipoArchivo) {
		int nroPrioridad; 
		String datosCliente=null;
		// invoco datosCliente=metodo(cliente.getDni()) que recorre arch 
		//y devuelve nulo si no estaba o fecha nacimiento grupo afinidad 
		
		if(datosCliente==null) { // si no esta peor prioridad=2
			nroPrioridad=2;
		}
		else { 
			nroPrioridad=1;
		}
		return nroPrioridad;
	}

}
