/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.plugin;

import com.py.mbaez.porandu.components.TreeView;
import com.py.mbaez.porandu.icon.Icon;
import com.py.mbaez.porandu.util.StringUtil;
import com.py.mbaez.porandu.util.TypeEnum;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author mbaez
 */
public class SqlServerTree extends TreeView {

    private String DB_INFO = "SELECT * FROM information_schema.tables order by TABLE_NAME";

    public SqlServerTree(Connection conexion) {
        super(conexion);
    }

    public TypeEnum getIcon(String type, boolean group) {
        TypeEnum icon = null;
        if (type.contains("TABLE")) {
            icon = group ? TypeEnum.TABLAS : TypeEnum.TABLA;
        } else if (type.contains("VIEW")) {
            icon = group ? TypeEnum.VIEWS : TypeEnum.VIEW;
        } else if (type.contains("PROCEDURE")) {
            icon = group ? TypeEnum.FUNCIONES : TypeEnum.FUNCION;
        }
        return icon;
    }

    @Override
    public void buildTree() throws SQLException {
        HashMap<String, DefaultMutableTreeNode> info = new HashMap<String, DefaultMutableTreeNode>();
        ResultSet rs = execute(DB_INFO);
        while (rs.next()) {
            String catalog = rs.getString("TABLE_CATALOG");
            String schema = rs.getString("TABLE_SCHEMA");
            String table = rs.getString("TABLE_NAME");
            String type = rs.getString("TABLE_TYPE");
            //base de datos
            if (!info.containsKey(catalog)) {
                TreeElement el = new TreeElement(catalog, TypeEnum.BASE_DATOS);
                DefaultMutableTreeNode cNode = new DefaultMutableTreeNode(el);
                this.add(cNode);
                info.put(catalog, cNode);
            }

            DefaultMutableTreeNode cNode = info.get(catalog);
            //esquema info
            String key = catalog + "-" + schema;
            if (!info.containsKey(key)) {
                TreeElement el = new TreeElement(schema, TypeEnum.SCHEMA);
                DefaultMutableTreeNode sNode = new DefaultMutableTreeNode(el);
                cNode.add(sNode);
                info.put(key, sNode);
            }
            //tipo de elemento, tabla o view
            DefaultMutableTreeNode sNode = info.get(key);
            key = catalog + "-" + schema + "-" + type;
            if (!info.containsKey(key)) {
                String formatType = type.replace("BASE ", "").toLowerCase();
                formatType = StringUtil.toBeautifulCase(formatType + "s");
                TreeElement el = new TreeElement(formatType, getIcon(type, true));
                DefaultMutableTreeNode tNode = new DefaultMutableTreeNode(el);
                sNode.add(tNode);
                info.put(key, tNode);
            }
            //nodos hoja
            DefaultMutableTreeNode tNode = info.get(key);
            TreeElement el = new TreeElement(table, getIcon(type, false));
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(el);
            tNode.add(node);

        }
    }

}
