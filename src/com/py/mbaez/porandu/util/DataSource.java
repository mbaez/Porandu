/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.util;

import java.util.HashMap;

/**
 * Esta clase contiene la información del datasource
 * @author mbaez
 */
public class DataSource extends HashMap<String, String> {

    public String getDriver() {
        return this.get("driver-class");
    }

    public void setDriver(String driver) {
        this.put("driver-class", driver);
    }

    public String getName() {
        return this.get("name");
    }

    public void setName(String name) {
        this.put("name", name);
    }

    public String getPassword() {
        return this.get("password");
    }

    public void setPassword(String password) {
        this.put("password", password);
    }

    public String getUrl() {
        return this.get("connection-url");
    }

    public void setUrl(String url) {
        this.put("connection-url", url);
    }

    public String getUsername() {
        return this.get("user-name");
    }

    public void setUsername(String username) {
        this.put("user-name", username);
    }
}
