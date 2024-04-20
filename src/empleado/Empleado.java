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
	
	public Empleado(String nombre,int box, EstadoEmpleado estado) {
		this.nombre=nombre;
		this.box=box;
		this.estado=estado;
	}
	
	public Empleado(String nombre,int box, EstadoEmpleado estado, Cliente cliente) {
		this.nombre=nombre;
		this.box=box;
		this.estado=estado;
		this.cliente=cliente;
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
	public void atenderCliente(Cliente cliente) {
		this.cambioEstado(EstadoEmpleado.Atendiendo);
		this.cliente=cliente;	
	}
	public void quitarCliente() {
		this.cliente=null;
	}
	public String toString() {
		return ("Box: "+ this.box +", Estado: "+ this.estado+", Cliente: "+cliente);
	}
}
