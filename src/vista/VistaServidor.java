package vista;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controlador.ControladorCliente;

public class VistaServidor {

	private JFrame frame;
	private ControladorCliente cc=new ControladorCliente();
	
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
		
		JLabel lblNewLabel_1 = new JLabel("Estado: ");
		lblNewLabel_1.setBounds(27, 81, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Servidor "+numeroServidor);
		lblNewLabel_2.setBounds(91, 6, 93, 14);
		frame.getContentPane().add(lblNewLabel_2);
		initialize();
	}

	private void initialize() {	
		frame.setBounds(100, 100, 251, 182);
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
}
