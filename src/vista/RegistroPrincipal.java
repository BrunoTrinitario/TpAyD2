package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class RegistroPrincipal {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroPrincipal window = new RegistroPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegistroPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 787, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnDelete = new JButton("Servicio 1");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(240, 147, 89, 96);
		frame.getContentPane().add(btnDelete);
		
		JButton btnServicio = new JButton("Servicio 2");
		btnServicio.setBounds(339, 147, 89, 96);
		frame.getContentPane().add(btnServicio);
		
		JButton btnServicio_1 = new JButton("Servicio 3");
		btnServicio_1.setBounds(438, 147, 89, 96);
		frame.getContentPane().add(btnServicio_1);
		
		JButton btnServicio_3 = new JButton("Servicio 4");
		btnServicio_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnServicio_3.setBounds(240, 254, 89, 96);
		frame.getContentPane().add(btnServicio_3);
		
		JButton btnServicio_4 = new JButton("Servicio 5");
		btnServicio_4.setBounds(339, 254, 89, 96);
		frame.getContentPane().add(btnServicio_4);
		
		JButton btnServicio_1_1 = new JButton("Servicio 6");
		btnServicio_1_1.setBounds(438, 254, 89, 96);
		frame.getContentPane().add(btnServicio_1_1);
		
		JLabel lblNewLabel = new JLabel("Elija el servicio");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 44));
		lblNewLabel.setBounds(240, 24, 287, 112);
		frame.getContentPane().add(lblNewLabel);
	}
}
