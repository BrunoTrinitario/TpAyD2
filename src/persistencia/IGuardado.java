package persistencia;

import java.util.ArrayList;

import cliente.Cliente;

public interface IGuardado {
	public void guardar(Cliente cliente,String accion);
}
