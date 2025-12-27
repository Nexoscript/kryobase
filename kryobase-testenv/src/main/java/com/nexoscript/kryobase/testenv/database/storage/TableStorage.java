package com.nexoscript.kryobase.testenv.database.storage;

import com.nexoscript.kryobase.testenv.database.schema.EntitySchema;
import com.nexoscript.kryobase.testenv.util.JsonUtil;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

public class TableStorage {

    private final Path tableDir;

    public TableStorage(Path baseDir) {
        this.tableDir = baseDir.resolve("tables");
    }

    public void init() {
        try {
            Files.createDirectories(tableDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAll(Collection<Table> tables) {
        for (Table table : tables) {
            save(table);
        }
    }

    public void save(Table table) {
        try {
            Path file = tableDir.resolve(table.name() + ".table.json");
            try (Writer w = Files.newBufferedWriter(file)) {
                JsonUtil.GSON.toJson(table, w);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Table> load(EntitySchema schema) {
        try {
            Path file = tableDir.resolve(schema.name() + ".table.json");
            if (!Files.exists(file)) return Optional.empty();

            try (Reader r = Files.newBufferedReader(file)) {
                Table table = JsonUtil.GSON.fromJson(r, Table.class);
                table.attachSchema(schema);
                return Optional.of(table);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}

