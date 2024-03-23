package E4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Clase que representa la ventana gráfica
public class ControladorVehiculo extends JFrame {
    GestorFiltros gestor;
    JLabel valorRev, valorVel;

    public ControladorVehiculo(GestorFiltros gestor) {
        this.gestor = gestor;

        gestor.anadirFiltro(new CalcularVelocidad());
        gestor.anadirFiltro(new RepercutirRozamiento());

        // Configuración de la ventana
        setTitle("Controlador de Vehículo");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel principal para contener los componentes
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // Panel para contener los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1));

        // Crear y agregar botones para cada acción del enum
        for (EstadoMotor accion : EstadoMotor.values()) {
            JButton boton = new JButton(accion.getEtiqueta());
            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Lógica para manejar la acción cuando se presiona el botón
                    gestor.cambio(gestor.cadenaFiltros.objetivo.revoluciones, accion);
                    actualiza(gestor.cadenaFiltros.objetivo);
                }
            });
            panelBotones.add(boton);
        }

        // Cuentarrevoluciones
        JPanel panelCuentaRev = new JPanel();
        panelCuentaRev.setLayout(new FlowLayout());
        JLabel tituloRev = new JLabel("RPM:");
        valorRev = new JLabel();
        panelCuentaRev.add(tituloRev);
        panelCuentaRev.add(valorRev);

        //Velocímetro
        JPanel panelVeloz = new JPanel();
        panelVeloz.setLayout(new FlowLayout());
        JLabel tituloVel = new JLabel("Km/h:");
        valorVel = new JLabel();
        panelVeloz.add(tituloVel);
        panelVeloz.add(valorVel);

        // Agregar paneles al panel principal
        panelPrincipal.add(panelBotones, BorderLayout.NORTH);
        panelPrincipal.add(panelCuentaRev, BorderLayout.CENTER);
        panelPrincipal.add(panelVeloz, BorderLayout.SOUTH);

        // Agregar el panel principal a la ventana
        getContentPane().add(panelPrincipal);
        setVisible(true);
    }

    // Método para actualizar el valor mostrado en el JLabel
    public void actualiza(Objetivo obj) {
        valorRev.setText(Double.toString(obj.revoluciones));
        valorVel.setText(Double.toString(obj.velocidad));
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
