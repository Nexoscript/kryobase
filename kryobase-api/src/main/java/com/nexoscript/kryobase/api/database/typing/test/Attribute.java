package com.nexoscript.kryobase.api.database.typing.test;

import com.nexoscript.kryobase.api.database.typing.*;
import com.nexoscript.kryobase.api.database.typing.pair.IPropertyKey;
import com.nexoscript.kryobase.api.database.typing.pair.IPropertyValue;

/*
* namespace:key:name -> pair
* Pair = key#value
* TypedPair = (@type)key#value
*  */
public record Attribute(INamespace namespace, IKey key, IName name,
                        TypedPair<? extends IPropertyKey<?>, ? extends IPropertyValue<?>> pair) implements IAttribute {

    public static IAttribute test() {
        PropertyKey<String> key = new PropertyKey<>("age");
        PropertyValue<Object> value = new PropertyValue<>(13);
        return new Attribute(null, null, null, new TypedPair<>(Type.INTEGER, key, value));
    }
}
