/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.py.mbaez.porandu.gui;

/**
 *
 * @author mbaez
 */
public class Main {
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }
}
