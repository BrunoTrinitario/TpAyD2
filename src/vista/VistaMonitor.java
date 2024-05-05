package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import controlador.ControladorMonitor;
import servidor.Metrica;
import util.Constantes;
import javax.swing.JToggleButton;

public class VistaMonitor implements ActionListener{

	private JFrame frame;
	private JLabel titulo,labelMetrica1,labelTablaEstado,labelTablaBox;
	private DefaultTableModel tablaInside;
	private JTable tabla;
	private JButton botonAct;
	private ControladorMonitor ca;
	private JTextField textField;
	private JToggleButton tglbtnNewToggleButton_1;
	private JToggleButton tglbtnNewToggleButton_2;
	private JToggleButton tglbtnNewToggleButton_3;
	private JToggleButton tglbtnNewToggleButton_4;
	private JToggleButton tglbtnNewToggleButton_5;
	
	
	public VistaMonitor(ControladorMonitor ca) {
		this.ca=ca;
		labelTablaBox = new JLabel("Servidor");
		labelTablaEstado = new JLabel("Estado");
		tablaInside=new DefaultTableModel ();
		tablaInside.addColumn("columna1");
		tablaInside.addColumn("columna2");
		tabla=new JTable(tablaInside);
		TableColumnModel columnModel = tabla.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(20);
		labelMetrica1 = new JLabel("Servidor 1");
		titulo = new JLabel("Panel del Monitor");
		botonAct = new JButton("Ping / echo");
		botonAct.addActionListener(this);
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 506, 304);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		this.addBounds();
		this.addToFrame();
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		
		JLabel lblServidor = new JLabel("Servidor 2");
		lblServidor.setBounds(237, 104, 62, 14);
		frame.getContentPane().add(lblServidor);
		
		JLabel lblServidor_1 = new JLabel("Servidor 3");
		lblServidor_1.setBounds(237, 129, 71, 14);
		frame.getContentPane().add(lblServidor_1);
		
		JLabel lblServidor_2 = new JLabel("Servidor 4");
		lblServidor_2.setBounds(237, 154, 62, 14);
		frame.getContentPane().add(lblServidor_2);
		
		JLabel lblServidor_3 = new JLabel("Servidor 5");
		lblServidor_3.setBounds(237, 179, 78, 14);
		frame.getContentPane().add(lblServidor_3);
		
		tglbtnNewToggleButton_1 = new JToggleButton("Encender");
		tglbtnNewToggleButton_1.setBounds(320, 75, 121, 23);
		frame.getContentPane().add(tglbtnNewToggleButton_1);
		tglbtnNewToggleButton_1.addActionListener(this);
		
		tglbtnNewToggleButton_2 = new JToggleButton("Encender");
		tglbtnNewToggleButton_2.setBounds(320, 100, 121, 23);
		frame.getContentPane().add(tglbtnNewToggleButton_2);
		tglbtnNewToggleButton_2.addActionListener(this);
		
		tglbtnNewToggleButton_3 = new JToggleButton("Encender");
		tglbtnNewToggleButton_3.setBounds(320, 125, 121, 23);
		frame.getContentPane().add(tglbtnNewToggleButton_3);
		tglbtnNewToggleButton_3.addActionListener(this);
		
		tglbtnNewToggleButton_4 = new JToggleButton("Encender");
		tglbtnNewToggleButton_4.setBounds(320, 152, 121, 23);
		frame.getContentPane().add(tglbtnNewToggleButton_4);
		tglbtnNewToggleButton_4.addActionListener(this);
		
		tglbtnNewToggleButton_5 = new JToggleButton("Encender");
		tglbtnNewToggleButton_5.setBounds(320, 179, 121, 23);
		frame.getContentPane().add(tglbtnNewToggleButton_5);
		tglbtnNewToggleButton_5.addActionListener(this);
		

	}
	private void addBounds() {
		labelTablaBox.setBounds(62, 54, 73, 14);
		labelTablaEstado.setBounds(145, 54, 46, 14);
		tabla.setBounds(62, 79, 129, 96);
		labelMetrica1.setBounds(237, 79, 62, 14);
		titulo.setBounds(160, 11, 238, 32);
		botonAct.setBounds(62, 190, 129, 23);
	}
	private void addToFrame() {
		frame.getContentPane().add(labelTablaBox);
		frame.getContentPane().add(labelTablaEstado);
		frame.getContentPane().add(tabla);
		frame.getContentPane().add(labelMetrica1);
		frame.getContentPane().add(titulo);
		frame.getContentPane().add(botonAct);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==botonAct) {
			
		}
		else if (e.getSource()==tglbtnNewToggleButton_1) {
			this.tglbtnNewToggleButton_1.setText("Apagar");
			
		}
	}
}
