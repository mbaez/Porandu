/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.util;


import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

/**
 *
 * @author mbaez
 */
public class ClosableTabbedHeader extends TabbedHeader {

    public ClosableTabbedHeader(JTabbedPane p) {
        super(p);
        button.setIcon(new ImageIcon(getClass().getResource("/com/py/mbaez/porandu/icon/close.png")));
        button.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int tabIndex = parent.getSelectedIndex();
                parent.remove(tabIndex);
                PgSession.CURRENTCONEXION.remove(tabIndex);
                if(PgSession.CURRENTCONEXION.size()==0)
                    parent.remove(0);
                else
                    parent.setSelectedIndex(tabIndex-1);

            }
        });

    }
}
