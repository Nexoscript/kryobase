package com.nexoscript.kryobase.testenv.database.schema;

import java.util.*;

public class EntitySchema {
    private String name;
    private final int version;
    private List<FieldDefinition> definitions;
    private transient Map<String, FieldDefinition> fields = new LinkedHashMap<>();

    public EntitySchema(String name, int version, List<FieldDefinition> definitions) {
        this.name = name;
        this.version = version;
        this.definitions = definitions;
        definitions.forEach(field -> this.fields.put(field.name(), field));
    }

    public FieldDefinition field(String name) {
        ensureFieldsInitialized();
        return fields.get(name);
    }

    public Collection<FieldDefinition> fields() {
        ensureFieldsInitialized();
        return fields.values();
    }

    private void ensureFieldsInitialized() {
        if (fields == null) {
            fields = new LinkedHashMap<>();
        }
        // Falls die Map leer ist, aber wir Definitionen haben (frisch aus JSON)
        if (fields.isEmpty() && definitions != null && !definitions.isEmpty()) {
            for (FieldDefinition def : definitions) {
                fields.put(def.name(), def);
            }
        }
    }

    public String name() {
        return name;
    }

    public boolean hasField(String name) {
        return fields.containsKey(name);
    }

    public int version() {
        return version;
    }
}
