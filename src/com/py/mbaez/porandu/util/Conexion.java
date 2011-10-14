package com.py.mbaez.porandu.util;

import java.sql.*;

public class Conexion {
    private static int PORT = 5432;
    private String JDBC_URL = "jdbc:postgresql://";
    private ConfiguracionManager config;
    
    public Conexion(ConfiguracionManager config){
        this.config = config;
    }
    /**
     * @param driver es el nombre del driver del gestor que se va utilizar en este caso, postgres
     */
    public boolean cargarDriver(String driver) {
        try {
            Class.forName(driver);
            JDBC_URL = config.getUrl(driver);
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
    public Connection conectar(String server, String BD, String port, String user,
           String pass) throws SQLException{
        Connection con = null;

            //Method method = clazz.getMethod("conectar",parTypes);
            //Object arglist[] = {server, BD, user, pass};
            String url = JDBC_URL.
                    replace("$SERVER",server).
                    replace("$PORT", port).
                    replace("$BD",BD).
                    replace("$USER", user).
                    replace("$PASSWORD", pass);
            con = DriverManager.getConnection(url);

            PgSession.CURRENTCONEXION.add(con);
            PgSession.SERVER = server;
            PgSession.DATABASE = BD;

        return con;

    }
}
