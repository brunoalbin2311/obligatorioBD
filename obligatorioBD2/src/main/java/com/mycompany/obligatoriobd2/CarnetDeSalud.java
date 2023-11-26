/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriobd2;

import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author albin
 */
public class CarnetDeSalud {

    String cedula;
    Date fechaEmision;
    Date fechaVencimiento;
    String comprobante;
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    public void insertarCarnet(JTextField cedula, JDateChooser fechaEmision, JDateChooser fechaVencimiento, String comprobante){
        
        setCedula(cedula.getText());
        Date fechaEmi = new Date(fechaEmision.getDate().getTime());
        setFechaEmision(fechaEmi);
        Date fechaVen = new Date(fechaVencimiento.getDate().getTime());
        setFechaVencimiento(fechaVen);
        setComprobante(comprobante);
        
        CConection coneccion = new CConection();
        
        String consulta = "INSERT INTO Carnet_Salud (Ci, Fch_Emision, Fch_Vencimiento, Comprobante) VALUES (?,?,?,?);";
        
        try {
            
            CallableStatement cs = coneccion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getCedula());
            cs.setDate(2, new java.sql.Date(getFechaEmision().getTime()));
            cs.setDate(3, new java.sql.Date(getFechaVencimiento().getTime()));
            cs.setString(4, getComprobante());
            
            cs.execute();

        } catch (HeadlessException | SQLException e ){
            JOptionPane.showMessageDialog(null, "Su registro no se hizo correctamente, error: "+e.toString());
        }
    }
    
    public int verificarCarnet(JDateChooser fechaEmision, JDateChooser fechaVencimiento, String comprobante) {
        
        Date fechaInicial = fechaEmision.getDate();
        Date fechaFinal = fechaVencimiento.getDate();
        LocalDate fechaHoy = LocalDate.now();
        Date fechaHoyDate = java.sql.Date.valueOf(fechaHoy); 
        
        if (comprobante == null) {
            return 1;
        }
        
        if (fechaInicial == null ){
            return 2;
        }
        
        if (fechaFinal == null ){
            return 3;
        }
        
        if (fechaInicial.before(fechaHoyDate) || fechaFinal.before(fechaHoyDate)) {
            return 4;
        }
        
        if (fechaInicial.after(fechaFinal)) {
            return 5;
        }
        return 6;
    }
}
