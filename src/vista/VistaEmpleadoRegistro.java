package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cliente.Cliente;
import controlador.ControladorEmpleado;
import excepciones.BoxYaRegistradoException;
import util.Constantes;

public class VistaEmpleadoRegistro {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private ControladorEmpleado ce;
	/**
	 * Launch the application.
	 */
	public VistaEmpleadoRegistro(ControladorEmpleado ce) {
		this.ce=ce;
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
					VistaEmpleado ve=new VistaEmpleado(ce,textField.getText(),Integer.parseInt(textField_1.getText()));
					frame.dispose();
				} catch (NumberFormatException e1) {
					if (textField_1.getText().equals("")) {
						VentanaEmergente ventanaEmergente = new VentanaEmergente("Debe completar con un BOX");
					}else {
						VentanaEmergente ventanaEmergente = new VentanaEmergente("Su box debe ser solo numeros");
					}
				} catch (BoxYaRegistradoException e1) {
					VentanaEmergente ventanaEmergente = new VentanaEmergente(Constantes.BOX_YA_REGISTRADO);
				}

			}
		});
		btnNewButton.setBounds(74, 211, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}

}
