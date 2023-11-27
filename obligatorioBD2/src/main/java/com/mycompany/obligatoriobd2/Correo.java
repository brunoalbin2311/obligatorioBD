/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.obligatoriobd2;

/**
 *
 * @author albin
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

public class Correo {

    private  static  String emailFrom = "Obligatorio.Base.de.Datos.UCU@gmail.com";
    private static String passwordFrom = "manq ferh wloo ohln";
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public Correo(){
        mProperties = new Properties();
    }
 
    public void crearEmail(String destino, String titulo, String mensaje) throws MessagingException {
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable","true");
        mProperties.setProperty("mail.smtp.starttls.enable","true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.ssl.smtp.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smpt.auth","true");

        mSession = javax.mail.Session.getInstance(mProperties);
        mCorreo = new MimeMessage(mSession);  

        try {
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(destino));
            mCorreo.setSubject(titulo);
            mCorreo.setText(mensaje, "ISO-8859-1", "html");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarEmail(){
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom,passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}