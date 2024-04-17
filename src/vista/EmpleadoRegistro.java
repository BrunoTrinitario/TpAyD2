package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.ControladorEmpleado;
import excepciones.BoxYaRegistradoException;

public class EmpleadoRegistro {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private ControladorEmpleado ce=new ControladorEmpleado();
	/**
	 * Launch the application.
	 */
	public EmpleadoRegistro() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 254, 284);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ingrese su nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(10, 36, 208, 32);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(20, 79, 173, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(20, 153, 86, 20);
		frame.getContentPane().add(textField_1);
		
		JLabel lblIngreseSuBox = new JLabel("Ingrese su BOX:");
		lblIngreseSuBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIngreseSuBox.setBounds(10, 110, 208, 32);
		frame.getContentPane().add(lblIngreseSuBox);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ce.crearEmpleado(textField.getText(),Integer.parseInt(textField_1.getText()));
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (BoxYaRegistradoException e1) {
					//instancia de ventana de box ya existente
				}
				VistaEmpleado ve=new VistaEmpleado(textField.getText(),Integer.parseInt(textField_1.getText()));
				frame.dispose();
			}
		});
		btnNewButton.setBounds(74, 211, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
