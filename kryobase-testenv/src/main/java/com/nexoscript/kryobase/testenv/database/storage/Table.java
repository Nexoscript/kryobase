package com.nexoscript.kryobase.testenv.database.storage;

import com.nexoscript.kryobase.testenv.database.schema.EntitySchema;
import com.nexoscript.kryobase.testenv.database.schema.FieldDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Table {
    private EntitySchema schema;
    private List<Row> rows = new ArrayList<>();

    public Table(EntitySchema schema) {
        this.schema = schema;
    }

    public void insert(Row row) {
        validate(row);
        rows.add(row);
    }

    public String name() {
        return schema.name();
    }

    public List<Row> rows() {
        return Collections.unmodifiableList(rows);
    }

    public void attachSchema(EntitySchema schema) {
        this.schema = schema;
    }

    protected void validate(Row row) {
        for (FieldDefinition field : schema.fields()) {
            if(!row.values().containsKey(field.name())) {
                throw new IllegalStateException(
                        "Missing field: " + field.name()
                );
            }
        }
    }


}
