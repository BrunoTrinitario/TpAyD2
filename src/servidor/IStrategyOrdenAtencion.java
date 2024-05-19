package servidor;

import java.io.File;
import java.util.ArrayList;

import cliente.Cliente;

public interface IStrategyOrdenAtencion {
	public ArrayList<Cliente> ordenClientes(Cliente cliente,ArrayList<Cliente> listaClientes,String tipoArchivo);
	
}
