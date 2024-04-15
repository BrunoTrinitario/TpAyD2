package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {
	private GestorColas gestorcolas=new GestorColas();
	private int puerto=1234;
	@Override
	public void run() {
		try {
            ServerSocket s = new ServerSocket(this.puerto);
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

}
