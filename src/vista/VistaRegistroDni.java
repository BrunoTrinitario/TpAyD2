package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controlador.ControladorCliente;
import excepciones.DniInvalidoException;
import excepciones.DniYaRegistradoException;
import util.Constantes;

public class VistaRegistroDni implements ActionListener {

	private JFrame frame;
	private JButton boton1,boton2,boton3,boton4,boton5,boton6,boton7,boton8,boton9,boton0;
	private JButton botonB,botonBT,botonAC;
	private JTextArea AreaTexto;
	private JLabel titulo;
	private ControladorCliente cc=new ControladorCliente();
	
	public VistaRegistroDni() {
		frame = new JFrame();
		boton1 = new JButton("1");
		boton2 = new JButton("2");
		boton3 = new JButton("3");
		boton4 = new JButton("4");
		boton5 = new JButton("5");
		boton6 = new JButton("6");
		boton7 = new JButton("7");
		boton8 = new JButton("8");
		boton9 = new JButton("9");
		boton0 = new JButton("0");
		botonB = new JButton("Borrar");
		botonBT = new JButton("Borrar todo");
		botonAC = new JButton("Aceptar");
		AreaTexto = new JTextArea();
		titulo = new JLabel("Ingrese su nro de DNI");
		initialize();
	}

	private void initialize() {	
		frame.setBounds(100, 100, 787, 615);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	
		this.addBounds();
		this.addToFrame();
		this.addActionListenerToButtons();
		
		AreaTexto.setFont(new Font("Monospaced", Font.PLAIN, 29));
		AreaTexto.setText("");
		
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 44));
		frame.setVisible(true);
	}
	
	private void addBounds() {
		boton1.setBounds(237, 148, 89, 96);
		boton2.setBounds(336, 148, 89, 96);
		boton3.setBounds(435, 148, 89, 96);
		boton4.setBounds(237, 255, 89, 96);
		boton5.setBounds(336, 255, 89, 96);
		boton6.setBounds(435, 255, 89, 96);
		boton7.setBounds(237, 362, 89, 96);
		boton8.setBounds(336, 362, 89, 96);
		boton9.setBounds(435, 362, 89, 96);
		boton0.setBounds(336, 469, 89, 96);
		botonB.setBounds(237, 469, 89, 96);
		botonBT.setBounds(435, 469, 89, 96);
		botonAC.setBounds(534, 469, 89, 96);
		AreaTexto.setBounds(237, 86, 287, 51);
		titulo.setBounds(80, 0, 610, 96);
	}
	
	private void addToFrame() {
		frame.getContentPane().add(boton1);
		frame.getContentPane().add(boton2);
		frame.getContentPane().add(boton3);
		frame.getContentPane().add(boton4);
		frame.getContentPane().add(boton5);
		frame.getContentPane().add(boton6);
		frame.getContentPane().add(boton7);
		frame.getContentPane().add(boton8);
		frame.getContentPane().add(boton9);
		frame.getContentPane().add(botonB);
		frame.getContentPane().add(boton0);
		frame.getContentPane().add(botonBT);
		frame.getContentPane().add(botonAC);
		frame.getContentPane().add(AreaTexto);
		frame.getContentPane().add(titulo);
	}
	
	private void addActionListenerToButtons() {
		boton1.addActionListener(this);
		boton2.addActionListener(this);
		boton3.addActionListener(this);
		boton4.addActionListener(this);
		boton5.addActionListener(this);
		boton6.addActionListener(this);
		boton7.addActionListener(this);
		boton8.addActionListener(this);
		boton9.addActionListener(this);
		boton0.addActionListener(this);
		botonB.addActionListener(this);
		botonBT.addActionListener(this);
		botonAC.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==boton1)
			AreaTexto.setText(AreaTexto.getText()+'1');
		if (e.getSource()==boton2)
			AreaTexto.setText(AreaTexto.getText()+'2');
		if (e.getSource()==boton3)
			AreaTexto.setText(AreaTexto.getText()+'3');
		if (e.getSource()==boton4)
			AreaTexto.setText(AreaTexto.getText()+'4');
		if (e.getSource()==boton5)
			AreaTexto.setText(AreaTexto.getText()+'5');
		if (e.getSource()==boton6)
			AreaTexto.setText(AreaTexto.getText()+'6');
		if (e.getSource()==boton7)
			AreaTexto.setText(AreaTexto.getText()+'7');
		if (e.getSource()==boton8)
			AreaTexto.setText(AreaTexto.getText()+'8');
		if (e.getSource()==boton9)
			AreaTexto.setText(AreaTexto.getText()+'9');
		if (e.getSource()==boton0)
			AreaTexto.setText(AreaTexto.getText()+'0');
		if (e.getSource()==botonB && !AreaTexto.getText().isEmpty())
			AreaTexto.setText(AreaTexto.getText().substring(0,AreaTexto.getText().length()-1));
		if (e.getSource()==botonBT)
			AreaTexto.setText("");
		if (e.getSource()==botonAC) {
			try {
				cc.crearCliente(AreaTexto.getText());
				VentanaEmergente ventanaEmergente= new VentanaEmergente(Constantes.CLIENTE_REGISTRO_OK);
			}catch (DniYaRegistradoException e1){
				VentanaEmergente ventanaEmergente= new VentanaEmergente(Constantes.DNI_YA_REGISTRADO);
			}catch (DniInvalidoException e1) {
				VentanaEmergente ventanaEmergente= new VentanaEmergente(Constantes.DNI_INCORRECTO);
			}catch (IOException e1) {
				VentanaEmergente ve = new VentanaEmergente(Constantes.ERROR_CONEXION);
			}
			AreaTexto.setText("");
		}
	}
}
