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
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class RegistroDni {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroDni window = new RegistroDni();
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
	public RegistroDni() {
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
		
		JButton btnNewButton = new JButton("1");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(237, 148, 89, 96);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("2");
		btnNewButton_1.setBounds(336, 148, 89, 96);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("3");
		btnNewButton_2.setBounds(435, 148, 89, 96);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("4");
		btnNewButton_3.setBounds(237, 255, 89, 96);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_1_1 = new JButton("5");
		btnNewButton_1_1.setBounds(336, 255, 89, 96);
		frame.getContentPane().add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("6");
		btnNewButton_2_1.setBounds(435, 255, 89, 96);
		frame.getContentPane().add(btnNewButton_2_1);
		
		JButton btnNewButton_4 = new JButton("7");
		btnNewButton_4.setBounds(237, 362, 89, 96);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_1_2 = new JButton("8");
		btnNewButton_1_2.setBounds(336, 362, 89, 96);
		frame.getContentPane().add(btnNewButton_1_2);
		
		JButton btnNewButton_2_2 = new JButton("9");
		btnNewButton_2_2.setBounds(435, 362, 89, 96);
		frame.getContentPane().add(btnNewButton_2_2);
		
		JButton btnDelete = new JButton("Borrar");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(237, 469, 89, 96);
		frame.getContentPane().add(btnDelete);
		
		JButton btnNewButton_1_3 = new JButton("0");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1_3.setBounds(336, 469, 89, 96);
		frame.getContentPane().add(btnNewButton_1_3);
		
		JButton btnNewButton_2_3 = new JButton("Borrar todo");
		btnNewButton_2_3.setBounds(435, 469, 89, 96);
		frame.getContentPane().add(btnNewButton_2_3);
		
		JButton btnNewButton_2_3_1 = new JButton("Aceptar");
		btnNewButton_2_3_1.setBounds(534, 469, 89, 96);
		frame.getContentPane().add(btnNewButton_2_3_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 29));
		textArea.setText("37550930");
		textArea.setBounds(237, 86, 287, 51);
		frame.getContentPane().add(textArea);
		
		JButton btnNewButton_2_3_1_1 = new JButton("Atras");
		btnNewButton_2_3_1_1.setBounds(52, 255, 89, 96);
		frame.getContentPane().add(btnNewButton_2_3_1_1);
		
		JLabel lblIngreseSuNro = new JLabel("Ingrese su nro de DNI");
		lblIngreseSuNro.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseSuNro.setFont(new Font("Tahoma", Font.PLAIN, 44));
		lblIngreseSuNro.setBounds(80, 0, 610, 96);
		frame.getContentPane().add(lblIngreseSuNro);
	}
}
