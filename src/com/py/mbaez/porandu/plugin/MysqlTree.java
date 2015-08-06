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
public class MysqlTree extends TreeView {

    private static final String TABLES_LIST = "SHOW TABLES;";
    private static final String DATABASE_NAME = "SELECT DATABASE();";
    private static final String DESCRIBE_TABLE = "DESCRIBE ";

    public MysqlTree(Connection conexion) {
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
        TreeElement dbEl = new TreeElement(databaseName, TypeEnum.BASE_DATOS);
        //se seta el nombre
        this.setUserObject(dbEl);
        this.add(getTables());

    }

}
