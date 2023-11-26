/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriobd2;

import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.awt.List;
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
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author albin
 */
public class Funcionario {
   
    String cuenta;
    String contra;
    int Cedula;
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

    public int getCedula() {
        return Cedula;
    }

    public void setCedula(int Cedula) {
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
    
    public void nuevoLogin(JTextField cuenta){
        setCuenta(cuenta.getText());
        
        CConection coneccion = new CConection();
        
        String consulta = "INSERT INTO Ultimo_Inicio (LogId) VALUES (?);";
        
        try {
            
            CallableStatement cs = coneccion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getCuenta());
            
            cs.execute();

        } catch (HeadlessException | SQLException e ){
            JOptionPane.showMessageDialog(null, "ERROR AL INGRESAR EL LOGIN A LA BASE, ERROR: "+e.toString());
        }
    }
    
    public void insertarFuncionario(JTextField cuenta, JPasswordField contra, JTextField cedula, JTextField nombre, JTextField apellido, JDateChooser fechaNacimiento, JTextField direccion, JTextField correo, JTextField telefono){
        
        setCuenta(cuenta.getText());
        char[] arrayContra = contra.getPassword();
        String contraStr = new String(arrayContra); 
        String contraHasheada = obtenerMD5(contraStr);
        setContra(contraHasheada);
        setCedula(Integer.valueOf(cedula.getText()));
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
            
            cs3.setInt(1, getCedula());
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
    
    private boolean actualizarDatos(JTextField cedula, JTextField nombre, JTextField apellido, JDateChooser fechaNacimiento) {
        boolean datosCorrectos = false;
        String LogId = obtenerUltimoLogId();
        try {
            CConection coneccion = new CConection();
            Connection connection = coneccion.establecerConexion();

            String consulta = "UPDATE Funcionario SET Ci = ?, Nombre = ?, Apellido = ?, Fch_Nacimiento = ?, Email = ? WHERE LogId = ?";
            PreparedStatement ps = connection.prepareStatement(consulta);

            ps.setInt(1, Integer.parseInt(cedula.getText()));
            ps.setString(2, nombre.getText());
            ps.setString(3, apellido.getText());
            ps.setDate(4, new java.sql.Date(fechaNacimiento.getDate().getTime()));
            ps.setString(5, LogId);

            int rowsAffected = ps.executeUpdate();

            datosCorrectos = rowsAffected > 0;

            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datosCorrectos;
    }
    
    public String obtenerUltimoLogId() {
        String ultimoLogId = null;

        try {
            String consulta = "SELECT LogId FROM Ultimo_Inicio ORDER BY Nro DESC LIMIT 1";

            CConection conexion = new CConection();
            Connection connection = conexion.establecerConexion();

            PreparedStatement ps = connection.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ultimoLogId = rs.getString("LogId");
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ultimoLogId;
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
    
    public int verificarNuevoFuncionario(JTextField cuenta, JPasswordField contra, JTextField cedula, JTextField nombre, JTextField apellido, JDateChooser fechaNacimiento, JTextField direccion, JTextField correo, JTextField telefono) {
        
        setCuenta(cuenta.getText());
        char[] arrayContra = contra.getPassword();
        String contraStr = new String(arrayContra); 
        String ci = cedula.getText();
        setNombre(nombre.getText());
        setApellido(apellido.getText());
        setDireccion(direccion.getText());
        setCorreo(correo.getText());
        setTelefono(telefono.getText());
        
        LocalDate fechaHoy = LocalDate.now();
        Date fechaHoyDate = java.sql.Date.valueOf(fechaHoy); 
        
        //Validaciones cuenta
        if (getCuenta().isEmpty()) {
            return 1;
        }
        if ((getCuenta().length()<8) || (getCuenta().length()>20)){
            return 11;
        }
        if (!validarCaracteres(getCuenta())){
            return 111;
        }
        
        //Validaciones contra
        if (contraStr.isEmpty()){
            return 2;
        }
        if ((contraStr.length()<8)){
            return 22;
        }
        if ((contraStr.length()>20)){
            return 222;
        }
        if (!validarCaracteres(contraStr)){
            return 2222;
        }
        
        //Validaciones cedula
        if (ci == null) {
            return 3;
        } 
        if (ci.length()!=8 || !validarNumeros(ci)){
            return 33;
        }
        
        //Validaciones nombre
        if (getNombre().isEmpty()){
            return 4;
        }
        if (!validarLetras(getNombre())){
            return 44;
        }
       
        //Validaciones apellido
        if (getApellido().isEmpty()){
            return 5;
        }
        if (!validarLetras(getApellido())){
            return 55;
        }
        
        //Valdiacion fechaNacimiento
        if (fechaNacimiento.getDate() != null) {
            Date fechaSeleccionada = new Date(fechaNacimiento.getDate().getTime());
            setFechaNacimiento(fechaSeleccionada);
        } else {
            return 6;
        }
        
        
        if (!validarEdad(FechaNacimiento)) {
            return 66;
        }
        
        //Validación direccion
        if (getDireccion().isEmpty()){
            return 7;
        }
        if (!validarCaracteres(getDireccion())){
            return 77;
        }
        
        //Validacion correo
        if (getCorreo().isEmpty()){
            return 8;
        }
        if (!validarCorreo(getCorreo())){
            return 88;
        }
        
        //Validar telefono
        if (getTelefono().isEmpty()){
            return 9;
        }
        if (getTelefono().length()!=9 || !validarTelefono(getTelefono())){
            return 99;
        }
 
        return 10;
    }
    
    public int verificarFuncionario(JTextField cedula, JTextField nombre, JTextField apellido, JDateChooser fechaNacimiento) {
        
        String ci = cedula.getText();
        setNombre(nombre.getText());
        setApellido(apellido.getText());
        
        //Validaciones cedula
        if (ci == null) {
            return 3;
        } 
        if (ci.length()!=8 || !validarNumeros(ci)){
            return 33;
        }
        
        //Validaciones nombre
        if (getNombre().isEmpty()){
            return 4;
        }
        if (!validarLetras(getNombre())){
            return 44;
        }
       
        //Validaciones apellido
        if (getApellido().isEmpty()){
            return 5;
        }
        if (!validarLetras(getApellido())){
            return 55;
        }
        
        //Valdiacion fechaNacimiento
        if (fechaNacimiento.getDate() != null) {
            Date fechaSeleccionada = new Date(fechaNacimiento.getDate().getTime());
            setFechaNacimiento(fechaSeleccionada);
        } else {
            return 6;
        }
        
        if (!validarEdad(FechaNacimiento)) {
            return 66;
        }

        return 10;
    }

    
    public String obtenerMD5(String contra) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(contra.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("ERROR DE HASHEO", e);
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
    
    public boolean validarCedula(int cedula) {
        
        String numeroString = Integer.toString(cedula);

        if (numeroString.length() == 8) {
            for (char i : numeroString.toCharArray()) {
                if (!Character.isDigit(i)) {
                    return false; 
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean validarCaracteres(String cuenta) {
     
        String caracteresPermitidos = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789 ";
        for (char c : cuenta.toCharArray()) {
            if (caracteresPermitidos.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean validarLargo(String cuenta) {
        if (cuenta.length()<8) {
            return false;
        } else if ((cuenta.length()>20)) {
            return false;
        }
        return true;
    }
    
    public static boolean validarNumeros(String input) {
        String caracteresPermitidos = "0123456789";
        for (char c : input.toCharArray()) {
            if (caracteresPermitidos.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean validarLetras(String input) {
        String caracteresPermitidos = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        for (char c : input.toCharArray()) {
            if (caracteresPermitidos.indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean validarEdad(Date fechaNacimiento) {
       
        LocalDate nacimiento = fechaNacimiento.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
        
        LocalDate fechaHoy = LocalDate.now(); 

        int edad = Period.between(nacimiento, fechaHoy).getYears();
        return edad >= 18;
    }
    
    public static boolean validarCorreo(String correo) {
        return correo.endsWith("@gmail.com") || correo.endsWith("@correo.ucu.edu.uy");
    }
    
    public static boolean validarTelefono(String telefono) {
        return telefono.startsWith("09");
    }
}