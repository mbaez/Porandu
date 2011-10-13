/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SqlQueryResult.java
 *
 * Created on 15/02/2011, 02:56:35 PM
 */
package com.py.mbaez.porandu.gui;

import com.py.mbaez.porandu.util.EditableTableModel;
import com.py.mbaez.porandu.util.FileIO;
import com.py.mbaez.porandu.util.PgQuery;
import com.py.mbaez.porandu.util.PgSession;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author mxbg
 */
public class SqlQueryResult extends javax.swing.JPanel {

    private PgQuery query;
    private int connexionIndex;
    private Thread thread;
    private DefaultMutableTreeNode rootNode;

    /** Creates new form SqlQueryResult */
    public SqlQueryResult(int connexionIndex) {
        this.connexionIndex = connexionIndex;
        try {
            createNodes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        //loadThreeIcons();

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        karakuSplitPane = new javax.swing.JSplitPane();
        javax.swing.JEditorPane editorPane= new javax.swing.JEditorPane();
        queryScrollPane = new javax.swing.JScrollPane(editorPane);
        queryEditorPane = editorPane;
        ResultTabbedPane = new javax.swing.JTabbedPane();
        resultScrollPane = new javax.swing.JScrollPane();
        resulTable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        messagesTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablesTree = new javax.swing.JTree(rootNode);

        setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSplitPane1.setDividerLocation(150);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setOneTouchExpandable(true);

        karakuSplitPane.setDividerLocation(150);
        karakuSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        karakuSplitPane.setContinuousLayout(true);
        karakuSplitPane.setOneTouchExpandable(true);

        queryScrollPane.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 1, 0, java.awt.SystemColor.windowBorder));
        queryScrollPane.setMinimumSize(new java.awt.Dimension(100, 100));

        queryEditorPane.setContentType("text/sql");
        queryEditorPane.setFont(new java.awt.Font("Monospaced", 0, 15));
        queryScrollPane.setViewportView(queryEditorPane);

        karakuSplitPane.setLeftComponent(queryScrollPane);

        resulTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        resulTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        resulTable.setColumnSelectionAllowed(true);
        resultScrollPane.setViewportView(resulTable);

        ResultTabbedPane.addTab("Output", resultScrollPane);

        messagesTextArea.setColumns(20);
        messagesTextArea.setRows(5);
        jScrollPane1.setViewportView(messagesTextArea);

        ResultTabbedPane.addTab("Messages", jScrollPane1);

        karakuSplitPane.setBottomComponent(ResultTabbedPane);

        jSplitPane1.setRightComponent(karakuSplitPane);

        tablesTree.setCellRenderer(loadThreeIcons());
        jScrollPane2.setViewportView(tablesTree);

        jSplitPane1.setLeftComponent(jScrollPane2);

        add(jSplitPane1);
    }// </editor-fold>//GEN-END:initComponents

    public void ejecutarLocalQuery() {
        if (thread != null && thread.isAlive()) {
            System.out.println(thread.getState());
            return;
        }
        thread = (new Thread() {

            public void run() {
                ejecutarThread();
            }
        });
        thread.start();
    }

    public void cancelarLocalQuery() {
        try {
            query.cancel();
            System.out.println("stoped");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void ejecutarThread() {
        try {
            ResultTabbedPane.setSelectedIndex(0);
            TableModel modelo = resulTable.getModel();
            if (modelo instanceof EditableTableModel) {
                ((EditableTableModel) modelo).removeRow();
            }

            String sqlText = queryEditorPane.getSelectedText();
            if (sqlText == null || sqlText.equals("")) {
                sqlText = queryEditorPane.getText();
            }

            query = new PgQuery(SqlQueryResult.this.connexionIndex);
            resulTable.setModel(query.execute(sqlText));
        } catch (SQLException e) {

            messagesTextArea.setText("Error : " + e.getMessage());
            ResultTabbedPane.setSelectedIndex(1);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(SqlQueryResult.this, e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    /**
     *
     */
    public void guardarLocalResult() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            //Obtiene el modelo de la tabla
            EditableTableModel model = (EditableTableModel) resulTable.getModel();
            //Anhade el nombre de las columnas de la tabla para que sean visibles
            //cabecera en el archivo
            data.add(model.getColumnIdentifiers());
            //anhade las filas
            for (int i = 0; i < resulTable.getRowCount(); i++) {
                data.add(model.getValueAt(i));
            }
            //gurada el archivo
            FileIO file = new FileIO(fileChooser.getSelectedFile().getAbsolutePath());
            file.saveFile(data);
        }

    }

    /**
     *
     */
    public void guardarLocalQuery() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            ArrayList<Object[]> data = new ArrayList<Object[]>();
            //Obtiene el texto del editor
            data.add(new Object[]{this.queryEditorPane.getText()});
            //gurada el archivo
            FileIO file = new FileIO(fileChooser.getSelectedFile().
                    getAbsolutePath());
            file.saveFile(data);
        }

    }

    public DefaultTreeCellRenderer loadThreeIcons() {

        ImageIcon databaseIcon = new ImageIcon(getClass().
                getResource("/com/py/mbaez/porandu/icon/database.png"));
        DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
        render.setOpenIcon(databaseIcon);
        render.setClosedIcon(databaseIcon);
        databaseIcon = new ImageIcon(getClass().
                getResource("/com/py/mbaez/porandu/icon/table.png"));
        render.setLeafIcon(databaseIcon);
        return render;
    }

    /**
     * 
     */
    private void createNodes() throws SQLException {
        rootNode = new DefaultMutableTreeNode(PgSession.DATABASE);
        DefaultMutableTreeNode tableNode = null;

        Connection conexion = PgSession.CURRENTCONEXION.get(connexionIndex);

        String tipos[] = {"TABLE"};
        DatabaseMetaData dbmd = conexion.getMetaData();
        ResultSet schemas = dbmd.getSchemas();

        while (schemas.next()) {
            String schema = schemas.getString(schemas.findColumn("TABLE_SCHEM"));

            ResultSet tablas = dbmd.getTables(null, schema, null, tipos);
            String table;
            if(!tablas.isClosed()){
                tableNode = new DefaultMutableTreeNode(schema);
                rootNode.add(tableNode);
            }
            while (tablas.next()) {
                table = tablas.getString(tablas.findColumn("TABLE_NAME"));
                DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(table);
                tableNode.add(nodo);
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane ResultTabbedPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane karakuSplitPane;
    private javax.swing.JTextArea messagesTextArea;
    private javax.swing.JEditorPane queryEditorPane;
    private javax.swing.JScrollPane queryScrollPane;
    private javax.swing.JTable resulTable;
    private javax.swing.JScrollPane resultScrollPane;
    private javax.swing.JTree tablesTree;
    // End of variables declaration//GEN-END:variables
}
