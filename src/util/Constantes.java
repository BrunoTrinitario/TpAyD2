package util;

import java.util.ArrayList;

public class Constantes {
	
	public static final String CLIENTE_REGISTRO_OK = "Su dni ha sido registrado, por favor aguarde a ser llamado por la pantalla";
	public static final String EMPLEADO_REGISTRO_OK = "Empleado creado correctamente";
	public static final String METRICAS_CREACION_OK = "Las metricas se crearon correctamente";
	public static final String EMPLEADO_NUEVO = "EMPLEADO_NUEVO";
	public static final String DNI_YA_REGISTRADO = "Su dni ya habia sido registrado y se encuentra actualmente en la cola de espera para ser llamado";
	public static final String DNI_INCORRECTO = "El DNI ingresado es incorrecto, debe ser un numero entre 7 y 9 digitos";
	public static final String BOX_YA_REGISTRADO = "Su box ya se encuentra registrado en el sistema";
	public static final String EMPLEADO_CAMBIO_ESTADO = "Empleado cambio estado";
	public static final String IP = "localhost";
	public static final int PUERTO = 1234;
	public static final int PUERTO2 = 1235;
	public static final String SOLICITAR_METRICAS = "solicitud de metricas";
	public static final String CLIENTE_AUSENTE="Cliente ausente";
	public static final String ATENCION_FINALIZADA="Atencion Finalizada";
	public static final String EMPLEADO_CAMBIO_A_DISPONIBLE="Empleado disponible";
	public static final String EMPLEADO_CAMBIO_A_NO_DISPONIBLE="Empleado NO disponible";
	public static final String ERROR_CONEXION="Se produjo un error al intentar conectar, es posible que el servidor no esté disponible";
	public static final String NOTIFICACION_REGISTRO_OK = "El panel de notifiaciones se ha registrado en el servidor correctamente";
	public static final String ADMINISTRADOR_REGISTRO_OK = "El administrador se registro correctamente en el servidor";
	public static final String NOTIFICACIONES = "Notificaciones";
	public static final String ADMINISTRADOR ="Administrador";
	public static final String VERIFICAR_SERVIDOR_ACTIVO = "Verificar servidor activo";
	public static final int INTENTO_CONEXION=2;
	public static final ArrayList<Integer> PUERTOS=new ArrayList<Integer>();
	public static final long TIEMPO_REINTENTO = 5000;
	public static final String SERVIDOR_REGISTRO_OK = "Servidor registrado correctamente";
	public static final String INFORMAR_SERVIDOR_RESPALDO = "Informar servidor respaldo";
	public static final Object RESINCRONIZAR_ESTADO = "Resincronizar estado";
	static {
		PUERTOS.add(PUERTO);
		PUERTOS.add(PUERTO2);
	}
}
