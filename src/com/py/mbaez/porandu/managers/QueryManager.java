/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.managers;

import com.py.mbaez.porandu.components.EditableTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 *
 * @author mbaez
 */
public class QueryManager {

    private Statement st;
    private ResultSet rs;

    /**
     *
     * @param connexionIndex
     */
    public QueryManager(int connexionIndex) {
        try {
            st = SessionManager.CURRENTCONEXION.get(connexionIndex).createStatement();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    /**
     *
     * @throws Exception
     */
    public void cancel() throws Exception {
        st.cancel();
    }

    /**
     *
     * @param sql
     * @return EditableTableModel
     * @throws Exception
     */
    public EditableTableModel execute(String sql) throws Exception {
        int i = 0;
        EditableTableModel resulTableModel;
        rs = st.executeQuery(sql);

        ResultSetMetaData meta = rs.getMetaData();
        int columnLength = meta.getColumnCount();
        String columnName[] = new String[columnLength];
        Class clazz[] = new Class[columnLength];
        boolean editable[] = new boolean[columnLength];

        for (i = 0; i < meta.getColumnCount(); i++) {
            columnName[i] = meta.getColumnName(i + 1);
            clazz[i] = Object.class;
            editable[i] = false;
        }

        resulTableModel = new EditableTableModel(columnName, clazz, editable);

        while (rs.next()) {
            i = 0;
            Object row[] = new Object[columnLength];
            while (i < row.length) {
                row[i] = rs.getObject(i + 1);
                i++;
            }
            resulTableModel.addRow(row);
        }
        return resulTableModel;
    }
}
