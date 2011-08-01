package com.py.mbaez.porandu.util;

import java.lang.reflect.Method;
import java.sql.*;

public class Conexion {
    private Class parTypes[] = new Class[4];
    private Class clazz;
    
    public Conexion(){
        for(int i=0; i<parTypes.length;i++){
            parTypes[i] = String.class;
        }
    }
    /**
     * @param driver es el nombre del driver del gestor que se va utilizar en este caso, postgres
     */
    public boolean cargarDriver(String driver) {
        try {
            //Class.forName(driver);
            clazz = Class.forName(driver);
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
            Method method = clazz.getMethod("conectar",parTypes);
            Object arglist[] = {server, BD, user, pass};

            con = (Connection)method.invoke(clazz.newInstance(), arglist);

            PgSession.CURRENTCONEXION.add(con);
            PgSession.SERVER = server;
            PgSession.DATABASE = BD;
        } catch (Exception e) {
            System.err.println(e);
        }
        return con;

    }
}
