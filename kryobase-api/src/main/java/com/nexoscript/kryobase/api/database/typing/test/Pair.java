package com.nexoscript.kryobase.api.database.typing.test;

import com.nexoscript.kryobase.api.database.typing.IPair;

import java.util.Objects;

public record Pair<K, V>(K key, V value) implements IPair<K, V> {

    @Override
    public String toString() {
        return key + "#" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair<?, ?>(Object key1, Object value1))) return false;
        return Objects.equals(key, key1) &&
                Objects.equals(value, value1);
    }
}
