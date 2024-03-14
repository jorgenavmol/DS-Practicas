package E4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Clase que representa la ventana gráfica
public class ControladorVehiculo extends JFrame {
    GestorFiltros gestor;

    public ControladorVehiculo(GestorFiltros gestor) {
        this.gestor = gestor;

        gestor.anadirFiltro(new CalcularVelocidad());
        gestor.anadirFiltro(new RepercutirRozamiento());

        // Configuración de la ventana
        setTitle("Controlador de Vehículo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel para contener los botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        // Crear y agregar botones para cada acción del enum
        for (EstadoMotor accion : EstadoMotor.values()) {
            JButton boton = new JButton(accion.getEtiqueta());
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para manejar la acción cuando se presiona el botón
                    gestor.cambio(gestor.cadenaFiltros.objetivo.revoluciones, accion);
                    JOptionPane.showMessageDialog(null, "Realizando acción: " + accion.getEtiqueta());
                }
            });
            panel.add(boton);
        }

        // Agregar el panel a la ventana
        getContentPane().add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Objetivo salpicadero = new Objetivo();
        GestorFiltros gestor = new GestorFiltros(salpicadero);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ControladorVehiculo(gestor);
            }
        });
    }
}