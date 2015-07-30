/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.components;

import com.py.mbaez.porandu.views.ConexionDialog;
import com.py.mbaez.porandu.views.SqlQueryResult;
import com.py.mbaez.porandu.views.VentanaPrincipal;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

/**
 *
 * @author mbaez
 */
public class AddTabbedHeader extends TabbedHeader {

    public AddTabbedHeader(JTabbedPane p,final VentanaPrincipal main) {
        super(p);
        title.setPreferredSize(new Dimension(0,0));
        button.setIcon(new ImageIcon(getClass().getResource("/com/py/mbaez/porandu/icon/add.png")));
        button.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
              ConexionDialog conexionDialog = new ConexionDialog(main, true);
              conexionDialog.setVisible(true);
            }
        });
    }
}
