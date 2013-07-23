/**
 * 
 */
package com.py.mbaez.porandu.util;

import java.security.CodeSource;
import java.util.HashMap;
import java.util.Set;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Se encarga de leer los datos del datasource e inicializar el hashMap para
 * facilitar el acceso a los parámetros
 * 
 * @author Maximiliano Báez <mbaez@konecta.com.py>
 * @version 1.0 03/06/2012
 */
public class DataSourceManager {

    private XMLManager xmlDataSource;
    private HashMap<String, DataSource> dataSourceHashMap;
    private String xmlFile = "datasource.xml";

    /**
     * Consturctor de la clase DataSourceManager, encargada de manejar el datasource
     * definidos en xml.
     * 
     * @throws Exception
     *             si ocurrio un problema al procesar el xml del datasource
     * 
     * @see XMLManger
     */
    public DataSourceManager() throws Exception {
        try {
            // se establece el path de los archivos xml
            String path =  xmlFile;
            // se procesan los tags property unicamente
            xmlDataSource = new XMLManager(path , "datasource",
                    getClass());
            // se inicializa la tabla hash
            dataSourceHashMap = new HashMap<String, DataSource>();
            // se cargan los tags en la tabla
            initTable();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al leer el archivo " + xmlFile, e);
        }

    }

    /**
     * Inicializa la una tabla hash en donde se genera una entrada por cada
     * property
     */
    private void initTable() {
        Node cima;
        NamedNodeMap atributos;
        // se reinicia el puntero de la lista para comenzar a buscar desde el
        // inicio.
        xmlDataSource.reset();
        // se obtiene la cima
        cima = xmlDataSource.getNextNode();
        while (cima != null) {
            // se obtienen los atributos de la cima
            DataSource ds = getDatasource(cima);
            /*
             * se obtiene el nombre del datasource y el valor del mismo y se genera
             * una nueva entrada en la tabla hash
             */
            System.out.println(ds.getName());
            dataSourceHashMap.put(ds.getName(), ds);
            // se obtiene el siguiente nodo
            cima = xmlDataSource.getNextNode();

        }
    }

    /**
     * Este método se encarga de procesar el nodo y extraer los datos del mismos
     * @param cima
     * @return 
     */
    private DataSource getDatasource(Node cima) {
        NodeList childs = cima.getChildNodes();
        DataSource ds = new DataSource();
        for (int i = 0; i < childs.getLength(); i++) {
            Node child = childs.item(i);
            String name = child.getNodeName();
            String value = child.getTextContent();
            ds.put(name, value);
        }
        return ds;

    }

    /**
     * Este método obtiene la referencia del objeto datasource a partir del nombre
     * del mismo.
     * 
     * @param name
     *            es nombre de la propiedad
     * @return el valor del property
     */
    public DataSource getPropertyByName(String name) throws Exception {

        DataSource property = (DataSource) dataSourceHashMap.get(name);
        if (property == null) {
            throw new Exception("La configuración " + name + " no existe");
        }
        return property;
    }
    
    public Set<String> getNames(){
        return this.dataSourceHashMap.keySet();
    }
}
