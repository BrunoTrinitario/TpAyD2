package servidor;

import java.io.File;
import java.util.ArrayList;

import cliente.Cliente;

public class OrdenPorGrupoAfinidad implements IStrategyOrdenAtencion{

	@Override
	public ArrayList<Cliente> ordenClientes(Cliente cliente,ArrayList<Cliente> listaClientes,String tipoArchivo) {
		int pos=0;
		// invoco metodo(cliente.getDni()) que recorre arch 
		//y devuelve nulo si no estaba o grupo afinidad fecha nacimiento 
		
		/*
		while (pos < listaClientes.size() && listaClientes.get(pos).getNroPrioridad() <= nroPrioridad) {
            pos++;
		}
		*/
		listaClientes.add(pos, cliente);
		return listaClientes;
	}
	

}
