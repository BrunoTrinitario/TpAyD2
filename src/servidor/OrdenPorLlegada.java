package servidor;

import java.io.File;
import java.util.ArrayList;

import cliente.Cliente;

public class OrdenPorLlegada implements IStrategyOrdenAtencion {

	@Override
	public ArrayList<Cliente> ordenClientes(Cliente cliente,ArrayList<Cliente> listaClientes,String tipoArchivo) {
		int pos=0;
		int nroPrioridad; 
		String datosCliente=null;
		// invoco datosCliente=metodo(cliente.getDni()) que recorre arch 
		//y devuelve nulo si no estaba o fecha nacimiento grupo afinidad 
		
		if(datosCliente==null) { // si no esta lo mando al final del archivo
			nroPrioridad=2;
			//cliente.setNroPrioridad(nroPrioridad);
			listaClientes.add(cliente);
		}
		else { // lo mando al final de los que si estan en arch
			nroPrioridad=1;
			//cliente.setNroPrioridad(nroPrioridad);
			/*
			while (pos < listaClientes.size() && listaClientes.get(pos).getNroPrioridad() <= nroPrioridad) {
	            pos++;
			}
			*/
			listaClientes.add(pos, cliente);
		}
		
		
		return listaClientes;
	}

}
