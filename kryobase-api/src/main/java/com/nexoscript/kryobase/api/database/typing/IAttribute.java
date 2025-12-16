package com.nexoscript.kryobase.api.database.typing;

import com.nexoscript.kryobase.api.database.typing.pair.IPropertyKey;
import com.nexoscript.kryobase.api.database.typing.pair.IPropertyValue;

public interface IAttribute {
    INamespace namespace();
    IKey key();
    IName name();
    IPair<? extends IPropertyKey<?>, ? extends IPropertyValue<?>> pair();
}
