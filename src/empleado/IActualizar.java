package empleado;

import cliente.Cliente;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.EstadoEmpleado;

public interface IActualizar {
	void informaEstado(Empleado empleado);
	void clienteAusente() throws DniYaRegistradoException;
	void crearEmpleado(String nombre,int box) throws BoxYaRegistradoException;
	void informarAcceso(Empleado empleado) throws BoxYaRegistradoException;
	void cambioEstado(EstadoEmpleado estado);
	void finalizarAtencion();

}
