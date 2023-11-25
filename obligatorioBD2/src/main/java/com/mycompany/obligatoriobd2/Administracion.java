/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriobd2;

import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author albin
 */
public class Administracion {

    int ano;
    int semestre;
    Date fechaInicio;
    Date fechaFin;
    
    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public void actualizarPeriodo(JDateChooser fechaInicio, JDateChooser fechaFin){
        
        LocalDate fechaActual = LocalDate.now(); 
        int anioActual = Integer.valueOf(fechaActual.getYear());
        setAno(anioActual);
        
        int mesActual = fechaActual.getMonthValue(); 
        if (mesActual <= 6) {
            setSemestre(1);
        } else {
            setSemestre(2);
        }
        
        Date fechaIn = new Date(fechaInicio.getDate().getTime());
        setFechaInicio(fechaIn);
        Date fechaF = new Date(fechaFin.getDate().getTime());
        setFechaFin(fechaF); 
        
        CConection coneccion = new CConection();
        
        String consulta = "INSERT INTO Periodos_Actualizacion(Año, Semestre, Fch_Inicio, Fch_Fin) VALUES (?,?,?,?);";
        
        try {
            
            CallableStatement cs = coneccion.establecerConexion().prepareCall(consulta);
            
            cs.setInt(1, getAno());
            cs.setInt(2, getSemestre());
            cs.setDate(3, new java.sql.Date(getFechaInicio().getTime()));
            cs.setDate(4, new java.sql.Date(getFechaFin().getTime()));
            
            cs.execute();
            
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            JOptionPane.showMessageDialog(null, "Actualización de periodo con exito, fecha de inicio: ("+formatoFecha.format(getFechaInicio())+"), fecha final ("+formatoFecha.format(getFechaFin())+")");
            
        } catch (HeadlessException | SQLException e ){
            JOptionPane.showMessageDialog(null, "Su registro no se hizo correctamente, error: "+e.toString());
        }
    }
    
    public boolean fechaDisponible() {
        boolean disponible = false;

        try {
            CConection conexion = new CConection();
            Connection connection = conexion.establecerConexion();

            String consulta = "SELECT Fch_Inicio, Fch_Fin FROM Periodos_Actualizacion ORDER BY Nro DESC LIMIT 1";

            PreparedStatement ps = connection.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Date fechaInicio = rs.getDate("Fch_Inicio");
                Date fechaFin = rs.getDate("Fch_Fin");

                Date fechaActual = new Date();
                if (fechaActual.after(fechaInicio) && fechaActual.before(fechaFin)) {
                    disponible = true;   
                }
            }
            
            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disponible;
    }
    
    public int verificarFechas(JDateChooser fechaInicio, JDateChooser fechaFin){
        Date fechaInicial = fechaInicio.getDate();
        Date fechaFinal = fechaFin.getDate();
        LocalDate fechaHoy = LocalDate.now();
        Date fechaHoyDate = java.sql.Date.valueOf(fechaHoy); 


        if (fechaInicial == null || fechaFinal == null) {
            return 1;
        }
        
        if (fechaInicial.before(fechaHoyDate) || fechaFinal.before(fechaHoyDate)) {
            return 2;
        }

        if (fechaInicial.after(fechaFinal)) {
            return 3;
        }
        
        return 4;
    }
}
