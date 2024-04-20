package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import cliente.Cliente;
import empleado.Empleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.Constantes;
import util.DatosConexion;
import util.EstadoEmpleado;

public class Servidor extends Thread {
	private GestorColas gestorcolas = new GestorColas(this);
	private HashMap<Integer, DatosConexion> empleadosConectados = new HashMap<>();
	private boolean servidorActivo = true;

	@Override
	public void run() {
		try {
			ServerSocket s = new ServerSocket(Constantes.PUERTO);
			System.out.println("Servidor online");
			while (servidorActivo) {

				DatosConexion datosConexion = new DatosConexion(s.accept());
				Object objeto = datosConexion.ois.readObject();
				String msg = datosConexion.in.readLine();
				System.out.println("Datos recibidos: " + objeto + " " + msg);

				if (objeto instanceof Cliente) {
					try {
						this.gestorcolas.registrarCliente((Cliente) objeto);
						datosConexion.out.println(Constantes.CLIENTE_REGISTRO_OK);
					} catch (DniYaRegistradoException e) {
						datosConexion.out.println(e.getMessage());
					}
				} else if (objeto instanceof Empleado) {
					Empleado empleado = (Empleado) objeto;
					if (msg.equals(Constantes.EMPLEADO_NUEVO)) {
						try {
							this.gestorcolas.registrarEmpleado((Empleado) objeto);
							this.empleadosConectados.put(empleado.getBox(), datosConexion);
							datosConexion.out.println(Constantes.EMPLEADO_REGISTRO_OK);
							escucharEmpleado(datosConexion);
						} catch (BoxYaRegistradoException e) {
							datosConexion.out.println(e.getMessage());
						}
					}
				} else {
					if (msg.equals(Constantes.SOLICITAR_METRICAS)) {
						Object aux = gestorcolas.actualizarMetricas();
						System.out.println("Intentando enviar " + aux);
						System.out.println("Metricas enviadas");
						datosConexion.out.println(Constantes.METRICAS_CREACION_OK);
						datosConexion.oos.writeObject(aux);
					}
				}
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void enviarClienteAEmpleado(Empleado empleado, Cliente cliente) {
		try {
			empleadosConectados.get(empleado.getBox()).oos.writeObject(cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void escucharEmpleado(DatosConexion datosConexion) {
		new Thread() {
			public void run() {
				try {
					while (datosConexion.socket.isConnected()) {
						Object object = datosConexion.ois.readObject();
						Empleado empleado = (Empleado) object;
						String msg = datosConexion.in.readLine();
						System.out.println("Datos recibidos: " + empleado);
						if (msg.equals(Constantes.CLIENTE_AUSENTE)) {
							gestorcolas.clienteNoPresentado(empleado);
						}
						if (msg.equals(Constantes.ATENCION_FINALIZADA)) {
							gestorcolas.finalizarAtencion(empleado);
						}
						if (msg.equals(Constantes.EMPLEADO_CAMBIO_ESTADO)) {
							System.out.println("Solicitud de cambio de estado a " + msg);
							gestorcolas.cambioEstado(empleado);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	public void informarEmpleado(Empleado empleado, Cliente cliente) {
		DatosConexion aux = this.empleadosConectados.get(empleado.getBox());
		try {
			aux.oos.writeObject(cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
