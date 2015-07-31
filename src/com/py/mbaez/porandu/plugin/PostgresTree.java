/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.plugin;

import com.py.mbaez.porandu.components.TreeView;
import com.py.mbaez.porandu.icon.Icon;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author mbaez
 */
public class PostgresTree extends TreeView {

    private static final String DATABASE_NAME = "SELECT current_database()";
    private static final String TABLES_LIST = "SELECT table_name FROM information_schema.tables WHERE table_type = ''BASE TABLE'' AND table_schema = ''{0}''";
    private static final String TABLE_SCHEMA = "SELECT distinct table_schema FROM information_schema.tables WHERE table_type = 'BASE TABLE' AND table_schema NOT IN ('pg_catalog', 'information_schema') order by table_schema";
    private static final String FUNCTIONS = "SELECT routines.routine_name FROM information_schema.routines WHERE routines.specific_schema=''{0}''";
    private static final String SEQUENCES = "SELECT relname FROM pg_catalog.pg_statio_all_sequences where schemaname=''{0}''";

    public PostgresTree(Connection conexion) {
        super(conexion);
    }

    private DefaultMutableTreeNode getTables(String schema) throws SQLException {
        String sql = MessageFormat.format(TABLES_LIST, new Object[]{schema});
        ResultSet tablas = execute(sql);
        String table;
        TreeElement parentEl = new TreeElement("Tablas", Icon.TABLES);
        DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(parentEl);
        while (tablas.next()) {
            table = tablas.getString(1);
            TreeElement el = new TreeElement(table, Icon.TABLE);
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(el);
            parentNode.add(nodo);
        }
        return parentNode;
    }

    private DefaultMutableTreeNode getFunctions(String schema) throws SQLException {
        String sql = MessageFormat.format(FUNCTIONS, new Object[]{schema});
        ResultSet rows = execute(sql);
        String value;
        TreeElement parentEl = new TreeElement("Funciones", Icon.FUNCTIONS);
        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentEl);
        while (rows.next()) {
            value = rows.getString(1);
            TreeElement el = new TreeElement(value, Icon.FUNCTION);
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(el);
            parent.add(nodo);
        }
        return parent;
    }

    private DefaultMutableTreeNode getSequences(String schema) throws SQLException {
        String sql = MessageFormat.format(SEQUENCES, new Object[]{schema});
        ResultSet rows = execute(sql);
        String value;
        TreeElement parentEl = new TreeElement("Sequencias", Icon.SEQUENCES);
        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentEl);
        while (rows.next()) {
            value = rows.getString(1);
            TreeElement el = new TreeElement(value, Icon.SEQUENCE);
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(el);
            parent.add(nodo);
        }
        return parent;
    }

    /**
     *
     * @throws SQLException
     */
    @Override
    public void buildTree() throws SQLException {
        ResultSet rs = execute(DATABASE_NAME);
        //se posiciona el cursor
        rs.next();
        String databaseName = rs.getString(1);
        //se seta el nombre
        TreeElement dbEl = new TreeElement(databaseName, Icon.DATABASE);
        this.setUserObject(dbEl);
        ResultSet schemas = execute(TABLE_SCHEMA);
        while (schemas.next()) {
            String schema = schemas.getString(1);
            TreeElement el = new TreeElement(schema, Icon.SCHEMA);
            DefaultMutableTreeNode schemaNode = new DefaultMutableTreeNode(el);
            //se obtiene la lista de tablas
            schemaNode.add(this.getFunctions(schema));
            schemaNode.add(this.getSequences(schema));
            schemaNode.add(this.getTables(schema));
            this.add(schemaNode);

        }
    }
}
