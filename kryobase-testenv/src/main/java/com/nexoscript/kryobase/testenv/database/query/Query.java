package com.nexoscript.kryobase.testenv.database.query;

import com.nexoscript.kryobase.testenv.database.storage.Row;

import java.util.function.Predicate;

public class Query {
    private String table;
    private Predicate<Row> filter = r -> true;

    public Query(String table) {
        this.table = table;
    }

    public Query where(Predicate<Row> predicate) {
        this.filter = predicate;
        return this;
    }

    public String table() {
        return table;
    }

    public Predicate<Row> filter() {
        return filter;
    }
}
