/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.components;

import com.py.mbaez.porandu.icon.Icon;
import com.py.mbaez.porandu.plugin.*;
import com.py.mbaez.porandu.util.TypeEnum;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author mbaez
 */
public class TreeView extends DefaultMutableTreeNode {

    protected Connection conexion = null;

    public TreeView(Connection conexion) {
        this.conexion = conexion;
    }

    protected ResultSet execute(String sql) throws SQLException {
        Statement st = conexion.createStatement();
        return st.executeQuery(sql);
    }

    public void buildTree() throws SQLException {
        DefaultMutableTreeNode tableNode = null;
        String tipos[] = {"TABLE"};
        DatabaseMetaData dbmd = conexion.getMetaData();
        ResultSet schemas = dbmd.getSchemas();

        while (schemas.next()) {
            String schema = schemas.getString(schemas.findColumn("TABLE_SCHEM"));

            ResultSet tablas = dbmd.getTables(null, schema, null, tipos);
            String table;
            TreeElement el = new TreeElement(schema, TypeEnum.SCHEMA);
            tableNode = new DefaultMutableTreeNode(el);
            boolean hasChilds = false;
            while (tablas.next()) {
                hasChilds = true;
                table = tablas.getString(tablas.findColumn("TABLE_NAME"));
                TreeElement tEl = new TreeElement(table, TypeEnum.TABLA);
                DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(tEl);
                tableNode.add(nodo);
            }
            if (hasChilds) {
                this.add(tableNode);
            }
        }
    }

}
