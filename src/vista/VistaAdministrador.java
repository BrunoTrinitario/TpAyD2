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
import servidor.Metrica;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class VistaAdministrador implements ActionListener{

	private JFrame frame;
	private JLabel titulo,labelMetrica1,labelMetrica2,labelMetrica3,labelTablaEstado,labelTablaBox;
	private DefaultTableModel tablaInside;
	private JTable tabla;
	private JTextField metrica1,metrica2,metrica3;
	private JButton botonAct;
	private ControladorAdministrador ca=new ControladorAdministrador();
	private JTextField textField;
	
	
	public VistaAdministrador() {
		labelTablaBox = new JLabel("Box");
		labelTablaEstado = new JLabel("Estado");
		tablaInside=new DefaultTableModel ();
		tablaInside.addColumn("columna1");
		tablaInside.addColumn("columna2");
		tabla=new JTable(tablaInside);
		labelMetrica1 = new JLabel("Cantidad de clientes atendidos:");
		labelMetrica2 = new JLabel("Tiempo promedio en espera:");
		labelMetrica3 = new JLabel("Cantidad de clientes en espera:");
		metrica2 = new JTextField();
		metrica1 = new JTextField();
		titulo = new JLabel("Panel de administrador");
		botonAct = new JButton("Actualizar metricas");
		metrica3 = new JTextField();
		botonAct.addActionListener(this);
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 524, 304);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		metrica1.setText("-");
		metrica1.setColumns(30);
		metrica2.setText("-");
		metrica2.setColumns(30);
		this.addBounds();
		this.addToFrame();
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 19));
		metrica3.setText("-");
		metrica3.setColumns(30);
		metrica1.setEditable(false);
		metrica2.setEditable(false);
		metrica3.setEditable(false);


	}
	private void addBounds() {
		labelTablaBox.setBounds(62, 54, 46, 14);
		labelTablaEstado.setBounds(118, 54, 46, 14);
		tabla.setBounds(62, 79, 129, 96);
		labelMetrica1.setBounds(237, 79, 161, 14);
		metrica1.setBounds(396, 76, 86, 20);
		metrica2.setBounds(396, 101, 86, 20);
		titulo.setBounds(160, 11, 238, 32);
		botonAct.setBounds(237, 152, 245, 23);
		labelMetrica3.setBounds(237, 129, 161, 14);
		labelMetrica2.setBounds(237, 104, 161, 14);
		metrica3.setBounds(396, 126, 86, 20);
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
		frame.getContentPane().add(labelMetrica3);
		frame.getContentPane().add(metrica3);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==botonAct) {
			Metrica metrica=ca.solicitarMetricas();
			metrica1.setText(String.valueOf(metrica.cantidadAtendidos()));
			metrica2.setText(metrica.promedioEspera());
			metrica3.setText(String.valueOf(metrica.cantidadEnEspera()));
			int i=0;
			tablaInside.setRowCount(0);
			while (i<metrica.getListaEmpleados().size()) {
				String box=String.valueOf(metrica.getListaEmpleados().get(i).getBox());
				String estado=String.valueOf(metrica.getListaEmpleados().get(i).getEstado());
				tablaInside.addRow(new Object[]{box, estado});
				i++;
			}
		}
	}
}
