package persistencia;

import java.util.ArrayList;

import cliente.Cliente;

public interface ILectoEscritura {
	public void guardar(Cliente cliente,String accion);
	public String buscaFecha(String dni);
	public String buscarGrupo(String dni);
	public ArrayList<String> buscar(String dni);
}
