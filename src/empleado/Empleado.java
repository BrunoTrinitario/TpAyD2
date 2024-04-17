package empleado;

import java.util.Objects;

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
	public boolean equals(Object obj) {
		 if (this == obj)
	            return true;
	        if (obj == null || getClass() != obj.getClass())
	            return false;
	        Empleado empleado = (Empleado) obj;
	        return Objects.equals(box, empleado.box);
	}
}
