/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.py.mbaez.porandu.managers;

import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author mbaez
 * Contine la informacion acerca de la session estblecida con el
 * servidor
 */
public class SessionManager {
    public static String NAME;
    public static ArrayList<Connection> CURRENTCONEXION=new ArrayList<Connection>();
}
