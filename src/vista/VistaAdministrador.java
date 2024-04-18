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

import controlador.ControladorAdministrador;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class VistaAdministrador implements ActionListener{

	private JFrame frame;
	private JLabel titulo,labelMetrica1,labelMetrica2,labelTablaEstado,labelTablaBox;
	private JTable tabla;
	private JTextField metrica1,metrica2;
	private JButton botonAct;
	private ControladorAdministrador ca=new ControladorAdministrador();
	
	
	public VistaAdministrador() {
		labelTablaBox = new JLabel("Box");
		labelTablaEstado = new JLabel("Estado");
		tabla=new JTable();
		labelMetrica1 = new JLabel("Cantidad de clientes atendidos:");
		labelMetrica2 = new JLabel("Tiempo promedio en espera:");
		metrica2 = new JTextField();
		metrica1 = new JTextField();
		titulo = new JLabel("Panel de administrador");
		botonAct = new JButton("Actualizar metricas");
		botonAct.addActionListener(this);
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 304);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		metrica1.setText("");
		metrica1.setColumns(30);
		metrica2.setText("");
		metrica2.setColumns(30);
		this.addBounds();
		this.addToFrame();
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 19));
	}
	private void addBounds() {
		labelTablaBox.setBounds(62, 54, 46, 14);
		labelTablaEstado.setBounds(118, 54, 46, 14);
		tabla.setBounds(62, 79, 129, 96);
		labelMetrica1.setBounds(237, 79, 161, 14);
		metrica1.setBounds(396, 76, 86, 20);
		metrica2.setBounds(396, 101, 86, 20);
		titulo.setBounds(160, 11, 238, 32);
		botonAct.setBounds(237, 132, 245, 23);
		labelMetrica2.setBounds(237, 104, 161, 14);
	}
	private void addToFrame() {
		frame.getContentPane().add(labelTablaBox);
		frame.getContentPane().add(labelTablaEstado);
		frame.getContentPane().add(tabla);
		frame.getContentPane().add(labelMetrica1);
		frame.getContentPane().add(metrica1);
		frame.getContentPane().add(metrica2);
		frame.getContentPane().add(labelMetrica2);
		frame.getContentPane().add(titulo);
		frame.getContentPane().add(botonAct);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ca.solicitarMetricas();
	}
}
