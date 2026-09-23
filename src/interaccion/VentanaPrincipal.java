package interaccion;

import gestion.Parqueadero;
import gestion.TipoPago;
import gestion.Pago;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;

public class VentanaPrincipal extends JFrame {

    private Parqueadero parqueadero;
    private JTextArea consolaVisual;

    public VentanaPrincipal() {
        parqueadero = new Parqueadero(23, 40.0);

        setTitle("Sistema de Parqueadero de Motos");
        setSize(500, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 5, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));

        JTextField txtPlaca = new JTextField();
        JTextField txtMarca = new JTextField();
        JTextField txtId = new JTextField();
        JComboBox<TipoPago> cbxTipoPago = new JComboBox<>(TipoPago.values());

        panelFormulario.add(new JLabel("Placa de la moto:"));
        panelFormulario.add(txtPlaca);
        panelFormulario.add(new JLabel("Marca:"));
        panelFormulario.add(txtMarca);
        panelFormulario.add(new JLabel("ID del Dueño:"));
        panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Medio de Pago:"));
        panelFormulario.add(cbxTipoPago);

        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnIngreso = new JButton("1. Registrar Ingreso");
        JButton btnSalida = new JButton("2. Registrar Salida");
        JButton btnReporte = new JButton("3. Ver Reporte");

        panelBotones.add(btnIngreso);
        panelBotones.add(btnSalida);
        panelBotones.add(btnReporte);

        consolaVisual = new JTextArea(12, 40);
        consolaVisual.setEditable(false);
        consolaVisual.setBackground(Color.BLACK);
        consolaVisual.setForeground(Color.GREEN);
        consolaVisual.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollConsola = new JScrollPane(consolaVisual);
        scrollConsola.setBorder(BorderFactory.createTitledBorder("Registro de Operaciones (Consola)"));

        redirigirConsola();

        btnIngreso.addActionListener(e -> {
            String placa = txtPlaca.getText().trim();
            String marca = txtMarca.getText().trim();
            String id = txtId.getText().trim();

            if (!placa.isEmpty() && !marca.isEmpty() && !id.isEmpty()) {
                boolean exito = parqueadero.registrarIngreso(placa, marca, id);

                if (exito) {
                    System.out.println("Ingreso exitoso - Placa: " + placa + " | ID Dueno: " + id);
                    limpiarCampos(txtPlaca, txtMarca, txtId);
                } else {
                    System.out.println("Error: No se pudo registrar el ingreso. Parqueadero lleno.");
                }
            } else {
                System.out.println("AVISO: Llene Placa, Marca e ID para ingresar.");
            }
        });

        btnSalida.addActionListener(e -> {
            String placa = txtPlaca.getText().trim();

            if (!placa.isEmpty()) {
                TipoPago tipo = (TipoPago) cbxTipoPago.getSelectedItem();
                Pago pagoGenerado = parqueadero.registrarSalida(placa, tipo);

                if (pagoGenerado != null) {
                    System.out.println("Salida registrada para la moto: " + placa);
                    System.out.println("Total cobrado: $" + pagoGenerado.getValor() + " | Metodo: " + tipo);
                    limpiarCampos(txtPlaca, txtMarca, txtId);
                } else {
                    System.out.println("moto no registrada");
                }
            } else {
                System.out.println("AVISO: Ingrese la placa de la moto a retirar.");
            }
        });

        btnReporte.addActionListener(e -> {
            System.out.println("\n--- GENERANDO REPORTE ---");
            System.out.println("Total recaudado en el dia: $" + parqueadero.calcularTotalDia());
        });

        add(panelFormulario, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollConsola, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
    }

    private void limpiarCampos(JTextField placa, JTextField marca, JTextField id) {
        placa.setText("");
        marca.setText("");
        id.setText("");
    }

    private void redirigirConsola() {
        PrintStream printStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                consolaVisual.append(String.valueOf((char) b));
                consolaVisual.setCaretPosition(consolaVisual.getDocument().getLength());
            }
        });
        System.setOut(printStream);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
