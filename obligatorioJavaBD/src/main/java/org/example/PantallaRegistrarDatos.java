package org.example;

import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PantallaRegistrarDatos {

    private JTextField campoCedula;
    private JTextField campoNombre;
    private JTextField campoFechaNacimiento;
    private JTextField campoDomicilio;
    private JTextField campoCorreo;
    private JTextField campoTelefono;
    private JButton botonEnviar;
    private JTextField campoFechaVencimiento;
    private JButton botonCargarImagen;
    private JLabel etiquetaLinkNoCarnet;

    public PantallaRegistrarDatos() {
        JFrame frame = new JFrame("Formulario de Registro");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        campoCedula = createTextFieldWithPlaceholder("X.XXX.XXX-X", 20);
        campoNombre = createTextFieldWithPlaceholder("Nombres Apellidos", 20);
        campoFechaNacimiento = createTextFieldWithPlaceholder("AAAA-MM-DD", 10);
        campoDomicilio = createTextFieldWithPlaceholder("Dirección", 20);
        campoCorreo = createTextFieldWithPlaceholder("Correo electrónico", 20);
        campoTelefono = createTextFieldWithPlaceholder("Teléfono", 20);
        campoFechaVencimiento = createTextFieldWithPlaceholder("AAAA-MM-DD", 10);

        JLabel etiquetaCedula = new JLabel("Cédula   ");
        JLabel etiquetaNombre = new JLabel("Nombre   ");
        JLabel etiquetaFechaNacimiento = new JLabel("Fecha de Nacimiento   ");
        JLabel etiquetaDomicilio = new JLabel("Domicilio   ");
        JLabel etiquetaCorreo = new JLabel("Correo   ");
        JLabel etiquetaTelefono = new JLabel("Teléfono   ");
        JLabel etiquetaImagen = new JLabel("Carnet de salud   ");
        JLabel etiquetaFechaVencimiento = new JLabel("Fecha vencimiento carnet   ");

        botonCargarImagen = new JButton("Adjuntar comprobante");
        botonCargarImagen.addActionListener(e -> cargarImagen());

        etiquetaLinkNoCarnet = new JLabel("<html><u>No tengo carnet de salud</u></html>");
        etiquetaLinkNoCarnet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        etiquetaLinkNoCarnet.setForeground(Color.BLUE);
        etiquetaLinkNoCarnet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                abrirVentanaNoCarnet();
            }
        });

        botonEnviar = new JButton("Enviar");
        botonEnviar.addActionListener(e -> enviarDatos());

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        addToPanel(panelFormulario, etiquetaCedula, campoCedula, gbc);
        addToPanel(panelFormulario, etiquetaNombre, campoNombre, gbc);
        addToPanel(panelFormulario, etiquetaFechaNacimiento, campoFechaNacimiento, gbc);
        addToPanel(panelFormulario, etiquetaDomicilio, campoDomicilio, gbc);
        addToPanel(panelFormulario, etiquetaCorreo, campoCorreo, gbc);
        addToPanel(panelFormulario, etiquetaTelefono, campoTelefono, gbc);
        addToPanel(panelFormulario, etiquetaImagen, botonCargarImagen, gbc);
        addToPanel(panelFormulario, etiquetaFechaVencimiento, campoFechaVencimiento, gbc);
        addToPanel(panelFormulario, etiquetaLinkNoCarnet, null, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panelFormulario.add(botonEnviar, gbc);

        frame.add(panelFormulario);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JTextField createTextFieldWithPlaceholder(String placeholder, int columns) {
        JTextField textField = new JTextField(columns);
        textField.setText(placeholder);
        textField.setForeground(Color.GRAY);

        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });

        return textField;
    }

    private void addToPanel(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.weightx = 0.0;
        panel.add(label, gbc);

        if (component != null) {
            gbc.gridx = 1;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(component, gbc);
        }
    }

    private void cargarImagen() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Imagen", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int resultado = fileChooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
        }
    }

    private void abrirVentanaNoCarnet() {
        JOptionPane.showMessageDialog(null, "Mensaje: No tengo carnet de salud");
    }

    private void enviarDatos() {
        String cedula = campoCedula.getText();
        String nombre = campoNombre.getText();
        String fechaNacimiento = campoFechaNacimiento.getText();
        String domicilio = campoDomicilio.getText();
        String correo = campoCorreo.getText();
        String telefono = campoTelefono.getText();
        String fechaVencimiento = campoFechaVencimiento.getText();

        System.out.println("Cédula: " + cedula);
        System.out.println("Nombre: " + nombre);
        System.out.println("Fecha de Nacimiento: " + fechaNacimiento);
        System.out.println("Domicilio: " + domicilio);
        System.out.println("Correo: " + correo);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Fecha Vencimiento Carnet: " + fechaVencimiento);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PantallaRegistrarDatos();
        });
    }
}

//por ahora esto igual que CompletarDatos dsp vemos