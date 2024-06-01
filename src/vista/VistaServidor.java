package vista;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controlador.ControladorCliente;
import util.Constantes;
import util.LectorArchivoTexto;

public class VistaServidor {

	private JFrame frame;
	private String estadoServidor="Pasivo";
	private JLabel lblNewLabel_1;
	
	public VistaServidor(int numeroServidor, int puerto) {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel;
		try {
			lblNewLabel = new JLabel("IP: "+ InetAddress.getLocalHost().getHostAddress());
			lblNewLabel.setBounds(27, 31, 110, 14);
			frame.getContentPane().add(lblNewLabel);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}		
		JLabel lblPuerto = new JLabel("Puerto: "+puerto);
		lblPuerto.setBounds(27, 56, 110, 14);
		frame.getContentPane().add(lblPuerto);
		
		lblNewLabel_1 = new JLabel("Estado: "+estadoServidor);
		lblNewLabel_1.setBounds(27, 81, 198, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Servidor "+numeroServidor);
		lblNewLabel_2.setBounds(91, 6, 93, 14);
		frame.getContentPane().add(lblNewLabel_2);
		String contenido = null;
        try {
            contenido = LectorArchivoTexto.leerArchivo(Constantes.ARCHIVO_STRATEGY);
           //System.out.println("Contenido leído del archivo:");
           //System.out.println(contenido);
       } catch (IOException e) {
           e.printStackTrace();
           //System.out.println("Error al leer el archivo.");
       }
		
		JLabel lblNewLabel_3 = new JLabel("Estrategia: "+contenido);
		lblNewLabel_3.setBounds(27, 106, 186, 14);
		frame.getContentPane().add(lblNewLabel_3);
		initialize();
	}

	private void initialize() {	
		frame.setBounds(100, 100, 257, 186);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		this.addBounds();
		this.addToFrame();
		this.addActionListenerToButtons();
		frame.setVisible(true);
	}
	
	private void addBounds() {
	}
	
	private void addToFrame() {
	}
	
	private void addActionListenerToButtons() {
	}

	public void informarServidorActivo() {
		this.estadoServidor="Activo";
		this.lblNewLabel_1.setText("Estado: "+estadoServidor);
	}
}
