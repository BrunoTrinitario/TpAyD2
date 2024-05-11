package util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

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
		String aux=""+ip+":"+puerto;
		String texto;
		while(true) {
			if (conecto()) {
				texto=aux+" conexion existosa";
			}else
				texto=aux+" conexion fallida";
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
			return true;
		} catch (UnknownHostException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
		
	}
}
