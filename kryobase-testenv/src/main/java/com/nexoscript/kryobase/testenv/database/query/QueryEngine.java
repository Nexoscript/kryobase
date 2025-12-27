package com.nexoscript.kryobase.testenv.database.query;

import com.nexoscript.kryobase.testenv.database.storage.Row;
import com.nexoscript.kryobase.testenv.database.storage.StorageEngine;

import java.util.List;

public class QueryEngine {
    private StorageEngine storage;

    public QueryEngine(StorageEngine storage) {
        this.storage = storage;
    }

    public List<Row> execute(Query query) {
        return storage.table(query.table())
                .rows()
                .stream()
                .filter(query.filter())
                .toList();
    }
}
