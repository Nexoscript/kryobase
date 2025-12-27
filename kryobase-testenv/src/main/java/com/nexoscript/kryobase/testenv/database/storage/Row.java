package com.nexoscript.kryobase.testenv.database.storage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Row {
    private Map<String, Object> values = new HashMap<>();

    public Row set(String key, Object value) {
        values.put(key, value);
        return this;
    }

    public <T> T get(String key) {
        return (T) values.get(key);
    }

    public Map<String, Object> values() {
        return Collections.unmodifiableMap(values);
    }

}
