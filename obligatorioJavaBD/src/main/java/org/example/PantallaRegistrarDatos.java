package org.example;

import javax.swing.*;
import java.awt.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PantallaRegistrarDatos {

    private JTextField campoCuenta;
    private JTextField campoContra;
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

        campoCuenta = createTextFieldWithPlaceholder("ejemplo123", 20);
        campoContra = createTextFieldWithPlaceholder("********", 20);
        campoCedula = createTextFieldWithPlaceholder("X.XXX.XXX-X", 20);
        campoNombre = createTextFieldWithPlaceholder("Nombres Apellidos", 20);
        campoFechaNacimiento = createTextFieldWithPlaceholder("AAAA-MM-DD", 10);
        campoDomicilio = createTextFieldWithPlaceholder("Dirección", 20);
        campoCorreo = createTextFieldWithPlaceholder("Correo electrónico", 20);
        campoTelefono = createTextFieldWithPlaceholder("Teléfono", 20);
        campoFechaVencimiento = createTextFieldWithPlaceholder("AAAA-MM-DD", 10);

        JLabel etiquetaCuenta = new JLabel("Cuenta   ");
        JLabel etiquetaContra = new JLabel("Contraseña   ");
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

        addToPanel(panelFormulario, etiquetaCuenta, campoCuenta, gbc);
        addToPanel(panelFormulario, etiquetaContra, campoContra, gbc);
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
        boolean datosCorrectos = false;
        boolean errorEncontrado = false;


        while (!datosCorrectos && !errorEncontrado) {
            String cuenta = campoCuenta.getText();
            String contra = campoContra.getText();
            String cedula = campoCedula.getText();
            String nombre = campoNombre.getText();
            String fechaNacimiento = campoFechaNacimiento.getText();
            String domicilio = campoDomicilio.getText();
            String correo = campoCorreo.getText();
            String telefono = campoTelefono.getText();
            String fechaVencimiento = campoFechaVencimiento.getText();

            if (validarCuenta(cuenta) && validarCuentaEspacios(cuenta) && validarContra(contra) && validarContraEspacios(contra) && validarCedula(cedula) && validarNombre(nombre) && validarFechaNacimiento(fechaNacimiento) && validarDomicilio(domicilio) && validarCorreo(correo) && validarTelefono(telefono) && validarFechaFormato(fechaVencimiento) && validarVencimientoFecha(fechaVencimiento)) {
                datosCorrectos = true;

                try {

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/obligatorioBD", "root", "bernardo");

                    String sqlLogin = "INSERT INTO Login (LogId, Contra) VALUES (?, ?)";
                    try (PreparedStatement pstmtLogin = conexion.prepareStatement(sqlLogin)) {
                        pstmtLogin.setString(1, cuenta);
                        pstmtLogin.setString(2, contra);
                        pstmtLogin.executeUpdate();
                    }

                    // Insertar datos en la tabla Funcionario
                    String sqlFuncionario = "INSERT INTO Funcionario (Ci, Nombre, Fch_Nacimiento, Dirección, Teléfono, Email, LogId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmtFuncionario = conexion.prepareStatement(sqlFuncionario)) {
                        pstmtFuncionario.setString(1, cedula);
                        pstmtFuncionario.setString(2, nombre);
                        pstmtFuncionario.setString(4, fechaNacimiento);
                        pstmtFuncionario.setString(5, domicilio);
                        pstmtFuncionario.setString(6, telefono);
                        pstmtFuncionario.setString(7, correo);
                        pstmtFuncionario.setString(8, cuenta); // Asociar el Funcionario con el Login
                        pstmtFuncionario.executeUpdate();

                    }
                    conexion.close();

                    System.out.println("Datos insertados correctamente.");

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {

                errorEncontrado = true;
                if (!validarCuenta(cuenta)) {
                    JOptionPane.showMessageDialog(null, "Su nombre de usuario no puede tener caracteres raros");
                    campoCuenta.setText(cuenta);
                }else if (!validarCuentaEspacios(cuenta)) {
                    JOptionPane.showMessageDialog(null, "Su nombre de usuario no puede tener espacios");
                    campoCuenta.setText(cuenta);
                }else if (!validarContra(contra)) {
                    JOptionPane.showMessageDialog(null, "Su contraseña no puede tener caracteres extraños");
                    campoContra.setText(contra);
                }else if (!validarContraEspacios(contra)) {
                    JOptionPane.showMessageDialog(null, "Su contraseña no puede tener espacios");
                    campoContra.setText(contra);
                }else if (!validarCedula(cedula)) {
                    JOptionPane.showMessageDialog(null, "Su cédula no tiene un formato válido (X.XXX.XXX-X)");
                    campoCedula.setText(cedula);
                } else if (!validarNombre(nombre)) {
                    JOptionPane.showMessageDialog(null, "Ingrese correctamente su nombre");
                    campoNombre.setText(nombre);
                } else if (!validarFechaNacimiento(fechaNacimiento)) {
                    JOptionPane.showMessageDialog(null, "Su fecha de nacimiento está mal o no tiene el formato válido (AAAA-MM-DD)");
                    campoFechaNacimiento.setText(fechaNacimiento);
                } else if (!validarDomicilio(domicilio)) {
                    JOptionPane.showMessageDialog(null, "Ingrese un domicilio existente");
                    campoDomicilio.setText(domicilio);
                } else if (!validarCorreo(correo)) {
                    JOptionPane.showMessageDialog(null, "Ingrese su correo correctamente");
                    campoCorreo.setText(correo);
                } else if (!validarTelefono(telefono)) {
                    JOptionPane.showMessageDialog(null, "Ingrese su telefono correctamente");
                    campoTelefono.setText(telefono);
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

    private boolean validarCuenta(String cuenta) {
        //Que sean solo letras y números xq los nombres no pueden tener otras cosas
        String noContener = "!@#$%^&*()_+-=[]{};:,.<>?/|~`\\\"'";
        for (int i = 0; i < cuenta.length(); i++) {
            char a = cuenta.charAt(i);
            if (noContener.contains(String.valueOf(a))) {
                return false;
            }
        }
        return true;
    }
    private boolean validarCuentaEspacios(String nombre) {
        return !nombre.contains(" ");
    }
    private boolean validarContra(String contra) {
        //Que sean solo letras y números xq los nombres no pueden tener otras cosas
        String noContener = "!@#$%^&*()_+-=[]{};:,.<>?/|~`\\\"'";
        for (int i = 0; i < contra.length(); i++) {
            char a = contra.charAt(i);
            if (noContener.contains(String.valueOf(a))) {
                return false;
            }
        }
        return true;
    }
    private boolean validarContraEspacios(String contra) {
        return !contra.contains(" ");
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
    private boolean validarDomicilio(String domicilio) {
        //Que sean solo letras xq los nombres no pueden tener otras cosas
        String noContener = "!@#$%^&*()_+-=[]{};:,.<>?/|~`\\\"'";
        for (int i = 0; i < domicilio.length(); i++) {
            char a = domicilio.charAt(i);
            if (noContener.contains(String.valueOf(a))) {
                return false;
            }
        }
        return true;
    }
    private boolean validarCorreo(String correo) {
        if (!correo.endsWith("@gmail.com")) {
            return false;
        }
        String noContener = "!#$%^&*()_+-=[]{};:,<>?/|~`\\\"'";
        for (int i = 0; i < correo.length(); i++) {
            char a = correo.charAt(i);
            if (Character.isWhitespace(a) || noContener.contains(String.valueOf(a))) {
                return false;
            }
        }
        return true;
    }
    private boolean validarTelefono(String telefono) {
        for (int i = 0; i < telefono.length(); i++) {
            char a = telefono.charAt(i);
            if (!Character.isDigit(a)) {
                return false;
            }
        }
        if (telefono.length() != 9) {
            return false;
        }
        if (!telefono.startsWith("09")) {
            return false;
        }

        return true;
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
            new PantallaRegistrarDatos();
        });
    }
}

//por ahora esto igual que CompletarDatos dsp vemos