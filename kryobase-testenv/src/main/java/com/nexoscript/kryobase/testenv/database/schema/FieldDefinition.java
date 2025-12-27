package com.nexoscript.kryobase.testenv.database.schema;

public class FieldDefinition {

    private String name;
    private FieldType type;
    private boolean primaryKey;

    public FieldDefinition() {}

    public FieldDefinition(String name, FieldType type, boolean primaryKey) {
        this.name = name;
        this.type = type;
        this.primaryKey = primaryKey;
    }

    public String name() {
        return name;
    }

    public FieldType type() {
        return type;
    }

    public boolean primaryKey() {
        return primaryKey;
    }
}

