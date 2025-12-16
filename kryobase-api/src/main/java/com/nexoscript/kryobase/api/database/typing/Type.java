package com.nexoscript.kryobase.api.database.typing;

import java.util.Date;

public enum Type {
    STRING(String.class, "str"),
    INTEGER(Integer.class, "int"),
    BOOLEAN(Boolean.class, "bool"),
    DATE(Date.class, "date");

    private final Class<?> clazz;
    private final String name;

    Type(Class<?> clazz, String name) {
        this.clazz = clazz;
        this.name = name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }
}
