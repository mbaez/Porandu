/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.plugin;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author mbaez
 */
public class SchemaTreeCellRenderer implements TreeCellRenderer {

    private JLabel label;

    public SchemaTreeCellRenderer() {
        label = new JLabel();
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {
        Object o = ((DefaultMutableTreeNode) value).getUserObject();
        if (o instanceof TreeElement) {
            TreeElement el = (TreeElement) o;
            URL imageUrl = getClass().getResource(el.getIcon());
            if (imageUrl != null) {
                label.setIcon(new ImageIcon(imageUrl));
            }
            label.setText(el.getName());
        } else {
            label.setIcon(null);
            label.setText("" + value);
        }
        return label;
    }
}
