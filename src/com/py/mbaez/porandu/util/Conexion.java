package com.py.mbaez.porandu.util;

import java.sql.*;

public class Conexion {

    private static int PORT = 0;
    private String JDBC_URL = "";
    private ConfiguracionManager config;

    public Conexion(ConfiguracionManager config) {
        this.config = config;
    }

    /**
     * @param driver es el nombre del driver del gestor que se va utilizar en este caso, postgres
     */
    public boolean cargarDriver(String driver) {
        try {
            Class.forName(driver);
            JDBC_URL = driver;
            System.out.println(JDBC_URL);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }


    /**
     * Establece una nueva conexion y la anhade al la lista de conexiones
     * establecidas
     * @param urlConnection La url de conexión
     * @param user el usuario
     * @param pass la contrasenha 
     */
    public Connection conectar(String urlConnection, String user,
            String pass) throws SQLException {
        Connection con = null;
        if(user.isEmpty() && pass.isEmpty()){
            con = DriverManager.getConnection(urlConnection);
        }else{
            con = DriverManager.getConnection(urlConnection, user,pass);
        }
        
        PgSession.CURRENTCONEXION.add(con);

        return con;
    }
}
