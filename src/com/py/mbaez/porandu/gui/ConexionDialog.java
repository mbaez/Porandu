/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConexionDialog.java
 *
 * Created on 12/11/2010, 12:44:49 PM
 */

package com.py.mbaez.porandu.gui;

import java.sql.Connection;
import javax.swing.JOptionPane;
import com.py.mbaez.porandu.util.Conexion;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mbaez
 */
public class ConexionDialog extends javax.swing.JDialog {
    private java.awt.Frame parent;
    /** Creates new form ConexionDialog */
    public ConexionDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent=parent;
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        driverTextField = new javax.swing.JTextField();
        serverTextField = new javax.swing.JTextField();
        databaseTextField = new javax.swing.JTextField();
        userTextField = new javax.swing.JTextField();
        driverLabel = new javax.swing.JLabel();
        serverLabel = new javax.swing.JLabel();
        databaseLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        conectarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Conectar");
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setModalityType(java.awt.Dialog.ModalityType.TOOLKIT_MODAL);
        setResizable(false);

        driverTextField.setText("org.postgresql.Driver");
        driverTextField.setEnabled(false);

        serverTextField.setText("localhost");

        databaseTextField.setText("scm");

        userTextField.setText("postgres");

        driverLabel.setText("Driver:");

        serverLabel.setText("Server:");

        databaseLabel.setText("Database:");

        userLabel.setText("User:");

        passwordLabel.setText("Password:");

        passwordField.setText("postgres");

        conectarButton.setText("Conectar");
        conectarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(driverLabel)
                    .addComponent(passwordLabel)
                    .addComponent(databaseLabel)
                    .addComponent(serverLabel)
                    .addComponent(userLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField)
                    .addComponent(userTextField)
                    .addComponent(databaseTextField)
                    .addComponent(serverTextField)
                    .addComponent(driverTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(104, Short.MAX_VALUE)
                .addComponent(conectarButton)
                .addGap(97, 97, 97))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(driverTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(driverLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serverTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(databaseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(databaseLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(conectarButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void conectarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarButtonActionPerformed

        Conexion conexion=new Conexion();
        if (!conexion.cargarDriver(driverTextField.getText())) {
            JOptionPane.showMessageDialog(this, "Error al cargar el driver",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Connection con= conectar(conexion);
        if (con == null) {
            JOptionPane.showMessageDialog(this, "Error al conectar con la BD",
                    "Error de conexion", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ((VentanaPrincipal)parent).addSqlQueryResultTab();
        this.dispose();
    }//GEN-LAST:event_conectarButtonActionPerformed
    private Connection conectar(Conexion conexion) {
        return  conexion.conectar(serverTextField.getText(), databaseTextField.getText(),
                userTextField.getText(), String.copyValueOf(passwordField.getPassword()));
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton conectarButton;
    private javax.swing.JLabel databaseLabel;
    private javax.swing.JTextField databaseTextField;
    private javax.swing.JLabel driverLabel;
    private javax.swing.JTextField driverTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JLabel serverLabel;
    private javax.swing.JTextField serverTextField;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField userTextField;
    // End of variables declaration//GEN-END:variables

}