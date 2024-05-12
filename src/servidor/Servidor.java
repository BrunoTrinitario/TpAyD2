package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

import cliente.Cliente;
import controlador.ControladorServidor;
import empleado.Empleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.Conexion;
import util.Constantes;
import util.SocketConexion;
import util.GestorColasDTO;

public class Servidor extends Thread {
	private ControladorServidor cs;
	private GestorColas gestorcolas;
	private HashMap<Integer, SocketConexion> empleadosConectados = new HashMap<>();
	private ArrayList<SocketConexion> notificaciones = new ArrayList<>();
	private boolean servidorActivo = false;
	public boolean isServidorRespaldo = false;
	private int puerto;
	private SocketConexion administrador;
	private ArrayList<SocketConexion> servidoresPasivos = new ArrayList<SocketConexion>();
	private Conexion conexion;
	private SocketConexion servidorRespaldo;
	private ServerSocket socketDePing;
	private int contador=0;
	
	public Servidor(int puerto, ControladorServidor cs) {
		this.cs=cs;
		this.puerto=puerto;
		this.gestorcolas = new GestorColas(this);
		this.conexion = new Conexion();
		try {
			socketDePing=new ServerSocket(this.puerto+2000);
		} catch (IOException e1) {
		}
		try {
			this.conexion.verificarServidorActivo(this, Constantes.VERIFICAR_SERVIDOR_ACTIVO);
		} catch (IOException e) {
			this.start();
		}
		
	}

	public void resincronizacionDeEstado(GestorColasDTO dto2) {
		this.gestorcolas.reesincronizar(dto2);
	}
	

	@Override
	public void run() {
		try {
			this.cs.informarServidorActivo();
			this.servidorActivo=true;
			this.isServidorRespaldo=false;
			ServerSocket s = new ServerSocket(this.puerto);
			while (servidorActivo) {
				SocketConexion datosConexion = new SocketConexion(s.accept());
				System.out.println("Recibiendo conexion");
				Object objeto = datosConexion.ois.readObject();
				System.out.println("objeto leido: "+objeto);
				String msg = datosConexion.in.readLine();
				System.out.println("mensaje leido: "+ msg);

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
							escucharEmpleado(datosConexion, empleado);
						} catch (BoxYaRegistradoException e) {
							datosConexion.out.println(e.getMessage());
							}
						
					}
					else if(msg.equals(Constantes.REINTENTO_EMPLEADO)) {
						datosConexion.out.println(Constantes.REINTENTAR_EMPLEADO_OK);
						escucharEmpleado(datosConexion, empleado);
						this.empleadosConectados.put(empleado.getBox(), datosConexion);
					}
				}
									
				else {
					if (msg.equals(Constantes.NOTIFICACIONES)) {
						this.notificaciones.add(datosConexion);
						datosConexion.out.println(Constantes.NOTIFICACION_REGISTRO_OK);
					}
					else if(msg.equals(Constantes.REINTENTO_NOTIFICACION)){
						this.notificaciones.add(datosConexion);
						datosConexion.out.println(Constantes.REINTENTAR_NOTIFICACION_OK);						
					}
					else if (msg.equals(Constantes.ADMINISTRADOR)) {
						this.administrador=datosConexion;
						datosConexion.out.println(Constantes.ADMINISTRADOR_REGISTRO_OK);
					}
					else if (msg.equals(Constantes.SOLICITAR_METRICAS)) {
						Object aux = gestorcolas.actualizarMetricas();
						datosConexion.out.println(Constantes.METRICAS_CREACION_OK);
						datosConexion.oos.writeObject(aux);
					}
					else if (msg.equals(Constantes.VERIFICAR_SERVIDOR_ACTIVO)) {
						System.out.println("registrando servidor pasivo");
						datosConexion.out.println(Constantes.SERVIDOR_REGISTRO_OK);
						this.registrarServidor(datosConexion);					
					}
				}
				this.gestorcolas.gestorColasDTO();
				System.out.println(this.servidoresPasivos);
			}
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void registrarServidor(SocketConexion servidor) {
		if(this.servidoresPasivos.isEmpty()) {
			this.registrarServidorRespaldo(servidor);
		}
		this.servidoresPasivos.add(servidor);		
	}

	private void registrarServidorRespaldo(SocketConexion servidor) {
		this.servidorRespaldo=servidor;
		this.informarServidorRespaldo(servidor);
	}

	private void informarServidorRespaldo(SocketConexion servidor) {
		try {
			System.out.println("Intentando informar respaldo a servidor"+ servidor);
			servidor.oos.writeObject(Constantes.INFORMAR_SERVIDOR_RESPALDO);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	    
	    Thread thread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                servidor.in.read();
	            } catch (IOException e) {
	            	e.printStackTrace();
	                servidorRespaldoCaido();
	            }
	        }
	    });
	    thread.start();
	}

	private void servidorRespaldoCaido() {
		this.servidoresPasivos.remove(0);
		if (!this.servidoresPasivos.isEmpty()) {			
			registrarServidorRespaldo(this.servidoresPasivos.get(0));			
		}
	}

	public void enviarClienteAEmpleado(Empleado empleado, Cliente cliente) {
		try {
			empleadosConectados.get(empleado.getBox()).oos.writeObject(cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void escucharEmpleado(SocketConexion datosConexion, Empleado empleado) {
		new Thread() {
			public void run() {
				try {
					while (datosConexion.socket.isConnected()) {
						Object object = datosConexion.ois.readObject();						
						Empleado empleado = (Empleado) object;
						String msg = datosConexion.in.readLine();
						System.out.println("mensaje recibido: "+msg);
						if (msg.equals(Constantes.CLIENTE_AUSENTE)) {
							gestorcolas.clienteNoPresentado(empleado);
						}
						if (msg.equals(Constantes.ATENCION_FINALIZADA)) {
							gestorcolas.finalizarAtencion(empleado);
						}
						if (msg.equals(Constantes.EMPLEADO_CAMBIO_ESTADO)) {
							gestorcolas.cambioEstadoEmpleado(empleado);
						}
						gestorcolas.gestorColasDTO();
					}
				} catch(IOException e) {
					gestorcolas.desconectarEmpleado(empleado);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();

	}

	public void informarEmpleado(Empleado empleado, Cliente cliente) {
		SocketConexion aux = this.empleadosConectados.get(empleado.getBox());
		try {
			aux.oos.writeObject(cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void informarNotificaciones(Empleado empleado) {
		try {
			for (SocketConexion aux : this.notificaciones)
				aux.oos.writeObject(empleado);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public boolean getServidorActivo() {
		return this.servidorActivo;
	}
	
	
	public void resincronizarServidoresPasivos(GestorColasDTO dto) {
		System.out.println("dto a enviar:" +dto);
		ArrayList<SocketConexion> servidoresARemover = new ArrayList<SocketConexion>();
		for (SocketConexion i:servidoresPasivos) {
			try {
				i.oos.writeObject(dto);
				System.out.println("dto enviado:" +dto);
			} catch (IOException e) {
				servidoresARemover.add(i);
			}
		
		}
		for (SocketConexion i:servidoresARemover) {
			servidoresPasivos.remove(i);
		}
	}

	@Override
	public String toString() {
		return "Servidor [servidorActivo=" + servidorActivo + ", isServidorRespaldo=" + isServidorRespaldo + ", puerto="
				+ puerto + "]";
	}

	
}
