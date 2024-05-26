package vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import util.Constantes;
import util.EscritorArchivoTexto;

public class VistaConfiguracion {
	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtPanel;
	private JTextField txtSeleccioneFormatoDe;
	private JTextField txtSeleccioneElTipo;
	private JLabel titulo;
	private JList<String> listaIzquierda;
	private JList<String> listaDerecha;
	 public VistaConfiguracion() {
	        frame = new JFrame();
	        frame.setTitle("Panel de configuracion");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(600, 400);

	        // Panel principal con BorderLayout
	        JPanel panelPrincipal = new JPanel(new BorderLayout());

	        // Título
	        JLabel titulo = new JLabel("Panel de configuracion", SwingConstants.CENTER);
	        titulo.setFont(new Font("Tahoma", Font.PLAIN, 19));
	        panelPrincipal.add(titulo, BorderLayout.NORTH);

	        // Panel central con GridBagLayout para tener más control sobre el tamaño de las opciones
	        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 5, 5)); // 1 fila, 2 columnas, 5px de espacio horizontal y vertical
	        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Agregar un borde alrededor del panel

	        // Panel izquierdo con JList
	        String[] opcionesIzquierdas = {"TXT", "JSON", "XML"};
	        listaIzquierda = new JList<>(opcionesIzquierdas);
	        listaIzquierda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        listaIzquierda.setSelectedIndex(0); // Preseleccionar la primera opción
	        JPanel panelIzquierdo = new JPanel(new BorderLayout());
	        panelIzquierdo.setBorder(BorderFactory.createTitledBorder("Seleccione el formato de archivo"));
	        panelIzquierdo.add(new JScrollPane(listaIzquierda), BorderLayout.CENTER);
	        panelCentral.add(panelIzquierdo);

	        // Panel derecho con JList
	        String[] opcionesDerechas = {Constantes.ORDEN_DE_LLEGADA, Constantes.ORDEN_GRUPO_AFINIDAD, Constantes.ORDEN_GRUPO_ETARIO};
	        listaDerecha = new JList<>(opcionesDerechas);
	        listaDerecha.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        listaDerecha.setSelectedIndex(0); // Preseleccionar la primera opción
	        JPanel panelDerecho = new JPanel(new BorderLayout());
	        panelDerecho.setBorder(BorderFactory.createTitledBorder("Seleccione el tipo de atencion"));
	        panelDerecho.add(new JScrollPane(listaDerecha), BorderLayout.CENTER);
	        panelCentral.add(panelDerecho);

	        // Agregar panel central al panel principal
	        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

	        // Panel inferior para el botón aceptar
	        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.CENTER)); // FlowLayout con centrado horizontal
	        JButton botonAceptar = new JButton("Aceptar");
	        
	        botonAceptar.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Verificar si se ha seleccionado un elemento en la lista izquierda
	                if (!listaIzquierda.isSelectionEmpty()) {
	                    int indiceSeleccionado = listaIzquierda.getSelectedIndex();
	                    String elementoSeleccionado = listaIzquierda.getModel().getElementAt(indiceSeleccionado);
	                    
	                    System.out.println("Formato de texto seleccionado: " + elementoSeleccionado);
	                }

	                // Verificar si se ha seleccionado un elemento en la lista derecha
	                if (!listaDerecha.isSelectionEmpty()) {
	                    int indiceSeleccionado = listaDerecha.getSelectedIndex();
	                    String elementoSeleccionado = listaDerecha.getModel().getElementAt(indiceSeleccionado);

	                    System.out.println("Tipo de atención seleccionado: " + elementoSeleccionado);
	                    try {
	                        EscritorArchivoTexto.escribirArchivo(elementoSeleccionado, "Strategy.txt");
	                        System.out.println("Contenido escrito en el archivo exitosamente.");
	                    } catch (IOException e1) {
	                        e1.printStackTrace();
	                        System.out.println("Error al escribir en el archivo.");
	                    }
	                
	                }
	            }
	        });
	        // Ajustar el tamaño del botón
	        botonAceptar.setPreferredSize(new Dimension(100, 30));

	        panelInferior.add(botonAceptar);
	        // Agregar panel inferior al panel principal
	        panelPrincipal.add(panelInferior, BorderLayout.SOUTH);

	        // Añadir el panel central al panel principal
	        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
	        frame.add(panelPrincipal);
	        frame.setVisible(true);
	    }
}
