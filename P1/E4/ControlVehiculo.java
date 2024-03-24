package E4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

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
    private Timer acelerarTimer;
    private Timer frenarTimer;

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
                    estadoLabel.setForeground(Color.GREEN);
                    encenderButton.setText("Apagar");
                    encenderButton.setForeground(Color.RED);
                    acelerarButton.setEnabled(true);
                    frenarButton.setEnabled(true);
                    gestor.cambio(gestor.cadenaFiltros.objetivo.revoluciones, EstadoMotor.ENCENDIDO);
                    actualizarSalpicadero();
                } else {
                    estadoLabel.setText("APAGADO");
                    estadoLabel.setForeground(Color.BLACK);
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

        // Crear el Timer para acelerar
        acelerarTimer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestor.cambio(gestor.cadenaFiltros.objetivo.revoluciones, EstadoMotor.ACELERANDO);
                actualizarSalpicadero();
            }
        });

        // Comportamiento al pulsar y soltar el botón de acelerar
        acelerarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (frenarButton.isSelected()) {
                    estadoLabel.setText("ACELERANDO");
                    estadoLabel.setForeground(Color.RED);
                    frenarButton.setText("Frenar");
                    frenarButton.setForeground(Color.BLACK);
                    frenarTimer.stop();
                    frenarButton.setSelected(false);
                }
                
                if (acelerarButton.isSelected()) {
                    estadoLabel.setText("ACELERANDO");
                    estadoLabel.setForeground(Color.RED);
                    acelerarButton.setText("Soltar Acelerador");
                    acelerarButton.setForeground(Color.RED);
                    frenarButton.setSelected(false);
                    acelerarTimer.start();
                } else {
                    estadoLabel.setText("ENCENDIDO");
                    estadoLabel.setForeground(Color.GREEN);
                    acelerarButton.setText("Acelerar");
                    acelerarButton.setForeground(Color.BLACK);
                    acelerarTimer.stop();
                }
            }
        });

        // Crear el Timer para frenar
        frenarTimer = new Timer(100, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gestor.cambio(gestor.cadenaFiltros.objetivo.revoluciones, EstadoMotor.FRENANDO);
                actualizarSalpicadero();
            }
        });

        // Comportamiento al pulsar y soltar el botón de frenar
        frenarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (acelerarButton.isSelected()) {
                    estadoLabel.setText("FRENANDO");
                    estadoLabel.setForeground(Color.RED);
                    acelerarButton.setText("Acelerar");
                    acelerarButton.setForeground(Color.BLACK);
                    acelerarTimer.stop();
                    acelerarButton.setSelected(false);
                }
                if (frenarButton.isSelected()) {
                    estadoLabel.setText("FRENANDO");
                    estadoLabel.setForeground(Color.RED);
                    frenarButton.setText("Soltar Freno");
                    frenarButton.setForeground(Color.RED);
                    acelerarButton.setSelected(false);
                    frenarTimer.start();
                } else {
                    estadoLabel.setText("ENCENDIDO");
                    estadoLabel.setForeground(Color.GREEN);
                    frenarButton.setText("Frenar");
                    frenarButton.setForeground(Color.BLACK);
                    frenarTimer.stop();
                }
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
        DecimalFormat formato = new DecimalFormat("#.###");
        velocidadLabel.setText("km/h: " + formato.format(gestor.getCadenaFiltros().getObjetivo().getVelocidad()));
        contadorRecienteLabel.setText("Contador Reciente: " + formato.format(gestor.getCadenaFiltros().getObjetivo().getDistanciaActual()) + " km");
        contadorRealLabel.setText("Contador Real: " + formato.format(gestor.getCadenaFiltros().getObjetivo().getDistanciaReal()) + " km");
        rpmLabel.setText("RPM: " + formato.format(gestor.getCadenaFiltros().getObjetivo().getRevoluciones()));
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
