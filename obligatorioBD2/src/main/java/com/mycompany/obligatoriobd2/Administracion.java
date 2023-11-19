/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriobd2;

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author albin
 */
public class Administracion {
    
    static String ano = "2023";
    static String semestre = "2";
    static String fechaInicio = "2023-11-01";
    static String fechaFin = "2023-11-25";

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    public boolean fechaEstaEnRango(String fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate fechaObjeto = LocalDate.parse(fecha, formato);
        LocalDate inicioObjeto = LocalDate.parse(fechaInicio, formato);
        LocalDate finObjeto = LocalDate.parse(fechaFin, formato);

        return (fechaObjeto.isEqual(inicioObjeto) || fechaObjeto.isAfter(inicioObjeto)) && (fechaObjeto.isEqual(finObjeto) || fechaObjeto.isBefore(finObjeto));
    }
    
    public void nuevoPeriodo(JTextField fechaInicio, JTextField fechaFin){
        
        LocalDate fechaActual = LocalDate.now(); 
        String anioActual = String.valueOf(fechaActual.getYear());
        setAno(anioActual);
        
        int mesActual = fechaActual.getMonthValue(); 
        if (mesActual <= 6) {
            setSemestre("1");
        } else {
            setSemestre("2");
        }
        
        setFechaInicio(fechaInicio.getText());
        setFechaFin(fechaFin.getText());
        
        CConection coneccion = new CConection();
        
        String consulta = "INSERT INTO Periodos_Actualizacion(Año, Semestre, Fch_Inicio, Fch_Fin) VALUES (?,?,?,?);";
        
        try {
            
            CallableStatement cs = coneccion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getAno());
            cs.setString(2, getSemestre());
            cs.setString(3, getFechaInicio());
            cs.setString(4, getFechaFin());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Actualización de periodo con exito, fecha de inicio: ("+getFechaInicio()+"), fecha final ("+getFechaFin()+")");
            
        } catch (HeadlessException | SQLException e ){
            JOptionPane.showMessageDialog(null, "Su registro no se hizo correctamente, error: "+e.toString());
        }
    }

}
