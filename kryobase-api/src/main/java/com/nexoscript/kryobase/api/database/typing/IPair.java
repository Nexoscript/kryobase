package com.nexoscript.kryobase.api.database.typing;

public interface IPair<K, V> {
    K key();
    V value();
}
