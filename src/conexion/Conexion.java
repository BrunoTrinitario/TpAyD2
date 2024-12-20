package conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Random;

import cliente.Cliente;
import controlador.ControladorAdministrador;
import controlador.ControladorEmpleado;
import controlador.ControladorNotificaciones;
import empleado.Empleado;
import empleado.EmpleadoFacade;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import servidor.Metrica;
import servidor.Servidor;
import util.Constantes;
import util.GestorColasDTO;

@SuppressWarnings("deprecation")
public class Conexion extends Observable {

	private Socket socket;
	private ObjectOutputStream oos;
	private BufferedReader in;
	private PrintWriter out;
	private ObjectInputStream ois;
	private int puerto;

	public Conexion(ControladorEmpleado controlador) {
		this.addObserver(controlador);

	}

	public Conexion() {
	}

	public String envioEmpleadoAServidor(EmpleadoFacade negociosEmpleado, String mensaje)
			throws BoxYaRegistradoException, IOException {
		String msg = envioDatosAServidor(negociosEmpleado.getEmpleado(), mensaje);
		if (msg.equals(Constantes.BOX_YA_REGISTRADO)) {
			throw new BoxYaRegistradoException(Constantes.BOX_YA_REGISTRADO);
		}
		escucharServidorEmpleado(negociosEmpleado);
		return msg;
	}

	public String envioClienteAServidor(Object objeto, String mensaje) throws DniYaRegistradoException, IOException {
		String msg = envioDatosAServidor(objeto, mensaje);
		if (msg.equals(Constantes.DNI_YA_REGISTRADO)) {
			throw new DniYaRegistradoException(Constantes.DNI_YA_REGISTRADO);
		}
		cerrarConexion();
		return msg;
	}

	public void envioNotificacionesAServidor(ControladorNotificaciones cn, String mensaje) throws IOException {
		String msg = envioDatosAServidor(null, mensaje);
		this.escucharServidorNotificaciones(cn);
	}

	public void envioAdministrador(ControladorAdministrador ca, String mensaje) throws IOException {
		String msg = envioDatosAServidor(null, mensaje);
	}

	public void verificarServidorActivo(Servidor servidor, String mensaje) throws IOException {

		String msg = envioDatosAServidor(null, mensaje);
		this.escucharServidorServidor(servidor);

	}

	private String envioDatosAServidor(Object objeto, String mensaje) throws IOException {
		for (int i = 0; i < Constantes.INTENTO_CONEXION; i++) {
			for (int puerto : Constantes.PUERTOS) {
				try {
					abrirConexion(Constantes.IP, puerto);
					oos.writeObject(objeto);
					out.println(mensaje);
					this.puerto = puerto;
					return in.readLine();
				} catch (Exception e) {
				}
			}
			try {
				if (!mensaje.equals(Constantes.VERIFICAR_SERVIDOR_ACTIVO))
					Thread.sleep(Constantes.TIEMPO_REINTENTO);
			} catch (InterruptedException e) {
			}
		}

		throw new IOException();

	}

	public Metrica solicitudDeActulizacionMetricas(Object objeto, String mensaje) throws IOException {
		Object metrica = null;
		String msg = envioDatosAServidor(objeto, mensaje);
		try {
			metrica = this.ois.readObject();
		} catch (Exception e) {

		} finally {
			cerrarConexion();
		}
		return (Metrica) metrica;
	}

	private void abrirConexion(String Ip, int puerto) throws UnknownHostException, IOException {
		this.socket = new Socket(Ip, puerto);
		this.puerto = puerto;
		this.oos = new ObjectOutputStream(socket.getOutputStream());
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		;
		this.out = new PrintWriter(socket.getOutputStream(), true);
		this.ois = new ObjectInputStream(socket.getInputStream());
	}

	private void enviarDatos(Object objeto, String mensaje) throws IOException {
		oos.writeObject(objeto);
		out.println(mensaje);
	}

	private void escucharServidorEmpleado(EmpleadoFacade negociosEmpleado) {
		new Thread() {
			public void run() {
				try {
					while (socket.isConnected()) {
						Object objeto = ois.readObject();
						if (objeto instanceof Cliente) {
							negociosEmpleado.enviarClienteAEmpleado((Cliente) objeto);
						}
					}
				} catch (Exception e) {
					try {
						setChanged();
						notifyObservers(Constantes.RECONECTANDO);

						envioEmpleadoAServidor(negociosEmpleado, Constantes.REINTENTO_EMPLEADO);
						setChanged();
						notifyObservers(Constantes.PUERTOS.indexOf(puerto) + 1);
					} catch (IOException | BoxYaRegistradoException e1) {
						negociosEmpleado.conexionCaida();
					}

				}
			}
		}.start();
	}

	public void escucharServidorServidor(Servidor servidor) throws IOException {
		new Thread() {
			public void run() {
				Object objeto;
				try {
					while (socket.isConnected()) {
						objeto = ois.readObject();
						if (objeto instanceof GestorColasDTO) {
							servidor.resincronizacionDeEstado((GestorColasDTO) objeto);
						} else {
							if (objeto instanceof String) {
								if (objeto.equals(Constantes.INFORMAR_SERVIDOR_RESPALDO))
									servidor.isServidorRespaldo = true;
							}
						}
					}
				} catch (Exception e) {
					if (servidor.isServidorRespaldo)
						servidor.start();
					else {
						try {
							verificarServidorActivo(servidor, Constantes.VERIFICAR_SERVIDOR_ACTIVO);
						} catch (IOException e1) {
							Random random = new Random();
							int tiempoAleatorio = random.nextInt(2001);
							try {
								Thread.sleep(tiempoAleatorio);
							} catch (InterruptedException e2) {
								e2.printStackTrace();
							}
							try {
								verificarServidorActivo(servidor, Constantes.VERIFICAR_SERVIDOR_ACTIVO);
							} catch (IOException e2) {
								servidor.start();
							}
						}
					}
				}
			}
		}.start();
	}

	private void escucharServidorNotificaciones(ControladorNotificaciones controladorNotificaciones) {
		new Thread() {
			public void run() {
				try {
					while (socket.isConnected()) {
						Object objeto = ois.readObject();
						Empleado aux = (Empleado) objeto;
						controladorNotificaciones.agregarCliente(aux.getCliente(), aux);
					}
				} catch (Exception e) {
					try {
						envioNotificacionesAServidor(controladorNotificaciones, Constantes.REINTENTO_NOTIFICACION);
					} catch (IOException e1) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	private void cerrarConexion() {
		try {
			socket.close();
			oos.close();
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void informarAccionAServidor(Empleado e, String mensaje) {
		try {
			// por patron DAO
			Empleado empleado = new Empleado(e.getNombre(), e.getBox(), e.getEstadoDTO(), e.getCliente());
			enviarDatos(empleado, mensaje);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public int getPuertoConectado() {
		return puerto;
	}

}
