/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriobd2;

import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

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
    Date FechaNacimiento;
    String Direccion;
    String Correo;
    String Telefono;
    
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

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
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
    
    public void insertarFuncionario(JTextField cuenta, JPasswordField contra, JTextField cedula, JTextField nombre, JTextField apellido, JDateChooser fechaNacimiento, JTextField direccion, JTextField correo, JTextField telefono){
        
        setCuenta(cuenta.getText());
        
        char[] arrayContra = contra.getPassword();
        String contraStr = new String(arrayContra); 
        String contraHasheada = obtenerMD5(contraStr);
        setContra(contraHasheada);

        setCedula(cedula.getText());
        setNombre(nombre.getText());
        setApellido(apellido.getText());
        
        Date fechaSeleccionada = new Date(fechaNacimiento.getDate().getTime());
        setFechaNacimiento(fechaSeleccionada);
        
        setDireccion(direccion.getText());
        setCorreo(correo.getText());
        setTelefono(telefono.getText());
        
        CConection coneccion = new CConection();
        
        String consulta1 = "INSERT INTO Administracion (LogId, Rol) VALUES (?,?);";
        String consulta2 = "INSERT INTO Login(LogId, Contra) VALUES (?,?);";
        String consulta3 = "INSERT INTO Funcionario(Ci, Nombre, Apellido, Fch_Nacimiento, Dirección, Telefono, Email, LogId) VALUES (?,?,?,?,?,?,?,?);";
        
        try {
            
            CallableStatement cs1 = coneccion.establecerConexion().prepareCall(consulta1);
            
            cs1.setString(1, getCuenta());
            cs1.setBoolean(2, false);
            
            cs1.execute();
            
            CallableStatement cs2 = coneccion.establecerConexion().prepareCall(consulta2);
            
            cs2.setString(1, getCuenta());
            cs2.setString(2, getContra());
            
            cs2.execute();
            
            CallableStatement cs3 = coneccion.establecerConexion().prepareCall(consulta3);
            
            cs3.setString(1, getCedula());
            cs3.setString(2, getNombre());
            cs3.setString(3, getApellido());
            cs3.setDate(4, new java.sql.Date(getFechaNacimiento().getTime()));
            cs3.setString(5, getDireccion());
            cs3.setString(6, getTelefono());
            cs3.setString(7, getCorreo());
            cs3.setString(8, getCuenta());
            
            cs3.execute();
            
            JOptionPane.showMessageDialog(null, "Su registro se hizo con exito");
            
        } catch (HeadlessException | SQLException e ){
            JOptionPane.showMessageDialog(null, "Su registro no se hizo correctamente, error: "+e.toString());
        }
    }
 
    public boolean verificarUsuario(JTextField cuenta, JPasswordField contra) {
        setCuenta(cuenta.getText());

        char[] arrayContra = contra.getPassword();
        String contraStr = new String(arrayContra);
        
        if(!esAdmin()){
           String contraHasheada = obtenerMD5(contraStr);
           setContra(contraHasheada); 
        } else {
            setContra(contraStr);
        }

        boolean usuarioEncontrado = false;

        try {
            CConection conexion = new CConection();
            Connection connection = conexion.establecerConexion();

            String consulta = "SELECT * FROM Login WHERE LogId = ? AND Contra = ?";
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setString(1, getCuenta());
            ps.setString(2, getContra());

            ResultSet rs = ps.executeQuery();

            usuarioEncontrado = rs.next();

            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarioEncontrado;
    }
    
    public String obtenerMD5(String contra) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(contra.getBytes());

            // Convertir el hash de bytes a representación hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña con MD5", e);
        }
    }
    
    public boolean esAdmin() {
        boolean esAdmin = false;

        try {
            CConection conexion = new CConection();
            Connection connection = conexion.establecerConexion();

            String consulta = "SELECT Rol FROM Administracion WHERE LogId = ?";
            PreparedStatement ps = connection.prepareStatement(consulta);
            ps.setString(1, getCuenta());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                esAdmin = rs.getBoolean("Rol");
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return esAdmin;
    }
}
