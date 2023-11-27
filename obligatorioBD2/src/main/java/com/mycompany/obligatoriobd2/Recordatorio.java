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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javax.mail.MessagingException;

/**
 *
 * @author albin
 */
public class Recordatorio {
    
    public void recordarFechaAgenda() {
        try {
            LocalDate fecha = LocalDate.now();
            Date fechaHoy = java.sql.Date.valueOf(fecha);

            CConection conection = new CConection();
            Connection connection = conection.establecerConexion();
            
            String consulta = "SELECT F.Email, A.Fch_Agenda FROM Funcionario F JOIN Agenda A ON F.Ci = A.Ci WHERE A.Fch_Agenda > ?";

            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setDate(1, fechaHoy);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String destino = resultSet.getString("Email");
                Date fechaAgenda = resultSet.getDate("Fch_Agenda");
                enviarRecordatorioAgenda(destino, fechaAgenda);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la base de datos", e);
        }
    }
    
    private void enviarRecordatorioAgenda(String destino, Date fechaAgenda) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String mensaje = "Recuerde que usted quedo agendado para renovar el carnet UCU para la fecha: " + dateFormat.format(fechaAgenda);
            Correo correo = new Correo();
            correo.crearEmail(destino, "Recordatorio carnet UCU", mensaje);
            correo.enviarEmail();
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }
    
    public void recordarVencimientoCarnet() {
        try {
            LocalDate fecha = LocalDate.now();
            Date fechaHoy = java.sql.Date.valueOf(fecha);

            CConection conection = new CConection();
            Connection connection = conection.establecerConexion();
            String consulta = "SELECT F.Email, CS.Fch_Vencimiento FROM Funcionario F JOIN Carnet_Salud CS ON F.Ci = CS.Ci WHERE CS.Fch_Vencimiento < ?";

            PreparedStatement statement = connection.prepareStatement(consulta);
            statement.setDate(1, fechaHoy);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String destino = resultSet.getString("Email");
                Date fechaVencimiento = resultSet.getDate("Fch_Vencimiento");
                enviarRecordatorioVencimientoCarnet(destino, fechaVencimiento);
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la base de datos", e);
        }
    }

    private void enviarRecordatorioVencimientoCarnet(String destino, Date fechaVencimiento) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String mensaje = "Estimado, su carnet de salud venció en la fecha: " + dateFormat.format(fechaVencimiento)+", porfavor complete el formulario de actualización de datos en el proximo período";
            Correo correo = new Correo();
            correo.crearEmail(destino, "Carnet UCU vencido", mensaje);
            correo.enviarEmail();
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar el correo", e);
        }
    }
}
