package com.hawahuri.expensemanager.models;

import java.io.Serializable;

public class Category implements Serializable {

    private String _id, name, type, icon, creator;

    public Category(String name, String type, String icon, String creator) {
        this.name = name;
        this.type = type;
        this.icon = icon;
        this.creator = creator;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getIcon() {
        return icon;
    }

    public String getCreator() {
        return creator;
    }
}
