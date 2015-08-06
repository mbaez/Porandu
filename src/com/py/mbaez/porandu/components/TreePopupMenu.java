/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.components;

import com.py.mbaez.porandu.plugin.TreeElement;
import com.py.mbaez.porandu.util.TypeEnum;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

/**
 *
 * @author mbaez
 */
public class TreePopupMenu extends JPopupMenu {

    public TreePopupMenu(TreeElement el) {
        super();
        this.buildEl(el);
    }

    private void buildEl(TreeElement el) {
        if (el.getType() == TypeEnum.TABLA) {
            this.add(new JMenuItem("CREATE script"));
            this.add(new JMenuItem("SELECT script"));
            this.add(new JMenuItem("INSERT script"));
            this.add(new JMenuItem("UPDATE script"));
            this.add(new JMenuItem("DELETE script"));
            this.add(new JMenuItem("DROP script"));
            this.add(new JSeparator());
            this.add(new JMenuItem("Propiedades"));
        } else if (el.getType() == TypeEnum.FUNCION) {
            this.add(new JMenuItem("CREATE script"));
            this.add(new JMenuItem("SELECT script"));
            this.add(new JMenuItem("DROP script"));
            this.add(new JSeparator());
            this.add(new JMenuItem("Propiedades"));

        }

    }

}
