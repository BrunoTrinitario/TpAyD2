package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import cliente.Cliente;
import controlador.ControladorNotificaciones;
import empleado.Empleado;
import empleado.NegociosEmpleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import servidor.Metrica;

public class Conexion {

	private Socket socket;
	private ObjectOutputStream oos;
	private BufferedReader in;
	private PrintWriter out;
	private ObjectInputStream ois;

	public void envioEmpleadoAServidor(NegociosEmpleado negociosEmpleado, Empleado empleado, String mensaje) throws BoxYaRegistradoException, IOException {
		String msg = envioDatosAServidor(empleado, mensaje);
		escucharServidorEmpleado(negociosEmpleado);
		if (msg.equals(Constantes.BOX_YA_REGISTRADO)) {
			throw new BoxYaRegistradoException(Constantes.BOX_YA_REGISTRADO);
		}
	}

	public void envioClienteAServidor(Object objeto, String mensaje) throws DniYaRegistradoException, IOException {
		String msg = envioDatosAServidor(objeto, mensaje);
		if (msg.equals(Constantes.DNI_YA_REGISTRADO)) {
			throw new DniYaRegistradoException(Constantes.DNI_YA_REGISTRADO);
		}
		cerrarConexion();
	}
	
	public void envioNotificacionesAServidor(ControladorNotificaciones cn, String mensaje) throws IOException {
		String msg = envioDatosAServidor(null, mensaje);
		this.escucharServidorNotificaciones(cn);
	}

	public String envioDatosAServidor(Object objeto, String mensaje) throws IOException {
		String msg = null;
			abrirConexion(Constantes.IP, Constantes.PUERTO);
			enviarDatos(objeto, mensaje);
			msg = in.readLine();
		return msg;

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

	private void escucharServidorEmpleado(NegociosEmpleado negociosEmpleado) {
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
					negociosEmpleado.conexionCaida();
				}
			}
		}.start();
	}
	
	private void escucharServidorNotificaciones(ControladorNotificaciones controladorNotificaciones) {
		new Thread() {
			public void run() {
				try {
					while (socket.isConnected()) {
						System.out.println("Escuchando al servidor");
						Object objeto = ois.readObject();
						Empleado aux = (Empleado) objeto;
						System.out.println("Objeto recibido: "+aux);
						controladorNotificaciones.agregarCliente(aux.getCliente(), aux);
					}
				} catch (Exception e) {
					e.printStackTrace();
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
			Empleado empleado = new Empleado(e.getNombre(), e.getBox(), e.getEstado(), e.getCliente());
			enviarDatos(empleado, mensaje);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

}
