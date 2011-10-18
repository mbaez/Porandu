/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.py.mbaez.porandu.util;

import java.io.FileInputStream;
import java.io.File;
import java.util.Properties;
import java.util.Set;
import java.security.CodeSource;
import javax.swing.JOptionPane;
import com.py.mbaez.porandu.gui.*;
/**
 *
 * @author Maximiliano Báez González <mxbg.py@gmail.com>
 */
public class ConfiguracionManager {

    private Properties configuracion;

    public ConfiguracionManager() throws Exception{
            init("porandu.conf");
    }

    private void init(String fileName) throws Exception {
        configuracion = new Properties();

        CodeSource codeSource = getClass().getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        File jarDir = jarFile.getParentFile();
        File propFile;

        if(jarDir != null && !jarFile.getName().contains("classes"))
            propFile = new File(jarDir, fileName);
        else
            propFile = new File(fileName);
        configuracion.load(new FileInputStream(propFile));
        configuracion.stringPropertyNames();
    }

    public Set<String> getDrivers(){
        return configuracion.stringPropertyNames();
    }
    
    public String getUrl(String key) throws Exception{
        String url = (String)configuracion.getProperty(key);
        return url;
    }

}
