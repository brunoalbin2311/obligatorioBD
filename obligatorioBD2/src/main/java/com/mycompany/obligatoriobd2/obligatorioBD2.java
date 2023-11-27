/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriobd2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.mail.MessagingException;

/**
 *
 * @author albin
 */
public class ObligatorioBD2 {

    public static void main(String[] args) {
        
        PantallaIniciar pantalla = new PantallaIniciar();
        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable recordarFechaAgendaTask = () -> {
            Recordatorio recordatorio = new Recordatorio();
            recordatorio.recordarFechaAgenda();
        };

        Runnable recordarVencimientoCarnetTask = () -> {
            Recordatorio recordatorio = new Recordatorio();
            recordatorio.recordarVencimientoCarnet();
        };

        scheduler.scheduleAtFixedRate(recordarFechaAgendaTask, 0, 24, TimeUnit.HOURS);
        scheduler.scheduleAtFixedRate(recordarVencimientoCarnetTask, 0, 24, TimeUnit.HOURS);
    }
}

