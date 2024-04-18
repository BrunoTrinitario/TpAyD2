package controlador;

import cliente.Cliente;

public interface IVista {
	void agregarCliente(Cliente cliente);
	void eliminarCliente(Cliente cliente);
	void moverCliente(Cliente cliente);
	void marcarCliente(Cliente cliente);
}
