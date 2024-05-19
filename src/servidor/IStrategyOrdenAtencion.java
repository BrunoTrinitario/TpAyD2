package servidor;

import java.io.File;
import java.util.ArrayList;

import cliente.Cliente;

public interface IStrategyOrdenAtencion {
	//ordena y asignaPrioridad a cliente
	public int ordenClientes(Cliente cliente,String tipoArchivo);
	
}
