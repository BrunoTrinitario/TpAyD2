package util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import controlador.ControladorAdministrador;

public class PingEcho extends Thread {
	private String ip;
	private int puerto;
	private ControladorAdministrador ca;
	public PingEcho(String ip,int puerto,ControladorAdministrador ca) {
		this.ip=ip;
		this.puerto=puerto;
		this.ca=ca;
	}
	public void run() {
		int aux2=puerto-2000;
		String aux=""+ip+":"+aux2;
		String texto;
		while(true) {
			LocalTime tl=LocalTime.now();
			DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
			String ha=tl.format(formatoHora);
			if (conecto()) {
				texto=aux+" conexion exitosa " + ha;
			}else
				texto=aux+" conexion fallida " + ha;
			ca.pingEcho(texto);
			try {
				this.sleep(5000);
			} catch (InterruptedException e) {
				
			}
		}
	}
	public boolean conecto() {
		try {
			Socket s=new Socket(this.ip,this.puerto);
			s.close();
			return true;
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		
	}
}
