package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Modelo: contiene los datos y la lógica del programa
class Modelo {
    private int contador = 0;

    public int getContador() {
        return contador;
    }

    public void incrementarContador() {
        contador++;
    }
}

// Vista: representa la interfaz gráfica de usuario
class Vista {
    private JFrame frame;
    private JLabel label;
    private JButton button;

    public Vista() {
        frame = new JFrame("Contador MVC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        label = new JLabel("Contador: 0", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(200, 50));
        frame.getContentPane().add(label, BorderLayout.CENTER);

        button = new JButton("Incrementar");
        frame.getContentPane().add(button, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }

    public void actualizarContador(int contador) {
        label.setText("Contador: " + contador);
    }

    public void agregarControlador(ActionListener controlador) {
        button.addActionListener(controlador);
    }
}

// Controlador: maneja las interacciones del usuario y actualiza el modelo y la vista
class Controlador implements ActionListener {
    private Modelo modelo;
    private Vista vista;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.agregarControlador(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        modelo.incrementarContador();
        vista.actualizarContador(modelo.getContador());
    }
    public static void main(String[] args) {
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador(modelo, vista);
    }
}

// Clase principal que inicia la aplicación


