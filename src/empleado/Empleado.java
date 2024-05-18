package empleado;

import java.io.Serializable;
import java.util.Objects;

import cliente.Cliente;
import util.EstadoEmpleado;


public class Empleado implements Serializable {
	private int box;
	private IStateEmpleado estado;
	private String nombre;
	private Cliente cliente;
	
	public Empleado(String nombre,int box, IStateEmpleado estado) {
		this.nombre=nombre;
		this.box=box;
		this.estado=estado;
	}
	
	public Empleado(String nombre,int box, IStateEmpleado estado, Cliente cliente) {
		this.nombre=nombre;
		this.box=box;
		this.estado=estado;
		this.cliente=cliente;
	}
	
	public IStateEmpleado getEstadoDTO() {		
		return estado.getEstadoDTO();
	}
	
	public void cambioEstado(IStateEmpleado estado) {
		this.estado=estado;
	}

	public void atenderCliente(Cliente cliente) {
		this.estado.atenderCliente(this, cliente);
	}
	
	public void finalizarAtencion() {
		this.estado.finalizarAtencion(this);		
	}
	
	public void clienteAusente() {
		this.estado.clienteAusente(this);
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente=cliente;
	}
	
	public int getBox() {
		return box;
	}
	
	public IStateEmpleado getEstado() {
		return estado;
	}
	
	public String getNombre() {
		return nombre;
	}
	public Cliente getCliente() {
		return cliente;
	}

	
	public String toString() {
		return ("Box: "+ this.box +", Estado: "+ this.estado+", Cliente: "+cliente);
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
