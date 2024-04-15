package empleado;

import cliente.Cliente;

public class Empleado {
	private int box;
	private boolean estado;
	private String nombre;
	private Cliente cliente;
	public int getBox() {
		return box;
	}
	public boolean isEstado() {
		return estado;
	}
	public String getNombre() {
		return nombre;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void cambioEstado() {
		if (this.estado) {
			this.estado=false;
		}else {
			this.estado=true;
		}
	}
}
