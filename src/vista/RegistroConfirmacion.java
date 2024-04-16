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
import javax.swing.JTextPane;

public class RegistroConfirmacion {

	private JFrame frame;

	public RegistroConfirmacion() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 398, 257);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextPane txtpnSuDniHa = new JTextPane();
		txtpnSuDniHa.setText("Su dni ha sido registrado, por favor aguarde a ser llamado por la pantalla\r\n");
		txtpnSuDniHa.setBounds(75, 59, 237, 73);
		frame.getContentPane().add(txtpnSuDniHa);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(147, 143, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
