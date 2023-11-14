package org.example;

import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        boolean datosCorrectos = false;
        boolean errorEncontrado = false;

        while (!datosCorrectos && !errorEncontrado) {
            String cedula = campoCedula.getText();
            String nombre = campoNombre.getText();
            String fechaNacimiento = campoFechaNacimiento.getText();
            String fechaVencimiento = campoFechaVencimiento.getText();

            if (validarCedula(cedula) && validarNombre(nombre) && validarFechaNacimiento(fechaNacimiento) && validarFechaFormato(fechaVencimiento) && validarVencimientoFecha(fechaVencimiento)) {
                datosCorrectos = true;
                System.out.println("Cedula: " + cedula);
                System.out.println("Nombre: " + nombre);
                System.out.println("Fecha Nacimiento: " + fechaNacimiento);
                System.out.println("Fecha carnet: " + fechaVencimiento);
            } else {
                errorEncontrado = true;

                if (!validarCedula(cedula)) {
                    JOptionPane.showMessageDialog(null, "Su cédula no tiene un formato válido (X.XXX.XXX-X)");
                    campoCedula.setText(cedula);
                } else if (!validarNombre(nombre)) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente su nombre");
                    campoNombre.setText(nombre);
                } else if (!validarFechaNacimiento(fechaNacimiento)) {
                    JOptionPane.showMessageDialog(null, "Su fecha de nacimiento está mal o no tiene el formato válido (AAAA-MM-DD)");
                    campoFechaNacimiento.setText(fechaNacimiento);
                } else if (!validarFechaFormato(fechaVencimiento)) {
                    JOptionPane.showMessageDialog(null, "La fecha de vencimiento de su carnet no tiene un formato válido (AAAA-MM-DD) o no existe");
                    campoFechaVencimiento.setText(fechaVencimiento);
                } else if (!validarVencimientoFecha(fechaVencimiento)) {
                    JOptionPane.showMessageDialog(null, "Su carnet está vencido, haga click aquí para renovar su carnet");
                    campoFechaVencimiento.setText(fechaVencimiento);
                }
            }
        }
        if(!errorEncontrado){
            JDialog dialog = new JDialog();
            dialog.setLayout(new BorderLayout());

            JPanel contentPanel = new JPanel(new GridLayout(2, 1)); // Panel para organizar el contenido
            contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel label = new JLabel("Se han actualizado sus datos con éxito!");
            label.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el mensaje
            contentPanel.add(label);

            JPanel buttonPanel = new JPanel(new FlowLayout()); // Panel para el botón
            JButton okButton = new JButton("Ok");
            okButton.addActionListener(e -> {
                dialog.setVisible(false);
                dialog.dispose();
                System.exit(0); // Esto cierra la aplicación
            });
            buttonPanel.add(okButton);

            contentPanel.add(buttonPanel);
            dialog.add(contentPanel, BorderLayout.CENTER);

            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        }
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
    private static boolean validarFechaNacimiento(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(fecha);
            Date hoyDate = new Date();

            if (date.after(hoyDate)) { //Fecha anterior a la de hoy
                return false;
            }

            // Validar q la fecha tenga sentido y no se agrege por ejemplo dia 30 en el mes 2
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int ano = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH) + 1; // El mes arranca en 0 nose xq
            int dia = cal.get(Calendar.DAY_OF_MONTH);

            if (ano < 0 || mes > 12 || mes < 1 || dia < 1 || dia > 31) {
                return false;
            }

            //Febrero
            if (mes == 2) {
                boolean esBisiesto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
                if (esBisiesto && dia > 29) {
                    return false;
                } else if (dia > 28) {
                    return false;
                }
                //Meses con 31 dias
            } else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
                return false;
            }

            return true;

        } catch (ParseException e) {
            return false;
        }
    }
    private static boolean validarFechaFormato(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(fecha);
            Date hoyDate = new Date();

            // Validar q la fecha tenga sentido y no se agrege por ejemplo dia 30 en el mes 2
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int ano = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH) + 1; // El mes arranca en 0 nose xq
            int dia = cal.get(Calendar.DAY_OF_MONTH);

            if (ano < 0 || mes > 12 || mes < 1 || dia < 1 || dia > 31) {
                return false;
            }

            //Febrero
            if (mes == 2) {
                boolean esBisiesto = (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
                if (esBisiesto && dia > 29) {
                    return false;
                } else if (dia > 28) {
                    return false;
                }
                //Meses con 31 dias
            } else if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
                return false;
            }

            return true;

        } catch (ParseException e) {
            return false;
        }
    }
    private static boolean validarVencimientoFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(fecha);
            Date currentDate = new Date();

            return date.after(currentDate);
        } catch (ParseException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PantallaCompletarDatos();
        });
    }
}
