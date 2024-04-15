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

public class Administrador {

	private JFrame frame;
	private JTextField txtInformacionDelCliente;
	private JTable table;
	private JTextField txtTablero;
	private JTextField txtTablero_1;
	private JTextField txtTablero_2;
	private JTextField txtTablero_3;
	private JTextField txtTablero_4;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JLabel lblAdministradorJuanOlave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrador window = new Administrador();
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
	public Administrador() {
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
		
		JLabel lblNewLabel = new JLabel("Box");
		lblNewLabel.setBounds(62, 54, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtInformacionDelCliente = new JTextField();
		txtInformacionDelCliente.setText("Tableros graficos de metricas");
		txtInformacionDelCliente.setBounds(255, 54, 483, 467);
		frame.getContentPane().add(txtInformacionDelCliente);
		txtInformacionDelCliente.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(118, 54, 46, 14);
		frame.getContentPane().add(lblEstado);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "Atendiendo"},
				{"2", "Disponible"},
				{"3", "No disponible"},
				{"4", "Atendiendo"},
				{"5", "Atendiendo"},
				{"6", "Atendiendo"},
			},
			new String[] {
				"New column", "New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(36);
		table.setBounds(62, 79, 129, 96);
		frame.getContentPane().add(table);
		
		txtTablero = new JTextField();
		txtTablero.setText("Tablero 1");
		txtTablero.setBounds(255, 518, 61, 20);
		frame.getContentPane().add(txtTablero);
		txtTablero.setColumns(10);
		
		txtTablero_1 = new JTextField();
		txtTablero_1.setText("Tablero 2");
		txtTablero_1.setColumns(10);
		txtTablero_1.setBounds(313, 518, 61, 20);
		frame.getContentPane().add(txtTablero_1);
		
		txtTablero_2 = new JTextField();
		txtTablero_2.setText("Tablero 3");
		txtTablero_2.setColumns(10);
		txtTablero_2.setBounds(373, 518, 61, 20);
		frame.getContentPane().add(txtTablero_2);
		
		txtTablero_3 = new JTextField();
		txtTablero_3.setText("Tablero 4");
		txtTablero_3.setColumns(10);
		txtTablero_3.setBounds(431, 518, 61, 20);
		frame.getContentPane().add(txtTablero_3);
		
		txtTablero_4 = new JTextField();
		txtTablero_4.setText("Tablero 5");
		txtTablero_4.setColumns(10);
		txtTablero_4.setBounds(493, 518, 61, 20);
		frame.getContentPane().add(txtTablero_4);
		
		JLabel lblMetrica = new JLabel("Metrica 1");
		lblMetrica.setBounds(64, 262, 46, 14);
		frame.getContentPane().add(lblMetrica);
		
		textField = new JTextField();
		textField.setText("102.5");
		textField.setBounds(64, 285, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("5");
		textField_1.setColumns(10);
		textField_1.setBounds(64, 339, 86, 20);
		frame.getContentPane().add(textField_1);
		
		JLabel lblMetrica_5 = new JLabel("Metrica 2");
		lblMetrica_5.setBounds(64, 316, 46, 14);
		frame.getContentPane().add(lblMetrica_5);
		
		textField_2 = new JTextField();
		textField_2.setText("2005.31");
		textField_2.setColumns(10);
		textField_2.setBounds(64, 393, 86, 20);
		frame.getContentPane().add(textField_2);
		
		JLabel lblMetrica_1 = new JLabel("Metrica 3");
		lblMetrica_1.setBounds(64, 370, 46, 14);
		frame.getContentPane().add(lblMetrica_1);
		
		textField_3 = new JTextField();
		textField_3.setText("120");
		textField_3.setColumns(10);
		textField_3.setBounds(64, 447, 86, 20);
		frame.getContentPane().add(textField_3);
		
		JLabel lblMetrica_2 = new JLabel("Metrica 4");
		lblMetrica_2.setBounds(64, 424, 46, 14);
		frame.getContentPane().add(lblMetrica_2);
		
		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setColumns(10);
		textField_4.setBounds(64, 501, 86, 20);
		frame.getContentPane().add(textField_4);
		
		JLabel lblMetrica_3 = new JLabel("Metrica 5");
		lblMetrica_3.setBounds(64, 478, 46, 14);
		frame.getContentPane().add(lblMetrica_3);
		
		lblAdministradorJuanOlave = new JLabel("Panel de administrador");
		lblAdministradorJuanOlave.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblAdministradorJuanOlave.setBounds(62, 11, 333, 32);
		frame.getContentPane().add(lblAdministradorJuanOlave);
	}
}
