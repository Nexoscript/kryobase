package com.nexoscript.kryobase.api.database.typing.test;

import com.nexoscript.kryobase.api.database.typing.pair.IPropertyKey;

public class PropertyKey<T> implements IPropertyKey<T> {
    private final T key;

    public PropertyKey(T key) {
        this.key = key;
    }

    @Override
    public T key() {
        return key;
    }
}
