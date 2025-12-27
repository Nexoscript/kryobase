package com.nexoscript.kryobase.testenv.database.storage;

import com.nexoscript.kryobase.testenv.database.schema.EntitySchema;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StorageEngine {
    private Map<String, Table> tables = new HashMap<>();

    public void registerTable(Table table) {
        tables.put(table.name(), table);
    }

    public Table createTable(EntitySchema schema) {
        Table table = new Table(schema);
        tables.put(schema.name(), table);
        return table;
    }

    public Table table(String name) {
        return tables.get(name);
    }

    public Collection<Table> tables() {
        return tables.values();
    }
}
