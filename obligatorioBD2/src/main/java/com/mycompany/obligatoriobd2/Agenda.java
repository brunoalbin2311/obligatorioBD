    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package com.mycompany.obligatoriobd2;

    import java.awt.HeadlessException;
    import java.sql.CallableStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.text.ParseException;
    import java.text.SimpleDateFormat;
    import java.util.Calendar;
    import javax.swing.JOptionPane;
    import javax.swing.JTextField;

    /**
     *
     * @author albin
     */
    public class Agenda {

        String cedula;
        String fecha;
        String comprobante = "ruta";

        public String getComprobante() {
            return comprobante;
        }

        public void setComprobante(String comprobante) {
            this.comprobante = comprobante;
        }

        public String getCedula() {
            return cedula;
        }

        public void setCedula(String cedula) {
            this.cedula = cedula;
        }

        public String getFecha() {
            return fecha;
        }

        public void setFecha(String fecha) {
            this.fecha = fecha;
        }

        public void insertarAgenda(String cedula) throws ParseException{

            setCedula(cedula);

            String nuevaFecha = obtenerFechaFuturaNoRegistrada();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateFormat.parse(nuevaFecha));
            calendar.add(Calendar.YEAR, 1);
            String nuevaFechaMasUnAnio = dateFormat.format(calendar.getTime());


            CConection coneccion = new CConection();

            String consulta1 = "INSERT INTO Agenda(Ci, Fch_Agenda) VALUES (?, ?);";
            String consulta2 = "INSERT INTO Carnet_Salud(Ci, Fch_Emision, Fch_Vencimiento, Comprobante) VALUES (?, ?, ?, ?);";

            try {
                CallableStatement cs1 = coneccion.establecerConexion().prepareCall(consulta1);

                cs1.setString(1, getCedula());
                cs1.setString(2, nuevaFecha);

                cs1.execute();

                CallableStatement cs2 = coneccion.establecerConexion().prepareCall(consulta2);

                cs2.setString(1, getCedula());
                cs2.setString(2, nuevaFecha);
                cs2.setString(3, nuevaFechaMasUnAnio);
                cs2.setString(4, getComprobante());

                cs2.execute();

                JOptionPane.showMessageDialog(null, "Usted quedó agendado para la fecha: " + nuevaFecha);

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al registrar la agenda: " + e.toString());
            }
        }

        private String obtenerFechaFuturaNoRegistrada() {
            CConection coneccion = new CConection();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 1);

            try {
                String fechaPropuesta = dateFormat.format(calendar.getTime());

                String consultaVerificar = "SELECT COUNT(*) FROM Agenda WHERE Fch_Agenda = ?";
                CallableStatement csVerificar = coneccion.establecerConexion().prepareCall(consultaVerificar);
                csVerificar.setString(1, fechaPropuesta);

                ResultSet rs = csVerificar.executeQuery();
                rs.next();

                while (rs.getInt(1) > 0) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    fechaPropuesta = dateFormat.format(calendar.getTime());

                    csVerificar.setString(1, fechaPropuesta);
                    rs = csVerificar.executeQuery();
                    rs.next();
                }

                return fechaPropuesta;

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al verificar la fecha: " + e.toString());
                return null;
            }
        }
    }
