package empleado;

import java.io.Serializable;
import java.util.Objects;

import cliente.Cliente;
import util.EstadoEmpleado;

@SuppressWarnings("serial")
public class Empleado implements Serializable {
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
		this.nombre="asd";
	}
	public boolean equals(Object obj) {
		 if (this == obj)
	            return true;
	        if (obj == null || getClass() != obj.getClass())
	            return false;
	        Empleado empleado = (Empleado) obj;
	        return Objects.equals(box, empleado.box);
	}
	public void atenderCliente(Cliente cliente) {
		this.cambioEstado(EstadoEmpleado.Atendiendo);
		this.cliente=cliente;	
	}
	
	public String toString() {
		return ("nombre: "+this.nombre + "Box: "+ this.box +", Estado: "+ this.estado);
	}
}
