/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FileIO {

    private String fileName;

    public FileIO(String fileName) {
        this.fileName = fileName;
    }

    public void saveFile(ArrayList<Object[]> data) {
        try {
            PrintWriter fileOut = new PrintWriter(new FileWriter(fileName, false));
            for (int i = 0; i < data.size(); i++) {
                fileOut.println(concatenar(data.get(i)));
            }
            fileOut.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR!!");
        }
    }

    /*
     * Pasa a formato CSV
     */
    private String concatenar(Object object[]) {
        String str = object[0].toString();
        for (int i = 1; i < object.length; i++) {
            str = str + ";" + object[i];
        }
        return str;
    }
}
