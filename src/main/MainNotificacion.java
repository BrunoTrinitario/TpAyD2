package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import vista.Notificacion;

public class MainNotificacion {

	public static void main(String[] args) {

		Notificacion vista = new Notificacion();
		
		 new Thread() {
	            public void run() {
	                try {
	                    ServerSocket s = new ServerSocket(124);
	                    while (true) {

	                        Socket soc = s.accept();
	                        PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
	                        BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
	                        String msg = in.readLine();
	                        System.out.println(msg);
	                    }

	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        }.start();

	}

}
