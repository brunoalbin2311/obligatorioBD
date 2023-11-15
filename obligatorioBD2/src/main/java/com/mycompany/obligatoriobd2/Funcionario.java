/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriobd2;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.sql.CallableStatement;
import java.sql.SQLException;

/**
 *
 * @author albin
 */
public class Funcionario {
   
    String cuenta;
    String contra;
    String Cedula;
    String Nombre;
    String Apellido;
    String FechaNacimiento;
    String Direccion;
    String Correo;
    String Telefono;
    String CarnetDeSalud;
    String FechaVencimiento;
    
    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCarnetDeSalud() {
        return CarnetDeSalud;
    }

    public void setCarnetDeSalud(String CarnetDeSalud) {
        this.CarnetDeSalud = CarnetDeSalud;
    }

    public String getFechaVencimiento() {
        return FechaVencimiento;
    }

    public void setFechaVencimiento(String FechaVencimiento) {
        this.FechaVencimiento = FechaVencimiento;
    }
    
    public void insertarFuncionario(JTextField cuenta, JTextField contra, JTextField cedula, JTextField nombre, JTextField apellido, JTextField fechaNacimiento, JTextField direccion, JTextField correo, JTextField telefono, JTextField fechaVencimiento){
        
        setCuenta(cuenta.getText());
        setContra(contra.getText());
        setCedula(cedula.getText());
        setNombre(nombre.getText());
        setApellido(apellido.getText());
        setFechaNacimiento(fechaNacimiento.getText());
        setDireccion(direccion.getText());
        setCorreo(correo.getText());
        setTelefono(telefono.getText());
        setFechaVencimiento(fechaVencimiento.getText());
        
        CConection coneccion = new CConection();
        
        String consulta1 = "INSERT INTO Login(LogId, Contra) VALUES (?,?);";
        String consulta2 = "INSERT INTO Funcionario(Ci, Nombre, Apellido, Fch_Nacimiento, Dirección, Teléfono, Email, LogId) VALUES (?,?,?,?,?,?,?,?);";
        
        try {
            
            CallableStatement cs = coneccion.establecerConexion().prepareCall(consulta1);
            
            cs.setString(1, getCuenta());
            cs.setString(2, getContra());
            
            cs.execute();
            
            CallableStatement cs2 = coneccion.establecerConexion().prepareCall(consulta2);
            
            cs2.setString(1, getCedula());
            cs2.setString(2, getNombre());
            cs2.setString(3, getApellido());
            cs2.setString(4, getFechaNacimiento());
            cs2.setString(5, getDireccion());
            cs2.setString(6, getTelefono());
            cs2.setString(7, getCorreo());
            cs2.setString(8, getCuenta());
            
            cs2.execute();
            
            JOptionPane.showMessageDialog(null, "Su registro se hizo con exito");
            
        } catch (HeadlessException | SQLException e ){
            JOptionPane.showMessageDialog(null, "Su registro no se hizo correctamente, error: "+e.toString());
        }
    }
}
