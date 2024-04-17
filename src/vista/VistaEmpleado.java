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
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;

public class VistaEmpleado {

	private JFrame frame;
	private JTextField txtInformacionDelCliente;
	private JTextField txtInformacionIngresadaPor;
	private String nombre;
	private int box;

	/**
	 * Create the application.
	 */
	public VistaEmpleado(String nombre,int box) {
		this.nombre=nombre;
		this.box=box;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 787, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Finalizar atencion");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(54, 413, 177, 41);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnClienteNoAsisti = new JButton("Cliente no asistió");
		btnClienteNoAsisti.setEnabled(false);
		btnClienteNoAsisti.setBounds(54, 465, 177, 41);
		frame.getContentPane().add(btnClienteNoAsisti);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(54, 90, 177, 20);
		frame.getContentPane().add(textPane);
		
		JLabel lblNewLabel = new JLabel("DNI del cliente:");
		lblNewLabel.setBounds(54, 75, 82, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtInformacionDelCliente = new JTextField();
		txtInformacionDelCliente.setEnabled(false);
		txtInformacionDelCliente.setText("Informacion anterior del cliente");
		txtInformacionDelCliente.setBounds(255, 90, 483, 242);
		frame.getContentPane().add(txtInformacionDelCliente);
		txtInformacionDelCliente.setColumns(10);
		
		txtInformacionIngresadaPor = new JTextField();
		txtInformacionIngresadaPor.setEnabled(false);
		txtInformacionIngresadaPor.setText("Informacion ingresada por el empleado sobre la atencion al cliente");
		txtInformacionIngresadaPor.setColumns(10);
		txtInformacionIngresadaPor.setBounds(255, 361, 483, 145);
		frame.getContentPane().add(txtInformacionIngresadaPor);
		
		JLabel lblBox = new JLabel("BOX: "+this.box);
		lblBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblBox.setBounds(54, 11, 66, 20);
		frame.getContentPane().add(lblBox);
		
		JLabel lblEmpleadoJuanOlave = new JLabel("Empleado: "+this.nombre);
		lblEmpleadoJuanOlave.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmpleadoJuanOlave.setBounds(152, 11, 145, 20);
		frame.getContentPane().add(lblEmpleadoJuanOlave);
		
		JLabel lblEstadoAtendiendo = new JLabel("Estado: No disponible");
		lblEstadoAtendiendo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEstadoAtendiendo.setBounds(346, 11, 145, 20);
		frame.getContentPane().add(lblEstadoAtendiendo);
		
		JButton btnNewButton_1 = new JButton("Cambiar a disponible");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(54, 361, 177, 41);
		frame.getContentPane().add(btnNewButton_1);
	}
}
