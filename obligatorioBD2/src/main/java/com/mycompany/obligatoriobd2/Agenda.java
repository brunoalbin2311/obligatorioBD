    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.mycompany.obligatoriobd2;

import com.toedter.calendar.JDateChooser;
    import java.awt.HeadlessException;
    import java.sql.CallableStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Calendar;
    import java.util.Date;
    import javax.swing.JOptionPane;
    import javax.swing.JTextField;

    /**
     *
     * @author albin
     */
    public class Agenda {

    String cedula;
    Date fecha;
        
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void insertarAgenda(JTextField cedula, JDateChooser fecha){
        
        setCedula(cedula.getText());
        Date fechaDate = new Date(fecha.getDate().getTime());
        setFecha(fechaDate);
        
        CConection coneccion = new CConection();
        
        String consulta = "INSERT INTO Agenda (Ci, Fch_Agenda) VALUES (?,?);";
        
        try {
            
            CallableStatement cs = coneccion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1, getCedula());
            cs.setDate(2, new java.sql.Date(getFecha().getTime()));
            
            cs.execute();

        } catch (HeadlessException | SQLException e ){
            JOptionPane.showMessageDialog(null, "Su registro no se hizo correctamente, error: "+e.toString());
        }
    }
}
