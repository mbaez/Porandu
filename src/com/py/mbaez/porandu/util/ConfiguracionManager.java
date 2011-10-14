/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.py.mbaez.porandu.util;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author Maximiliano Báez González <mxbg.py@gmail.com>
 */
public class ConfiguracionManager {

    private Properties configuracion;

    public ConfiguracionManager(){
        try{
            init("porandu.conf");
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void init(String fileName) throws Exception {
        configuracion = new Properties();
        configuracion.load(new FileInputStream(fileName));
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
