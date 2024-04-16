package empleado;

import cliente.Cliente;
import util.EstadoEmpleado;

public class Empleado {
	private int box;
	private EstadoEmpleado estado;
	private String nombre;
	private Cliente cliente;
	
	public Empleado(String nombre,int box) {
		this.nombre=nombre;
		this.box=box;
		this.estado=EstadoEmpleado.NoDisponible;
	}
	public int getBox() {
		return box;
	}
	public EstadoEmpleado getEstado() {
		return this.estado;
	}
	public String getNombre() {
		return nombre;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void cambioEstado(EstadoEmpleado estado) {
		this.estado=estado;
	}
}
