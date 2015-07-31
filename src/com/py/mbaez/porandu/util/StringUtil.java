/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.util;

/**
 *
 * @author mbaez
 */
public class StringUtil {

    /**
     * Este método se encarga de transformar una cadena que se encuentra en
     * underscore case una cadena normal. Ejemplo : <code>underscore_case</code>
     * se transforma a <code>Underscore Case</code>
     *
     * @param value
     * @return
     */
    public static String toBeautifulCase(String value) {
        if (value == null) {
            return "";
        }
        String newCase = "";
        String[] tokens = value.trim().replace("_", " ").replace(".", " ").split(" ", -1);
        for (String token : tokens) {
            //se transforma a mayuscula el primer caracter
            newCase += token.substring(0, 1).toUpperCase() + token.substring(1) + " ";
        }
        return newCase.trim();
    }
}
