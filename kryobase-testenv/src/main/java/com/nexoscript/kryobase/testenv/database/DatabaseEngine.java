package com.nexoscript.kryobase.testenv.database;

import com.nexoscript.kryobase.testenv.database.query.QueryEngine;
import com.nexoscript.kryobase.testenv.database.schema.EntitySchema;
import com.nexoscript.kryobase.testenv.database.schema.SchemaRegistry;
import com.nexoscript.kryobase.testenv.database.schema.SchemaStorage;
import com.nexoscript.kryobase.testenv.database.storage.StorageEngine;
import com.nexoscript.kryobase.testenv.database.storage.Table;
import com.nexoscript.kryobase.testenv.database.storage.TableStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Supplier;

public class DatabaseEngine {

    private final Path baseDir;

    private final StorageEngine storageEngine;
    private final SchemaRegistry schemaRegistry;
    private final SchemaStorage schemaStorage;
    private final TableStorage tableStorage;
    private final QueryEngine queryEngine;

    public DatabaseEngine(Path baseDir) {
        this.baseDir = baseDir;

        this.schemaRegistry = new SchemaRegistry();
        this.storageEngine = new StorageEngine();
        this.schemaStorage = new SchemaStorage(baseDir);
        this.tableStorage = new TableStorage(baseDir);
        this.queryEngine = new QueryEngine(storageEngine);
    }

    public void start() {
        initDirectories();
        loadSchemas();
        loadTables();
    }

    public void shutdown() {
        saveAll();
    }

    private void initDirectories() {
        try {
            Files.createDirectories(baseDir);
            schemaStorage.init();
            tableStorage.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSchemas() {
        for (EntitySchema schema : schemaStorage.loadAll()) {
            schemaRegistry.register(schema);
        }
    }

    private void loadTables() {
        for (EntitySchema schema : schemaRegistry.all()) {
            tableStorage.load(schema)
                    .ifPresent(storageEngine::registerTable);
        }
    }

    private void saveAll() {
        for (EntitySchema schema : schemaRegistry.all()) {
            schemaStorage.save(schema);
        }
        tableStorage.saveAll(storageEngine.tables());
    }

    public SchemaRegistry schemas() {
        return schemaRegistry;
    }

    public StorageEngine storage() {
        return storageEngine;
    }

    public QueryEngine queries() {
        return queryEngine;
    }

    public EntitySchema getOrCreateSchema(
            String name,
            Supplier<EntitySchema> factory
    ) {

        if (schemaStorage.exists(name)) {
            EntitySchema schema = schemaStorage.load(name);
            schemaRegistry.register(schema);
            return schema;
        }

        EntitySchema schema = factory.get();
        schemaStorage.save(schema);
        schemaRegistry.register(schema);
        return schema;
    }

    public Table getOrCreateTable(EntitySchema schema) {

        Optional<Table> loaded = tableStorage.load(schema);
        if (loaded.isPresent()) {
            storageEngine.registerTable(loaded.get());
            return loaded.get();
        }

        Table table = storageEngine.createTable(schema);
        tableStorage.save(table);
        return table;
    }
}

