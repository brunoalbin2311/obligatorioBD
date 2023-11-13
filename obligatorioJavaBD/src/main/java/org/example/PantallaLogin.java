package org.example;

import javax.swing.*;
import java.awt.*;

public class PantallaLogin {

    private JTextField campoCuenta;
    private JPasswordField campoContra;
    private JButton botonIniciar;

    public PantallaLogin() {
        JFrame frame = new JFrame("Inicio de Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel etiquetaCuenta = new JLabel("Cuenta ");
        JLabel etiquetaContra = new JLabel("Contraseña ");

        campoCuenta = new JTextField(20);
        campoContra = new JPasswordField(20);

        botonIniciar = new JButton("Iniciar");

        JPanel panelLogin = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        agregarPanel(panelLogin, etiquetaCuenta, campoCuenta, gbc);
        agregarPanel(panelLogin, etiquetaContra, campoContra, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panelLogin.add(botonIniciar, gbc);

        frame.add(panelLogin);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        botonIniciar.addActionListener(e -> iniciarSesion());
    }

    private void agregarPanel(JPanel panel, JLabel label, JComponent component, GridBagConstraints gbc) {
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

    private void iniciarSesion() {
        String cuenta = campoCuenta.getText();
        char[] contra = campoContra.getPassword();

        //Aca verificio la cuenta y contra

        System.out.println("Cuenta: " + cuenta);
        System.out.println("Contraseña: " + new String(contra));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PantallaLogin();
        });
    }
}
