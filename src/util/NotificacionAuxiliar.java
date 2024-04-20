package util;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;

public class NotificacionAuxiliar {
	private String dni;
	private int box;
	private JTextField TextoDni,TextoBox;
	private static int y0=99,x0=44,x1=371,ancho1=327,ancho2=340,alto=72;
	
	//x0 y 0 ancho alto
	public NotificacionAuxiliar(String dni,int box) {
		this.dni=dni;
		this.box=box;
		TextoDni=new JTextField();
		TextoDni.setFont(new Font("Tahoma", Font.PLAIN, 55));
		TextoDni.setText(dni);
		TextoDni.setColumns(10);
		TextoDni.setEditable(false);
		TextoDni.setBounds(x0,y0,ancho1,alto);
		TextoDni.setBackground(Color.green);
		
		TextoBox=new JTextField();
		TextoBox.setFont(new Font("Tahoma", Font.PLAIN, 55));
		TextoBox.setText(String.valueOf(box));
		TextoBox.setColumns(10);
		TextoBox.setEditable(false);
		TextoBox.setBounds(x1,y0,ancho2,alto);
		TextoBox.setBackground(Color.green);
	}

	public JTextField getTextoDni() {
		return TextoDni;
	}

	public JTextField getTextoBox() {
		return TextoBox;
	}
	public void desplazamiento() {
		int y=(int)this.TextoBox.getBounds().getY();
		this.TextoDni.setBounds(x0,y+alto,ancho1,alto);
		this.TextoBox.setBounds(x1,y+alto,ancho2,alto);
	}
	public void cambioColor() {
		this.TextoDni.setBackground(Color.gray);
		this.TextoBox.setBackground(Color.gray);
	}

}
