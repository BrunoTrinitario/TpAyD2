package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

import cliente.Cliente;
import empleado.Empleado;
import excepciones.BoxYaRegistradoException;
import excepciones.DniYaRegistradoException;
import util.Conexion;
import util.Constantes;
import util.DatosConexion;

public class Servidor extends Thread {
	private GestorColas gestorcolas = new GestorColas(this);
	private HashMap<Integer, DatosConexion> empleadosConectados = new HashMap<>();
	private ArrayList<DatosConexion> notificaciones = new ArrayList<>();
	private boolean servidorActivo = false;
	private boolean isServidorRespaldo = false;
	private int puerto;
	private DatosConexion administrador;
	private DatosConexion servidorRespaldo;
	private ArrayList<DatosConexion> servidoresPasivos = new ArrayList<DatosConexion>();
	private Conexion conexion;
	
	public Servidor(int puerto) {
		this.puerto=puerto;
		this.conexion = new Conexion();
		try {
			if (!this.conexion.verificarServidorActivo(this, Constantes.VERIFICAR_SERVIDOR_ACTIVO)) {				
			}
		} catch (IOException e) {
			this.start();
		}
		
	}

	@Override
	public void run() {
		try {
			this.servidorActivo=true;
			this.isServidorRespaldo=false;
			ServerSocket s = new ServerSocket(this.puerto);
			while (servidorActivo) {
				DatosConexion datosConexion = new DatosConexion(s.accept());
				Object objeto = datosConexion.ois.readObject();
				String msg = datosConexion.in.readLine();

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
				}else if (objeto instanceof Servidor) {
					this.registrarServidor(datosConexion);
					datosConexion.out.println(Constantes.SERVIDOR_REGISTRO_OK);
					
				}
							
				
				else {
					if (msg.equals(Constantes.NOTIFICACIONES)) {
					this.notificaciones.add(datosConexion);
					datosConexion.out.println(Constantes.NOTIFICACION_REGISTRO_OK);
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
				}
			}
			
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	

	private void registrarServidor(DatosConexion servidor) {
		if(this.servidoresPasivos.isEmpty()) {
			this.registrarServidorRespaldo(servidor);
		}
		else {
			this.servidoresPasivos.add(servidor);
		}
		
	}

	private void registrarServidorRespaldo(DatosConexion servidor) {
		this.servidorRespaldo=servidor;
		this.informarServidorRespaldo(servidor);
	}

	private void informarServidorRespaldo(final DatosConexion servidor) {
	    servidor.out.println(Constantes.INFORMAR_SERVIDOR_RESPALDO);
	    
	    Thread thread = new Thread(new Runnable() {
	        @Override
	        public void run() {
	            try {
	                servidor.in.read();
	            } catch (IOException e) {
	                servidorRespaldoCaido();
	            }
	        }
	    });
	    
	    // Iniciar el hilo
	    thread.start();
	}

	private void servidorRespaldoCaido() {
		if (!this.servidoresPasivos.isEmpty()) {
			registrarServidorRespaldo(this.servidoresPasivos.get(0));
			this.servidoresPasivos.remove(0);
			
		}
	}

	public void enviarClienteAEmpleado(Empleado empleado, Cliente cliente) {
		try {
			empleadosConectados.get(empleado.getBox()).oos.writeObject(cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void escucharEmpleado(DatosConexion datosConexion, Empleado empleado) {
		new Thread() {
			public void run() {
				try {
					while (datosConexion.socket.isConnected()) {
						Object object = datosConexion.ois.readObject();
						Empleado empleado = (Empleado) object;
						String msg = datosConexion.in.readLine();
						if (msg.equals(Constantes.CLIENTE_AUSENTE)) {
							gestorcolas.clienteNoPresentado(empleado);
						}
						if (msg.equals(Constantes.ATENCION_FINALIZADA)) {
							gestorcolas.finalizarAtencion(empleado);
						}
						if (msg.equals(Constantes.EMPLEADO_CAMBIO_ESTADO)) {
							gestorcolas.cambioEstadoEmpleado(empleado);
						}
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
		DatosConexion aux = this.empleadosConectados.get(empleado.getBox());
		try {
			aux.oos.writeObject(cliente);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void informarNotificaciones(Empleado empleado) {
		try {
			for (DatosConexion aux : this.notificaciones)
				aux.oos.writeObject(empleado);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public boolean getServidorActivo() {
		return this.servidorActivo;
	}


}
