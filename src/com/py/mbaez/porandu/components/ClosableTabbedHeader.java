/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.components;


import com.py.mbaez.porandu.managers.SessionManager;
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
                SessionManager.CURRENTCONEXION.remove(tabIndex);
                if(SessionManager.CURRENTCONEXION.size()==0)
                    parent.remove(0);
                else
                    parent.setSelectedIndex(tabIndex-1);

            }
        });

    }
}
