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
import util.DatosConexion;
import util.GestorColasDTO;

public class Servidor extends Thread {
	private ControladorServidor cs;
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
	
	public Servidor(int puerto, ControladorServidor cs) {
		this.cs=cs;
		this.puerto=puerto;
		this.conexion = new Conexion();
		try {
			this.conexion.verificarServidorActivo(null, Constantes.VERIFICAR_SERVIDOR_ACTIVO);
			this.escucharServidorActivo();
		} catch (IOException e) {
			this.start();
		}
		
	}

	private void resincronizacionDeEstado(GestorColasDTO dto) {
		this.gestorcolas.reesincronizar(dto);
	}
	
	private void escucharServidorActivo() {
		boolean escuchando = true;
		String msg;
		Object dto;
		while (escuchando) {
			try {
				msg = this.conexion.escucharServidorServidor();				
				if (msg.equals(Constantes.RESINCRONIZAR_ESTADO)){
					dto = this.conexion.escucharServidorServidorObjeto();
					this.resincronizacionDeEstado((GestorColasDTO)dto);
				}
				else if (msg.equals(Constantes.INFORMAR_SERVIDOR_RESPALDO))
					this.isServidorRespaldo=true;
			} catch (IOException e) {
				if (this.isServidorRespaldo) {
					this.start();
					escuchando=false;
				}
				else {
					try {
						Thread.sleep(1000);
						this.conexion.verificarServidorActivo(this, Constantes.VERIFICAR_SERVIDOR_ACTIVO);
					} catch (InterruptedException | IOException e1) {}						
				}
			}			
		}
	}

	@Override
	public void run() {
		try {
			this.cs.informarServidorActivo();
			this.servidorActivo=true;
			this.isServidorRespaldo=false;
			ServerSocket s = new ServerSocket(this.puerto);
			while (servidorActivo) {
				DatosConexion datosConexion = new DatosConexion(s.accept());
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
	public void resincronizarServidoresPasivos(GestorColasDTO dto) {
		for (DatosConexion i:servidoresPasivos) {
			i.out.println(Constantes.RESINCRONIZAR_ESTADO);
			try {
				i.oos.writeObject(dto);
			} catch (IOException e) {
				
			}
		}
	}


}
