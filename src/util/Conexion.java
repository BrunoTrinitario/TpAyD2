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
import controlador.ControladorAdministrador;
import controlador.ControladorNotificaciones;
import empleado.Empleado;
import empleado.NegociosEmpleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import servidor.Metrica;
import servidor.Servidor;

public class Conexion {

	private Socket socket;
	private ObjectOutputStream oos;
	private BufferedReader in;
	private PrintWriter out;
	private ObjectInputStream ois;
	private int puerto;
	
	public String envioEmpleadoAServidor(NegociosEmpleado negociosEmpleado, Empleado empleado, String mensaje) throws BoxYaRegistradoException, IOException {
		String msg = envioDatosAServidor(empleado, mensaje);		
		if (msg.equals(Constantes.BOX_YA_REGISTRADO)) {
			throw new BoxYaRegistradoException(Constantes.BOX_YA_REGISTRADO);
		}
		escucharServidorEmpleado(negociosEmpleado);
		return msg;
	}

	public String envioClienteAServidor(Object objeto, String mensaje) throws DniYaRegistradoException, IOException {
		String msg = envioDatosAServidor(objeto, mensaje);
		System.out.println("Llegue");
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
		this.escucharServidorAdministrador(ca);
	}
	
	public void verificarServidorActivo(Servidor servidor, String mensaje) throws IOException {
			String msg = envioDatosAServidor(servidor, mensaje);
	}
	
	public String escucharServidorServidor() throws IOException {
		return this.in.readLine();
	}
	public Object escucharServidorServidorObjeto() {
		try {
			return this.ois.readObject();
		} catch (ClassNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	private String envioDatosAServidor(Object objeto, String mensaje) throws IOException {
		String respuesta=null;
		for (int i=0; i<Constantes.INTENTO_CONEXION;i++) {
			for ( int puerto : Constantes.PUERTOS) {
				try {
					abrirConexion(Constantes.IP, puerto);
					oos.writeObject(objeto);
					out.println(mensaje);
					respuesta=in.readLine();
					this.puerto=puerto;
				} catch (Exception e) {}			
			}
			try {
				if (!mensaje.equals(Constantes.VERIFICAR_SERVIDOR_ACTIVO))
					Thread.sleep(Constantes.TIEMPO_REINTENTO);
			} catch (InterruptedException e) {}
			System.out.println(respuesta+"pepe");
			if(respuesta!=null) {
				return respuesta;
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
		this.puerto=puerto;
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
					try {
						reintentarConexion(negociosEmpleado.getEmpleado(),Constantes.REINTENTO_EMPLEADO);
						negociosEmpleado.numeroServidorConectado(Constantes.PUERTOS.indexOf(puerto)+1);
					} catch (IOException e1) {
						negociosEmpleado.conexionCaida(); 
					}
						
				}
			}
		}.start();
	}
	
	private void escucharServidorAdministrador(ControladorAdministrador ca) {
		new Thread() {
			public void run() {
				try {
					while (socket.isConnected()) {
						String heartBeat = in.readLine();
						
					}
				} catch (Exception e) {
					e.printStackTrace();
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
					boolean conecto=false;
					while(!conecto) {
						try {
							reintentarConexion(controladorNotificaciones,Constantes.REINTENTO_NOTIFICACION);
							conecto=true;
						} catch (IOException e1) {
							//sigue intentando conectar
						}
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
	private boolean reintentarConexion(Object objeto,String mensaje) throws IOException  {
		boolean conecto=false;
		String tipoRegistro;
		tipoRegistro=envioDatosAServidor(objeto,mensaje);
		System.out.println(tipoRegistro+" bOQUITA");
		if(tipoRegistro.equals(Constantes.REINTENTAR_NOTIFICACION_OK) || tipoRegistro.equals(Constantes.REINTENTAR_EMPLEADO_OK ))
			conecto=true;
		return conecto;
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
	public int getPuertoConectado() {
		return puerto;
	}

}
