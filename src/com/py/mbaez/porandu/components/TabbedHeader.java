/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.components;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author mbaez
 */
public class TabbedHeader extends JPanel {

    protected JTabbedPane parent;
    protected JButton button;
    protected JLabel title;

    public TabbedHeader(JTabbedPane p) {
        // LayoutManager m=new GridLayout();
        this.setLayout(new java.awt.GridBagLayout());
        if (p != null) {
            this.parent = p;
            setOpaque(false);
            title = new JLabel() {

                public String getText() {
                    int i = parent.indexOfTabComponent(TabbedHeader.this);
                    if (i != -1) {
                        return parent.getTitleAt(i);
                    }
                    return null;
                }
            };
            add(title);
            title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
            button = new JButton();
            button.setBorderPainted(false);
            button.setPreferredSize(new Dimension(16, 16));
            add(button);
        }
    }
}
