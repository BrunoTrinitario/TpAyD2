package cliente;

public interface iActualizar {
	void informaEstado(Empleado empleado,boolean estado);
	void cambioEstado();
	void finalizarAtencion();
	void clienteAtendido(Cliente cliente,Empleado empleado);
	void clienteAusente(Cliente cliente,Empleado empleado);
	void crearEmpleado(String nombre,int box);
	void informarAcceso(Empleado empleado);
}
