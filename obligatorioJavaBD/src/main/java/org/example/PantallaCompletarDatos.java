package org.example;

import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PantallaCompletarDatos {

    private JTextField campoCedula;
    private JTextField campoNombre;
    private JTextField campoFechaNacimiento;
    private JButton botonEnviar;
    private JLabel etiquetaLinkNoCarnet;
    private JButton botonCargarImagen;
    private JTextField campoFechaVencimiento;

    public PantallaCompletarDatos() {
        JFrame frame = new JFrame("Formulario de Datos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Componentes del formulario
        campoCedula = createTextFieldWithPlaceholder("X.XXX.XXX-X", 20);
        campoNombre = createTextFieldWithPlaceholder("Nombres Apellidos", 20);
        campoFechaNacimiento = createTextFieldWithPlaceholder("AAAA-MM-DD", 10);
        campoFechaVencimiento = createTextFieldWithPlaceholder("AAAA-MM-DD", 10);

        JLabel etiquetaCedula = new JLabel("Cedula   ");
        JLabel etiquetaNombre = new JLabel("Nombre   ");
        JLabel etiquetaFechaNacimiento = new JLabel("Fecha de Nacimiento   ");
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

        // Igual q en pantallaLogin
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        addToPanel(panelFormulario, etiquetaCedula, campoCedula, gbc);
        addToPanel(panelFormulario, etiquetaNombre, campoNombre, gbc);
        addToPanel(panelFormulario, etiquetaFechaNacimiento, campoFechaNacimiento, gbc);
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
        //if (resultado == JFileChooser.APPROVE_OPTION) {
        // Aca agregamos cosas para acceder a la imagen, por ahora esto no se toca
        //}
    }

    private void abrirVentanaNoCarnet() {
        //Aca pantalla agenda

        JOptionPane.showMessageDialog(null, "Mensaje: No tengo carnet de salud");
    }

    private void enviarDatos() {
        String cedula = campoCedula.getText();
        String nombre = campoNombre.getText();
        String fechaNacimiento = campoFechaNacimiento.getText();
        String fechaVencimiento = campoFechaVencimiento.getText();

        // Checker que los datos esten bien
        if (validarCedula(cedula)) {
            System.out.println("Cédula válida: " + cedula);
        } else {
            JOptionPane.showMessageDialog(null, "La cédula no tiene un formato válido (X.XXX.XXX-X)");
        }
        if (validarNombre(nombre)) {
            System.out.println("Nombre válido: " + nombre);
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese correctamente su nombre");
        }
        // Repetir con los otros
    }

    private boolean validarCedula(String cedula) {
        //Esto bien nose como funciona pero lo encontre por ahí, funciona q es lo importante, valida q sean números y tenga puntos y guion la cedula
        String forma = "\\d\\.\\d{3}\\.\\d{3}-\\d";
        Pattern pattern = Pattern.compile(forma);
        Matcher matcher = pattern.matcher(cedula);
        return matcher.matches();
    }
    private boolean validarNombre(String nombre) {
        //Que sean solo letras xq los nombres no pueden tener otras cosas
        String noContener = "1234567890!@#$%^&*()_+-=[]{};:,.<>?/|~`\\\"'";
        for (int i = 0; i < nombre.length(); i++) {
            char a = nombre.charAt(i);
            if (noContener.contains(String.valueOf(a))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PantallaCompletarDatos();
        });
    }
}
