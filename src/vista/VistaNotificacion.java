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

public class VistaNotificacion {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtDni;
	private JTextField txtBox;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_12;
	private JTextField textField_13;

	public VistaNotificacion() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 787, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField.setText("1");
		textField.setColumns(10);
		textField.setBounds(371, 179, 340, 72);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_1.setText("37550930");
		textField_1.setColumns(10);
		textField_1.setBounds(44, 179, 327, 72);
		frame.getContentPane().add(textField_1);
		
		txtDni = new JTextField();
		txtDni.setText("DNI");
		txtDni.setFont(new Font("Tahoma", Font.PLAIN, 65));
		txtDni.setColumns(10);
		txtDni.setBounds(44, 21, 327, 72);
		frame.getContentPane().add(txtDni);
		
		txtBox = new JTextField();
		txtBox.setText("BOX");
		txtBox.setFont(new Font("Tahoma", Font.PLAIN, 65));
		txtBox.setColumns(10);
		txtBox.setBounds(371, 21, 340, 72);
		frame.getContentPane().add(txtBox);
		
		textField_4 = new JTextField();
		textField_4.setText("7842215");
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_4.setColumns(10);
		textField_4.setBounds(44, 251, 327, 72);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setText("4");
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_5.setColumns(10);
		textField_5.setBounds(371, 251, 340, 72);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setForeground(Color.LIGHT_GRAY);
		textField_6.setText("12332335");
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_6.setColumns(10);
		textField_6.setBounds(44, 466, 327, 72);
		frame.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setForeground(Color.LIGHT_GRAY);
		textField_7.setText("3");
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_7.setColumns(10);
		textField_7.setBounds(371, 466, 340, 72);
		frame.getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setText("4522153");
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_8.setColumns(10);
		textField_8.setBounds(44, 325, 327, 72);
		frame.getContentPane().add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setText("5");
		textField_9.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_9.setColumns(10);
		textField_9.setBounds(371, 325, 340, 72);
		frame.getContentPane().add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setText("13233556");
		textField_10.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_10.setColumns(10);
		textField_10.setBounds(44, 398, 327, 72);
		frame.getContentPane().add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setText("2");
		textField_11.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_11.setColumns(10);
		textField_11.setBounds(371, 398, 340, 72);
		frame.getContentPane().add(textField_11);
		
		textField_2 = new JTextField();
		textField_2.setForeground(Color.LIGHT_GRAY);
		textField_2.setText("1");
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_2.setColumns(10);
		textField_2.setBounds(371, 541, 340, 72);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setForeground(Color.LIGHT_GRAY);
		textField_3.setText("12585548");
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_3.setColumns(10);
		textField_3.setBounds(44, 541, 327, 72);
		frame.getContentPane().add(textField_3);
		
		textField_12 = new JTextField();
		textField_12.setText("4562221");
		textField_12.setForeground(Color.BLACK);
		textField_12.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_12.setColumns(10);
		textField_12.setBounds(44, 104, 327, 72);
		frame.getContentPane().add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setText("3");
		textField_13.setForeground(Color.BLACK);
		textField_13.setFont(new Font("Tahoma", Font.PLAIN, 55));
		textField_13.setColumns(10);
		textField_13.setBounds(371, 104, 340, 72);
		frame.getContentPane().add(textField_13);
		frame.setVisible(true);
	}
	public void agregar() {
		
	}
	
}
