/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.py.mbaez.porandu.plugin;

import com.py.mbaez.porandu.icon.Icon;
import com.py.mbaez.porandu.util.TypeEnum;

/**
 *
 * @author mbaez
 */
public class TreeElement {

    private String name;
    private String icon;
    private TypeEnum type;

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public TreeElement(String name, TypeEnum type) {
        this.name = name;
        this.type = type;
        if (type == TypeEnum.BASE_DATOS) {
            icon = Icon.DATABASE;
        } else if (type == TypeEnum.TABLAS) {
            icon = Icon.TABLES;
        } else if (type == TypeEnum.TABLA) {
            icon = Icon.TABLE;
        } else if (type == TypeEnum.FUNCIONES) {
            icon = Icon.FUNCTIONS;
        } else if (type == TypeEnum.FUNCION) {
            icon = Icon.FUNCTION;
        } else if (type == TypeEnum.INDICES) {
            icon = Icon.INDEX;
        } else if (type == TypeEnum.INDICE) {
            icon = Icon.INDEXES;
        } else if (type == TypeEnum.VIEWS) {
            icon = Icon.VIEWS;
        } else if (type == TypeEnum.VIEW) {
            icon = Icon.VIEW;
        } else if (type == TypeEnum.SEQUENCIAS) {
            icon = Icon.SEQUENCES;
        } else if (type == TypeEnum.SEQUENCIA) {
            icon = Icon.SEQUENCE;
        } else if (type == TypeEnum.SCHEMAS) {
            icon = Icon.SCHEMAS;
        } else if (type == TypeEnum.SCHEMA) {
            icon = Icon.SCHEMA;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}
