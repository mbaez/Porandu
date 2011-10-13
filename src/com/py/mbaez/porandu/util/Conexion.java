package com.py.mbaez.porandu.util;

import java.sql.*;

public class Conexion {
    private static int PORT = 5432;
    private static String JDBC_URL = "jdbc:postgresql://";
    
    public Conexion(){

    }
    /**
     * @param driver es el nombre del driver del gestor que se va utilizar en este caso, postgres
     */
    public boolean cargarDriver(String driver) {
        try {
            //Class.forName(driver);
            Class.forName(driver);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
    /**
     * Establece una nueva conexion y la anhade al la lista de conexiones
     * establecidas
     * @param server la direccion del servidor
     * @param BD el nombre de la base de datos
     * @param user el usuario
     * @param pass la contrasenha 
     */
    public Connection conectar(String server, String BD, String user, String pass) {
        Connection con = null;

        try {
            //Method method = clazz.getMethod("conectar",parTypes);
            //Object arglist[] = {server, BD, user, pass};

            con = DriverManager.getConnection( JDBC_URL + server + ":"
					+ PORT + "/" + BD, user, pass);

            PgSession.CURRENTCONEXION.add(con);
            PgSession.SERVER = server;
            PgSession.DATABASE = BD;
        } catch (Exception e) {
            System.err.println(e);
        }
        return con;

    }
}
