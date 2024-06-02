package servidor;

import java.io.File;
import java.util.ArrayList;
import java.util.Queue;

import cliente.Cliente;
import persistencia.ILectoEscritura;

public interface IOrdenAtencion {
	public Cliente ordenClientes(ArrayList<Cliente> clientesEnEspera,ILectoEscritura archivo);
}
