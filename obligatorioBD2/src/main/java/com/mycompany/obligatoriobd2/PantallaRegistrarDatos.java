/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.obligatoriobd2;

import java.awt.Color;

/**
 *
 * @author albin
 */
public class PantallaRegistrarDatos extends javax.swing.JFrame {

    /**
     * Creates new form PantallaRegistrarDatos
     */
    public PantallaRegistrarDatos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelCuenta = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jLabelCedula = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelFechaTelefono = new javax.swing.JLabel();
        jLabelApellido = new javax.swing.JLabel();
        jLabelContra = new javax.swing.JLabel();
        jLabelFechaNacimiento = new javax.swing.JLabel();
        jLabelCarnet = new javax.swing.JLabel();
        jLabelFechaCarnet = new javax.swing.JLabel();
        jTextFieldCedula = new javax.swing.JTextField();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldCuenta = new javax.swing.JTextField();
        jTextFieldApellido = new javax.swing.JTextField();
        jTextFieldFechaNacimiento = new javax.swing.JTextField();
        jTextFieldDireccion = new javax.swing.JTextField();
        jTextFieldCorreo = new javax.swing.JTextField();
        jTextFieldFechaVencimiento = new javax.swing.JTextField();
        jButtonCarnet = new javax.swing.JButton();
        jLabelNoCarnet = new javax.swing.JLabel();
        botonCompletarDatos = new javax.swing.JButton();
        jTextFieldContra = new javax.swing.JTextField();
        jTextFieldTelefono1 = new javax.swing.JTextField();
        jLabelFechaTelefono1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelCuenta.setText("Cuenta");

        jLabelDireccion.setText("Dirección");

        jLabelCedula.setText("Cédula");

        jLabelNombre.setText("Nombre");

        jLabelFechaTelefono.setText("Teléfono");

        jLabelApellido.setText("Apellido");

        jLabelContra.setText("Contraseña");

        jLabelFechaNacimiento.setText("Fecha de nacimiento");

        jLabelCarnet.setText("Carnet de salud");

        jLabelFechaCarnet.setText("Fecha vencimiento carnet");

        jTextFieldCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCedulaActionPerformed(evt);
            }
        });

        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jTextFieldCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCuentaActionPerformed(evt);
            }
        });

        jTextFieldApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldApellidoActionPerformed(evt);
            }
        });

        jTextFieldFechaNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFechaNacimientoActionPerformed(evt);
            }
        });

        jTextFieldDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDireccionActionPerformed(evt);
            }
        });

        jTextFieldCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCorreoActionPerformed(evt);
            }
        });

        jTextFieldFechaVencimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFechaVencimientoActionPerformed(evt);
            }
        });

        jButtonCarnet.setText("Adjuntar comprobante");

        jLabelNoCarnet.setFont(new java.awt.Font("Eras Bold ITC", 0, 14)); // NOI18N
        jLabelNoCarnet.setText("No tengo carnet / No tengo carnet vigente");
        jLabelNoCarnet.setToolTipText("");
        jLabelNoCarnet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelNoCarnetMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelNoCarnetMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelNoCarnetMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabelNoCarnetMouseReleased(evt);
            }
        });

        botonCompletarDatos.setText("Enviar");
        botonCompletarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCompletarDatosActionPerformed(evt);
            }
        });

        jTextFieldContra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldContraActionPerformed(evt);
            }
        });

        jTextFieldTelefono1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTelefono1ActionPerformed(evt);
            }
        });

        jLabelFechaTelefono1.setText("Correo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(botonCompletarDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelFechaCarnet)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelCarnet)
                                .addGap(59, 59, 59)
                                .addComponent(jButtonCarnet)))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNoCarnet)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelFechaTelefono)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCuenta)
                                    .addComponent(jLabelContra)
                                    .addComponent(jLabelCedula)
                                    .addComponent(jLabelNombre)
                                    .addComponent(jLabelApellido)
                                    .addComponent(jLabelFechaNacimiento)
                                    .addComponent(jLabelDireccion)
                                    .addComponent(jLabelFechaTelefono1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jTextFieldCuenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldCedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldApellido, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addComponent(jTextFieldFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldContra, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCuenta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelContra)
                    .addComponent(jTextFieldContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCedula))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaNacimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaTelefono1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTelefono1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFechaTelefono))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCarnet)
                    .addComponent(jLabelCarnet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFechaCarnet)
                    .addComponent(jTextFieldFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jLabelNoCarnet)
                .addGap(18, 18, 18)
                .addComponent(botonCompletarDatos)
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCedulaActionPerformed

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jTextFieldCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCuentaActionPerformed

    private void jTextFieldApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldApellidoActionPerformed

    private void jTextFieldFechaNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFechaNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFechaNacimientoActionPerformed

    private void jTextFieldDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDireccionActionPerformed

    private void jTextFieldCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCorreoActionPerformed

    private void jTextFieldFechaVencimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFechaVencimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFechaVencimientoActionPerformed

    private void botonCompletarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCompletarDatosActionPerformed
        Funcionario funcionario = new Funcionario();
        funcionario.insertarFuncionario(jTextFieldCuenta, jTextFieldContra, jTextFieldCedula, jTextFieldNombre, jTextFieldApellido, jTextFieldFechaNacimiento, jTextFieldDireccion, jTextFieldCorreo, jTextFieldTelefono1);
    }//GEN-LAST:event_botonCompletarDatosActionPerformed

    private void jTextFieldContraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldContraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldContraActionPerformed

    private void jTextFieldTelefono1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTelefono1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTelefono1ActionPerformed

    private void jLabelNoCarnetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNoCarnetMouseClicked
        
        dispose();
        PantallaAgenda pantallaAgenda = new PantallaAgenda();
        pantallaAgenda.setLocationRelativeTo(null); 
        pantallaAgenda.setVisible(true);
    }//GEN-LAST:event_jLabelNoCarnetMouseClicked

    private void jLabelNoCarnetMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNoCarnetMouseEntered
        jLabelNoCarnet.setForeground(Color.BLUE);
    }//GEN-LAST:event_jLabelNoCarnetMouseEntered

    private void jLabelNoCarnetMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNoCarnetMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelNoCarnetMouseReleased

    private void jLabelNoCarnetMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNoCarnetMouseExited
        jLabelNoCarnet.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabelNoCarnetMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaRegistrarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaRegistrarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaRegistrarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaRegistrarDatos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaRegistrarDatos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCompletarDatos;
    private javax.swing.JButton jButtonCarnet;
    private javax.swing.JLabel jLabelApellido;
    private javax.swing.JLabel jLabelCarnet;
    private javax.swing.JLabel jLabelCedula;
    private javax.swing.JLabel jLabelContra;
    private javax.swing.JLabel jLabelCuenta;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelFechaCarnet;
    private javax.swing.JLabel jLabelFechaNacimiento;
    private javax.swing.JLabel jLabelFechaTelefono;
    private javax.swing.JLabel jLabelFechaTelefono1;
    private javax.swing.JLabel jLabelNoCarnet;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JTextField jTextFieldApellido;
    private javax.swing.JTextField jTextFieldCedula;
    private javax.swing.JTextField jTextFieldContra;
    private javax.swing.JTextField jTextFieldCorreo;
    private javax.swing.JTextField jTextFieldCuenta;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldFechaNacimiento;
    private javax.swing.JTextField jTextFieldFechaVencimiento;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldTelefono1;
    // End of variables declaration//GEN-END:variables
}
