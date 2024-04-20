package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import cliente.Cliente;
import empleado.Empleado;
import util.NotificacionAuxiliar;

import java.awt.Font;
import java.awt.Color;
import java.awt.Container;

public class VistaNotificacion {

	private JFrame frame;
	private JTextField txtDni;
	private JTextField txtBox;
	private Queue<NotificacionAuxiliar> lista=new LinkedList<NotificacionAuxiliar>();

	public VistaNotificacion() {
		initialize();
		frame.setVisible(true);
	}
	//x0 y 0 ancho alto
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 787, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtDni = new JTextField();
		txtDni.setText("DNI");
		txtDni.setFont(new Font("Tahoma", Font.PLAIN, 65));
		txtDni.setColumns(10);
		txtDni.setBounds(44, 21, 327, 72);
		txtDni.setEditable(false);
		frame.getContentPane().add(txtDni);
		
		txtBox = new JTextField();
		txtBox.setText("BOX");
		txtBox.setFont(new Font("Tahoma", Font.PLAIN, 65));
		txtBox.setColumns(10);
		txtBox.setBounds(371, 21, 340, 72);
		txtBox.setEditable(false);
		txtBox.setBackground(null);
		frame.getContentPane().add(txtBox);
		
		frame.setVisible(true);
	}
	public void agregar(Cliente cliente,Empleado empleado) {
		NotificacionAuxiliar aux=new NotificacionAuxiliar(cliente.getDni(),empleado.getBox());
		Queue<NotificacionAuxiliar>colaAux=new LinkedList<>(lista);
		if (!lista.isEmpty())
			this.lista.element().cambioColor();
		while (!colaAux.isEmpty()) {
			NotificacionAuxiliar elemento=colaAux.poll();
			elemento.cambioColor();
			elemento.desplazamiento();
			System.out.println(elemento.getTextoBox().getBounds().getY());
		}
		this.lista.add(aux);
		frame.getContentPane().add(aux.getTextoBox());
		frame.getContentPane().add(aux.getTextoDni());
		frame.repaint();
	}
	
	
}
