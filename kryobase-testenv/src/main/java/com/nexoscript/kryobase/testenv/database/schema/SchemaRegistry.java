package com.nexoscript.kryobase.testenv.database.schema;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SchemaRegistry {

    private final Map<String, EntitySchema> schemas = new HashMap<>();

    public void register(EntitySchema schema) {
        schemas.put(schema.name(), schema);
    }

    public EntitySchema get(String name) {
        return schemas.get(name);
    }

    public Collection<EntitySchema> all() {
        return schemas.values();
    }
}

