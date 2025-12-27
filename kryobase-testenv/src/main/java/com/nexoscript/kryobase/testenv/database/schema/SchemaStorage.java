package com.nexoscript.kryobase.testenv.database.schema;

import com.nexoscript.kryobase.testenv.util.JsonUtil;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SchemaStorage {

    private final Path schemaDir;

    public SchemaStorage(Path baseDir) {
        this.schemaDir = baseDir.resolve("schema");
    }

    public void init() {
        try {
            Files.createDirectories(schemaDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(EntitySchema schema) {
        try {
            Path file = schemaDir.resolve(schema.name() + ".schema.json");

            try (Writer writer = Files.newBufferedWriter(file)) {
                JsonUtil.GSON.toJson(schema, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EntitySchema load(String name) {
        try {
            Path file = schemaDir.resolve(name + ".schema.json");

            try (Reader reader = Files.newBufferedReader(file)) {
                return JsonUtil.GSON.fromJson(reader, EntitySchema.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean exists(String schemaName) {
        return Files.exists(schemaDir.resolve(schemaName + ".schema.json"));
    }


    public List<EntitySchema> loadAll() {
        try {
            if (!Files.exists(schemaDir)) return List.of();

            List<EntitySchema> schemas = new ArrayList<>();
            try (var stream = Files.list(schemaDir)) {
                for (Path file : stream.toList()) {
                    try (Reader r = Files.newBufferedReader(file)) {
                        schemas.add(JsonUtil.GSON.fromJson(r, EntitySchema.class));
                    }
                }
            }
            return schemas;
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }
}

