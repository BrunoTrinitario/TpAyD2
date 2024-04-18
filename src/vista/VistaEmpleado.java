package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.ControladorEmpleado;
import util.EstadoEmpleado;

public class VistaEmpleado implements ActionListener{

	private JFrame frame;
	private JTextField txtInformacionDelCliente;
	private JTextField txtInformacionIngresadaPor;
	private String nombre;
	private int box;
	private JButton botonFinalizar,botonNoAsistio,botonCambioEstado;
	private JLabel labelDniCliente,labelBox,labelEmpleado,labelEstado,textoDniCliente;
	private JTextField textoInfoCliente,textoInfoIngresada;
	private ControladorEmpleado ce;
	/**
	 * Create the application.
	 */
	public VistaEmpleado(ControladorEmpleado ce,String nombre,int box) {
		this.ce=ce;
		this.nombre=nombre;
		this.box=box;
		this.botonFinalizar= new JButton("Finalizar atencion");
		this.botonNoAsistio= new JButton("Cliente no asistió");
		this.botonCambioEstado= new JButton("Cambiar a disponible");
		this.labelDniCliente= new JLabel("DNI del cliente:");
		this.textoInfoCliente= new JTextField();
		this.textoInfoIngresada= new JTextField();
		this.labelBox= new JLabel("BOX: "+this.box);
		this.labelEmpleado= new JLabel("Empleado: "+this.nombre);
		this.labelEstado= new JLabel("Estado: "+ EstadoEmpleado.NoDisponible);
		
		this.textoDniCliente = new JLabel("-");
		initialize();
		this.addActionListenerToButtons();
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
		
		this.addBounds();
		this.addToFrame();
		
		botonFinalizar.setEnabled(false);
		botonNoAsistio.setEnabled(false);
		textoInfoCliente.setEnabled(false);
		textoInfoCliente.setText("Informacion anterior del cliente");
		textoInfoCliente.setColumns(10);
		textoInfoIngresada.setEnabled(false);
		textoInfoIngresada.setText("Informacion ingresada por el empleado sobre la atencion al cliente");
		textoInfoIngresada.setColumns(10);
		labelBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
	}
	private void addBounds() {
		botonFinalizar.setBounds(54, 413, 177, 41);
		botonNoAsistio.setBounds(54, 465, 177, 41);
		botonCambioEstado.setBounds(54, 361, 177, 41);
		labelDniCliente.setBounds(54, 75, 82, 14);
		textoInfoCliente.setBounds(255, 90, 483, 242);
		textoInfoIngresada.setBounds(255, 361, 483, 145);
		labelBox.setBounds(54, 11, 66, 20);
		labelEmpleado.setBounds(152, 11, 145, 20);
		labelEstado.setBounds(346, 11, 145, 20);
		textoDniCliente.setBounds(54, 90, 177, 20);
	}
	
	private void addToFrame() {
		frame.getContentPane().add(botonFinalizar);
		frame.getContentPane().add(botonNoAsistio);
		frame.getContentPane().add(botonCambioEstado);
		frame.getContentPane().add(labelDniCliente);
		frame.getContentPane().add(textoInfoCliente);
		frame.getContentPane().add(textoInfoIngresada);
		frame.getContentPane().add(labelBox);
		frame.getContentPane().add(labelEmpleado);
		frame.getContentPane().add(labelEstado);
		frame.getContentPane().add(textoDniCliente);
	}
	
	private void addActionListenerToButtons() {
		botonCambioEstado.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==botonCambioEstado) {
			
			if(ce.getEstado()==EstadoEmpleado.Disponible) {
				ce.cambioEstado(EstadoEmpleado.NoDisponible);
				this.labelEstado.setText("Estado: "+EstadoEmpleado.NoDisponible);
				this.botonCambioEstado.setText("Cambiar a Disponible");
				this.labelEstado.repaint();
			}
			else {
				ce.cambioEstado(EstadoEmpleado.Disponible);
				this.labelEstado.setText("Estado: "+EstadoEmpleado.Disponible);
				this.botonCambioEstado.setText("Cambiar a No Disponible");
				this.labelEstado.repaint();

			}
		}
		else {
			if(e.getSource()==botonFinalizar) {
				ce.cambioEstado(EstadoEmpleado.NoDisponible);
				this.labelEstado.setText("Estado: "+EstadoEmpleado.NoDisponible);	
				this.botonCambioEstado.setText("Cambiar a Disponible");
				this.botonCambioEstado.setEnabled(true);
			}
			else {
				if(e.getSource()==botonNoAsistio) {
					ce.cambioEstado(EstadoEmpleado.Disponible);
					this.labelEstado.setText("Estado: "+EstadoEmpleado.Disponible);
					this.botonCambioEstado.setText("Cambiar a No Disponible");
					this.botonCambioEstado.setEnabled(true);
					
				}
			}
		}
	}
	public void RecepcionClientes(){
		this.botonFinalizar.setEnabled(true);
		this.botonNoAsistio.setEnabled(true);
	}
}