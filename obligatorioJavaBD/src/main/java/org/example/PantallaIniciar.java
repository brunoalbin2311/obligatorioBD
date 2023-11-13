package org.example;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class PantallaIniciar {


    public PantallaIniciar() {

        //Creación interfaz
        JFrame frame = new JFrame("Actualización de datos UCU");    //Nombre de la ventana.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);           //Al cerrar la centana, termina el programa.

        //Titulo en la ventana
        JLabel titulo = new JLabel("Formulario de actualización de datos");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        //Botones
        JButton botonIniciar = new JButton(" Iniciar Sesión ");
        JButton botonRegistrarse = new JButton("  Registrarse   ");
        JButton botonAdmin = new JButton("Administración");

        //Creación de panel para agregar todo y crear un margen al rededor, por prolijidad
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //Creacion y ubicacion del gbc para darle estrucutura
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(titulo, gbc);

        //Ubicación del boton iniciar
        gbc.gridy++;
        gbc.insets = new Insets(30, 0, 0, 0);
        panel.add(botonIniciar, gbc);

        //Ubicación del boton registrarse
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0); // Separación
        panel.add(botonRegistrarse, gbc);

        //Ubicación del boton admin
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 0, 0); // Separación
        panel.add(botonAdmin, gbc);

        //Mostrar la interfaz
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Boton iniciar, corroborando que la fecha sea la correcta, si la es, redirigir a la pantalla de login
        botonIniciar.addActionListener(e -> {
            if (esFechaValida()) {
                frame.dispose();
                new PantallaLogin();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Fecha no disponible, comunicarse con soporte");
            }
        });

        //Clikcear boton, registrarse, tambien corroborando la fecha
        botonRegistrarse.addActionListener(e -> {
            if (esFechaValida()) {
                frame.dispose();
                new PantallaRegistrarDatos();
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: Fecha no disponible, comunicarse con soporte");
            }
        });

        //Clikcear boton admin
        botonAdmin.addActionListener(e -> {
            frame.dispose();
            new PantallaAdmin();
        });
    }

    //Método corroborar que la fecha es válida
    private boolean esFechaValida() {
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaInicio = LocalDate.of(fechaActual.getYear(), 11, 1);
        LocalDate fechaFin = LocalDate.of(fechaActual.getYear(), 11, 15);

        return fechaActual.isAfter(fechaInicio.minusDays(1)) && fechaActual.isBefore(fechaFin.plusDays(1));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PantallaIniciar();
        });
    }
}
