package E4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ControlVehiculo extends JFrame {
    private JPanel panelMandos;
    private JLabel estadoLabel;
    private JToggleButton encenderButton;
    private JToggleButton acelerarButton;
    private JToggleButton frenarButton;
    private JPanel salpicaderoPanel;
    private JLabel velocidadLabel;
    private JLabel contadorRecienteLabel;
    private JLabel contadorRealLabel;
    private JLabel rpmLabel;

    private GestorFiltros gestor;

    public ControlVehiculo(GestorFiltros gestor) {
        this.gestor = gestor;

        gestor.anadirFiltro(new CalcularVelocidad());
        gestor.anadirFiltro(new RepercutirRozamiento());

        setTitle("Control del Vehículo");
        setSize(500, 500); // Aumentamos el alto para dar espacio al salpicadero
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Arial", Font.PLAIN, 18);

        panelMandos = new JPanel();
        estadoLabel = new JLabel("APAGADO", SwingConstants.CENTER); // Texto centrado
        encenderButton = new JToggleButton("Encender");
        encenderButton.setFont(font);
        acelerarButton = new JToggleButton("Acelerar");
        acelerarButton.setFont(font);
        frenarButton = new JToggleButton("Frenar");
        frenarButton.setFont(font);

        estadoLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // Cambiar apariencia de los botones
        encenderButton.setForeground(Color.GREEN);
        acelerarButton.setForeground(Color.BLACK);
        frenarButton.setForeground(Color.BLACK);

        // Cambiar texto del botón Encender cuando está seleccionado
        encenderButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (encenderButton.isSelected()) {
                    estadoLabel.setText("ENCENDIDO");
                    encenderButton.setText("Apagar");
                    encenderButton.setForeground(Color.RED);
                    acelerarButton.setEnabled(true);
                    frenarButton.setEnabled(true);
                    gestor.cambio(gestor.cadenaFiltros.objetivo.revoluciones, EstadoMotor.ENCENDIDO);
                    actualizarSalpicadero();
                } else {
                    estadoLabel.setText("APAGADO");
                    encenderButton.setText("Encender");
                    encenderButton.setForeground(Color.GREEN);
                    acelerarButton.setEnabled(false);
                    frenarButton.setEnabled(false);
                    acelerarButton.setSelected(false);
                    frenarButton.setSelected(false);
                    acelerarButton.setText("Acelerar");
                    frenarButton.setText("Frenar");
                    acelerarButton.setForeground(Color.BLACK);
                    frenarButton.setForeground(Color.BLACK);
                    gestor.cambio(gestor.cadenaFiltros.objetivo.revoluciones, EstadoMotor.APAGADO);
                    actualizarSalpicadero();
                }
            }
        });

        // Cambiar texto del botón Acelerar cuando está seleccionado
        acelerarButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (acelerarButton.isSelected() && encenderButton.isSelected()) {
                    estadoLabel.setText("ACELERANDO");
                    acelerarButton.setText("Soltar Acelerador");
                    acelerarButton.setForeground(Color.RED);
                    frenarButton.setSelected(false);
                    gestor.cambio(gestor.cadenaFiltros.objetivo.revoluciones, EstadoMotor.ACELERANDO);
                    actualizarSalpicadero();

                } else if (!acelerarButton.isSelected() && encenderButton.isSelected()) {
                    estadoLabel.setText("ENCENDIDO");
                    acelerarButton.setText("Acelerar");
                    acelerarButton.setForeground(Color.BLACK);

                }
            }
        });

        // Cambiar texto del botón Frenar cuando está seleccionado
        frenarButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (frenarButton.isSelected() && encenderButton.isSelected()) {
                    estadoLabel.setText("FRENANDO");
                    frenarButton.setText("Soltar Frenar");
                    frenarButton.setForeground(Color.RED);
                    acelerarButton.setSelected(false);

                } else if (!frenarButton.isSelected() && encenderButton.isSelected()) {
                    estadoLabel.setText("ENCENDIDO");
                    frenarButton.setText("Frenar");
                    frenarButton.setForeground(Color.BLACK);

                }
                // Actualizar el salpicadero
                gestor.cambio(gestor.cadenaFiltros.objetivo.revoluciones, EstadoMotor.FRENANDO);
                actualizarSalpicadero();
            }
        });

        panelMandos.setLayout(new BorderLayout());
        panelMandos.add(estadoLabel, BorderLayout.NORTH);
        
        JPanel botonesPanel = new JPanel(new FlowLayout());
        botonesPanel.add(encenderButton);
        botonesPanel.add(acelerarButton);
        botonesPanel.add(frenarButton);
        panelMandos.add(botonesPanel, BorderLayout.CENTER);

        // Crear el panel del salpicadero
        salpicaderoPanel = new JPanel(new GridLayout(3, 1)); // 3 filas, 1 columna
        
        // Velocímetro
        velocidadLabel = new JLabel("km/h: ");
        velocidadLabel.setFont(font);
        salpicaderoPanel.add(velocidadLabel);

        // CuentaKilómetros
        JPanel cuentaKilometrosPanel = new JPanel(new GridLayout(2, 1)); // 2 filas, 1 columna
        contadorRecienteLabel = new JLabel("Contador Reciente: ");
        contadorRecienteLabel.setFont(font);
        contadorRealLabel = new JLabel("Contador Real: ");
        contadorRealLabel.setFont(font);
        cuentaKilometrosPanel.add(contadorRecienteLabel);
        cuentaKilometrosPanel.add(contadorRealLabel);
        salpicaderoPanel.add(cuentaKilometrosPanel);

        // Cuentarrevoluciones
        rpmLabel = new JLabel("RPM: ");
        rpmLabel.setFont(font);
        salpicaderoPanel.add(rpmLabel);

        add(panelMandos, BorderLayout.NORTH);
        add(salpicaderoPanel, BorderLayout.CENTER);
    }

    // Método para actualizar el salpicadero con los valores del GestorFiltros
    private void actualizarSalpicadero() {
        velocidadLabel.setText("km/h: " + Double.toString(gestor.getCadenaFiltros().getObjetivo().getVelocidad()) );
        contadorRecienteLabel.setText("Contador Reciente: " + Double.toString(gestor.getCadenaFiltros().getObjetivo().getDistancia()));
        contadorRealLabel.setText("Contador Real: " + Double.toString(gestor.getCadenaFiltros().getObjetivo().getDistancia()));
        rpmLabel.setText("RPM: " + Double.toString(gestor.getCadenaFiltros().getObjetivo().getRevoluciones()));
    }

    public static void main(String[] args) {
        Objetivo salpicadero = new Objetivo();
        GestorFiltros gestor = new GestorFiltros(salpicadero);
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ControlVehiculo controlVehiculo = new ControlVehiculo(gestor);
                controlVehiculo.setVisible(true);
            }
        });
    }
}
