package servidor;

import java.util.ArrayList;

import empleado.Empleado;

public interface iMetrica {
	int cantidadEnEspera();
	int cantidadAtendidos();
	String promedioEspera();
	ArrayList<Empleado> getListaEmpleados();
}
