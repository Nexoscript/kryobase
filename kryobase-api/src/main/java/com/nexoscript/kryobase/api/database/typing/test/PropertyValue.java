package com.nexoscript.kryobase.api.database.typing.test;

import com.nexoscript.kryobase.api.database.typing.pair.IPropertyValue;

public class PropertyValue<T> implements IPropertyValue<T> {
    private final T value;

    public PropertyValue(T value) {
        this.value = value;
    }

    @Override
    public T value() {
        return value;
    }
}
