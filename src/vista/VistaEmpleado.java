package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import cliente.Cliente;
import controlador.ControladorEmpleado;
import empleado.StateEmpleadoDisponible;
import empleado.StateEmpleadoNoDisponible;
import util.Constantes;
import util.EstadoEmpleado;

public class VistaEmpleado implements ActionListener{

	private JFrame frame;
	private JTextField txtInformacionDelCliente;
	private JTextField txtInformacionIngresadaPor;
	private String nombre;
	private int box;
	private JButton botonFinalizar,botonNoAsistio,botonCambioEstado;
	private JLabel labelDniCliente,labelBox,labelEmpleado,labelEstado,textoDniCliente,lblServidor;
	private boolean estadoAnteriorBotonFinalizar,estadoAnterioBotonNoAsistio,estadoAnteriorBotonCambioEstado;
	private ControladorEmpleado ce;
	private int servidorConectado;
	private boolean empleadoDisponible;
	/**
	 * Create the application.
	 * @param puertoConectado 
	 */
	public VistaEmpleado(ControladorEmpleado ce,String nombre,int box, int servidorConectado) {
		this.servidorConectado=servidorConectado;
		this.ce=ce;
		this.nombre=nombre;
		this.box=box;
		this.botonFinalizar= new JButton("Finalizar atencion");
		this.ce.setVistaPrincipal(this);
		this.empleadoDisponible=false;
		botonFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		this.botonNoAsistio= new JButton("Cliente no asistió");
		this.botonCambioEstado= new JButton("Cambiar a disponible");
		this.labelDniCliente= new JLabel("DNI del cliente:");
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
		frame.setBounds(100, 100, 544, 303);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		this.addBounds();
		this.addToFrame();
		
		botonFinalizar.setEnabled(false);
		botonNoAsistio.setEnabled(false);
		labelBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		try {
			lblServidor = new JLabel("Conectado a: Servidor "+this.servidorConectado+" en "+InetAddress.getLocalHost().getHostAddress()+" : "+Constantes.PUERTOS.get(this.servidorConectado-1));
		} catch (UnknownHostException e) {
		}
		lblServidor.setBounds(10, 250, 517, 14);
		frame.getContentPane().add(lblServidor);
	}
	private void addBounds() {
		botonFinalizar.setBounds(340, 114, 177, 41);
		botonNoAsistio.setBounds(340, 166, 177, 41);
		botonCambioEstado.setBounds(340, 62, 177, 41);
		labelDniCliente.setBounds(54, 75, 106, 14);
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
		frame.getContentPane().add(labelBox);
		frame.getContentPane().add(labelEmpleado);
		frame.getContentPane().add(labelEstado);
		frame.getContentPane().add(textoDniCliente);
	}
	
	private void addActionListenerToButtons() {
		this.botonCambioEstado.addActionListener(this);
		this.botonFinalizar.addActionListener(this);
		this.botonNoAsistio.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==botonCambioEstado) {
			if(this.empleadoDisponible) {
				ce.cambioEstado(new StateEmpleadoNoDisponible());
				this.empleadoDisponible=false;
				this.labelEstado.setText("Estado: "+EstadoEmpleado.NoDisponible);
				this.botonCambioEstado.setText("Cambiar a Disponible");
			}
			else {
				ce.cambioEstado(new StateEmpleadoDisponible());
				this.empleadoDisponible=true;
				this.labelEstado.setText("Estado: "+EstadoEmpleado.Disponible);
				this.botonCambioEstado.setText("Cambiar a No Disponible");
			}
		}
		else {
			if(e.getSource()==botonFinalizar) {
				ce.finalizarAtencion();
				this.labelEstado.setText("Estado: "+EstadoEmpleado.NoDisponible);	
				this.botonCambioEstado.setText("Cambiar a Disponible");
				this.botonCambioEstado.setEnabled(true);
				this.botonFinalizar.setEnabled(false);
				this.botonNoAsistio.setEnabled(false);
				this.textoDniCliente.setText("-");
			}
			else {
				if(e.getSource()==botonNoAsistio) {
					ce.clienteAusente();
					this.labelEstado.setText("Estado: "+EstadoEmpleado.NoDisponible);	
					this.botonCambioEstado.setText("Cambiar a Disponible");
					this.botonCambioEstado.setEnabled(true);
					this.botonFinalizar.setEnabled(false);
					this.botonNoAsistio.setEnabled(false);
					this.textoDniCliente.setText("-");
				}
			}
		}
	}
	//cuando matchea y se le asigna el cliente se pone visible boton fin y no asistio
	public void recepcionClientes(Cliente cliente){
		this.labelEstado.setText("Atendiendo");
		this.botonFinalizar.setEnabled(true);
		this.botonNoAsistio.setEnabled(true);
		this.textoDniCliente.setText(cliente.getDni());
		this.botonCambioEstado.setEnabled(false);
		this.botonCambioEstado.setText("Cambiar a Disponible");
		this.empleadoDisponible=false;
	}
	public void cambiarNumeroServidor(int servidorConectado) {
		try {
			System.out.println("Servidor conectado en vista "+servidorConectado);
			lblServidor.setText("Conectado a: Servidor "+servidorConectado+" en "+InetAddress.getLocalHost().getHostAddress()+" : "+Constantes.PUERTOS.get(servidorConectado-1));
			System.out.println("Conectado a: Servidor "+servidorConectado+" en "+InetAddress.getLocalHost().getHostAddress()+" : "+Constantes.PUERTOS.get(servidorConectado-1));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	// si se cayeron todos los servidores no hay chance de que vuelva 
	// abrir otro y se reconecte
	public void ConexionCaida() {
		this.botonFinalizar.setEnabled(false);
		this.botonNoAsistio.setEnabled(false);
		this.botonCambioEstado.setEnabled(false);
		lblServidor.setText("Error: Servidor no disponible");
	}
	public void bloquearVista() {
		lblServidor.setText("Intentando reconectar...");
		this.estadoAnteriorBotonCambioEstado=this.botonCambioEstado.isEnabled();
		this.estadoAnterioBotonNoAsistio=this.botonNoAsistio.isEnabled();
		this.estadoAnteriorBotonFinalizar=this.botonFinalizar.isEnabled();
		this.botonFinalizar.setEnabled(false);
		this.botonNoAsistio.setEnabled(false);
		this.botonCambioEstado.setEnabled(false);
	}

	public void desbloquearVista() {
		this.botonFinalizar.setEnabled(this.estadoAnteriorBotonFinalizar);
		this.botonNoAsistio.setEnabled(this.estadoAnterioBotonNoAsistio);
		this.botonCambioEstado.setEnabled(this.estadoAnteriorBotonCambioEstado);

	}
}