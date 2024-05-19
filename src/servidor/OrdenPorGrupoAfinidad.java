package servidor;

import java.io.File;
import java.util.ArrayList;

import cliente.Cliente;

public class OrdenPorGrupoAfinidad implements IStrategyOrdenAtencion{

	@Override
	public int ordenClientes(Cliente cliente,String tipoArchivo) {
		int nroPrioridad=0;
		// invoco metodo(cliente.getDni()) que recorre arch 
		//y devuelve nulo si no estaba o grupo afinidad fecha nacimiento 
		
		return nroPrioridad;
	}
	

}
