/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.porandu.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mbaez
 */
public class Driver {
    
    private String driver = "org.postgresql.Driver";

    public Connection conectar(String server, String BD, String user, String pass) {
        Connection con = null;
        try {
            Class.forName(driver);
            DriverManager.setLoginTimeout(20);
            con = DriverManager.getConnection("jdbc:postgresql://" + server + ":5432/" + BD, user, pass);
        } catch (Exception e) {
            System.err.println(e);
        }
        System.out.println(con);
        return con;
    }
}
