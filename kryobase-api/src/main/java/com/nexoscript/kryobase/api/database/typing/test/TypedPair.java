package com.nexoscript.kryobase.api.database.typing.test;

import com.nexoscript.kryobase.api.database.typing.ITypedPair;
import com.nexoscript.kryobase.api.database.typing.Type;

import java.util.Objects;

public record TypedPair<K, V>(Type type, K key, V value) implements ITypedPair<K, V> {

    @Override
    public String toString() {
        return "(@" + type.getName() + ")" + key + "#" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypedPair<?, ?>(Type type1, Object key1, Object value1))) return false;
        return Objects.equals(type, type1) &&
                Objects.equals(key, key1) &&
                Objects.equals(value, value1);
    }
}
