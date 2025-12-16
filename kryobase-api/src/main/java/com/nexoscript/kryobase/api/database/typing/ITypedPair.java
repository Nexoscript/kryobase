package com.nexoscript.kryobase.api.database.typing;

public interface ITypedPair<K, V> extends IPair<K, V> {
    Type type();
    K key();
    V value();
}
