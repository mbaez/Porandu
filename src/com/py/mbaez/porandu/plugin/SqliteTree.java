/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.plugin;

import com.py.mbaez.porandu.components.TreeView;
import com.py.mbaez.porandu.icon.Icon;
import com.py.mbaez.porandu.util.TypeEnum;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author mbaez
 */
public class SqliteTree extends TreeView {

    private static final String TABLES_LIST = "SELECT name FROM sqlite_master where type='table';";
    private static final String INDEX_LIST = "SELECT name FROM sqlite_master where type='index';";

    public SqliteTree(Connection conexion) {
        super(conexion);
    }

    private DefaultMutableTreeNode getTables() throws SQLException {
        TreeElement parentEl = new TreeElement("Tablas", TypeEnum.TABLAS);
        DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode(parentEl);
        //se obtiene la lista de tablas
        ResultSet tablas = execute(TABLES_LIST);
        String table;
        while (tablas.next()) {
            table = tablas.getString(1);
            TreeElement el = new TreeElement(table, TypeEnum.TABLA);
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(el);
            parentNode.add(nodo);
        }
        return parentNode;
    }

    private DefaultMutableTreeNode getIndex() throws SQLException {
        ResultSet rows = execute(INDEX_LIST);
        String value;
        TreeElement parentEl = new TreeElement("Indices", TypeEnum.INDICES);
        DefaultMutableTreeNode parent = new DefaultMutableTreeNode(parentEl);
        while (rows.next()) {
            value = rows.getString(1);
            TreeElement el = new TreeElement(value, TypeEnum.INDICE);
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

        String databaseName = "Database";
        TreeElement dbEl = new TreeElement(databaseName, TypeEnum.BASE_DATOS);
        //se seta el nombre
        this.setUserObject(dbEl);
        this.add(getIndex());
        this.add(getTables());

    }

}
